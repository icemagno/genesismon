package br.com.cmabreu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@EnableScheduling
public class UserService {
	private Logger logger = LoggerFactory.getLogger( UserService.class );
	private Web3j web3;
	private List<TokenInfo> tokens;
	private Web3j web3Sk;
	
	@Value("${bscscan.key}")
	private String bscScanApiKey; //PRGH4EEVRARQM68YX3IA4ZVBAYTADKHHQJ
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;   	
	
	@Autowired
	private RESTService restService;

	public void sendToUser( Object payload) {
		simpMessagingTemplate.convertAndSend( "/tokens", payload );
	}
	
	public List<TokenInfo> getTokens(){
		return this.tokens;
	}
		
	@PostConstruct
	public void init() {
		try {
			String endpoint = "https://bsc-mainnet.web3api.com/v1/38SCJC71VPWUVN7UB72FE7PW9UXMV6FHSE/";
			this.tokens = new ArrayList<TokenInfo>();
			
			WebSocketService web3jService = new WebSocketService("wss://bsc-ws-node.nariox.org:443	", true);
			web3jService.connect();
			web3Sk = Web3j.build(web3jService);			
			web3Sk.blockFlowable(false).subscribe(block -> {
			    System.out.println("NEW BLOCK -> " + block.getBlock().getNumber().intValue());
			});			
			
			
			web3 = Web3j.build( new HttpService( endpoint ) ); 
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			logger.info( clientVersion );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private String getLastBlock() {
		return get( "https://api.bscscan.com/api?module=proxy&action=eth_blockNumber" );
	}	
	
	private String get( String url ) {
		return restService.doRequestGet( url );		
	}
	
	/*
	public void testEthGetBlockByNumber() throws Exception {
	    EthBlock ethBlock = web3.ethGetBlockByNumber( DefaultBlockParameter.valueOf(BigInteger.valueOf(2391)), true).send();
	    EthBlock.Block block = ethBlock.getBlock();

	    for ( TransactionResult tx : block.getTransactions() ) {
	        System.out.println( " >>>>> " +   ((TransactionObject) tx).getCreates());
	        EthTransaction ethTransaction = web3.ethGetTransactionByHash(((TransactionObject) tx).getHash()).send();
	        System.out.println(ethTransaction.getTransaction().get().getCreates());
	    }
	    System.out.println(new ObjectMapper().writeValueAsString(block));
	}
	*/	
	
	
	@Scheduled(fixedDelay = 5500 )
	private void checkBlock() {
		String lastBlock = getLastBlock();
		JSONObject lb = new JSONObject( lastBlock );
		String lbNumber = lb.getString("result");
		String lbTX = getLastBlockTransactions( lbNumber );
		JSONObject lbTXObj = new JSONObject( lbTX );
		JSONArray transactions = lbTXObj.getJSONObject("result").getJSONArray("transactions");
		for( int x=0; x < transactions.length(); x++ ) {
			JSONObject tx = transactions.getJSONObject( x );
			//String from = tx.getString("from");
			
			String to = "";
			try {
				to = tx.getString("to");
			} catch ( Exception e ) {
				//
			}
			
			if( to.equals("") ) {
				String txHash = tx.getString("hash");
				logger.info("Candidate TX: " + txHash);
				try {
					TransactionReceipt receipt = getTransactionReceipt( txHash );
					//System.out.println( new ObjectMapper().writeValueAsString( receipt ) );
					String tokenContract = receipt.getLogs().get(0).getAddress();
					logger.info("Token contract " + tokenContract );
					//System.out.println( tokenContract );
					getTokenData( tokenContract );
				} catch ( Exception e ) { 
					logger.info("Seems it not a token. Sorry.");
				}
			}
		}
		
	}
	
	private String getLastBlockTransactions( String blockNumber ) {
		return this.get("https://api.bscscan.com/api?module=proxy&action=eth_getBlockByNumber&tag=" + blockNumber + "&boolean=true&apikey=" + bscScanApiKey );
	}
	
	public void getTokenData( String address ) throws Exception {
		// https://api.bscscan.com/api?module=token&action=tokeninfo&contractaddress=0xc9849e6fdb743d08faee3e34dd2d1bc69ea11a51&apikey=YourApiKeyToken
		String pk = "0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130";
		Credentials credentials = Credentials.create(pk);
		ERC20 javaToken = ERC20.load(address, web3, credentials, new DefaultGasProvider());				
		String symbol = javaToken.symbol().send();
		String name = javaToken.name().send();
		//BigInteger decimal = javaToken.decimals().send();
		TokenInfo tf = new TokenInfo( address, name, symbol );
		sendToUser(tf);
		logger.info("  > [ " + symbol + " ]   " + name );
		this.tokens.add( tf );
	}

	/*
	public Transaction getTransactionByHash( String transactionHash ) throws Exception {
		return web3.ethGetTransactionByHash(transactionHash).send().getResult();
		//Optional<Transaction> otx =  web3.ethGetTransactionByHash(transactionHash).send().getTransaction();
		//if( otx.isPresent() ) {
		//	return otx.get();
		//}
		//return null;
	}
	*/
	
	public TransactionReceipt getTransactionReceipt( String txHash ) throws Exception {
		Optional<TransactionReceipt> transactionReceipt = 
			    web3.ethGetTransactionReceipt(txHash).send().getTransactionReceipt();
		if( transactionReceipt.isPresent() ) {
			return transactionReceipt.get();
		}
		return null;
	}
	
}
