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
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@EnableScheduling
public class GenesisMonService {
	private Logger logger = LoggerFactory.getLogger( GenesisMonService.class );
	private Web3j web3;
	private List<TokenInfo> tokens;
	private final String bscScanApiKey = "PRGH4EEVRARQM68YX3IA4ZVBAYTADKHHQJ";
	
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
	
	@Scheduled(fixedDelay = 5000 )
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
				//logger.info("Candidate TX: " + txHash);
				try {
					TransactionReceipt receipt = getTransactionReceipt( txHash );
					//System.out.println( new ObjectMapper().writeValueAsString( receipt ) );
					String tokenContract = receipt.getLogs().get(0).getAddress();
					//logger.info("Token contract " + tokenContract );
					//System.out.println( tokenContract );
					getTokenData( tokenContract );
				} catch ( Exception e ) { 
					// logger.info("Seems it not a token. Sorry.");
				}
			}
		}
		
	}
	
	private String getLastBlockTransactions( String blockNumber ) {
		return this.get("https://api.bscscan.com/api?module=proxy&action=eth_getBlockByNumber&tag=" + blockNumber + "&boolean=true&apikey=" + bscScanApiKey );
	}
	
	public void getTokenData( String address ) throws Exception {
		String pk = "0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130";
		Credentials credentials = Credentials.create(pk);
		ERC20 javaToken = ERC20.load(address, web3, credentials, new DefaultGasProvider());				
		String symbol = javaToken.symbol().send();
		String name = javaToken.name().send();
		//BigInteger decimal = javaToken.decimals().send();
		TokenInfo tf = new TokenInfo( address, name, symbol );
		sendToUser(tf);
		// logger.info("  > [ " + symbol + " ]   " + name );
		this.tokens.add( tf );
	}

	public TransactionReceipt getTransactionReceipt( String txHash ) throws Exception {
		Optional<TransactionReceipt> transactionReceipt = 
			    web3.ethGetTransactionReceipt(txHash).send().getTransactionReceipt();
		if( transactionReceipt.isPresent() ) {
			return transactionReceipt.get();
		}
		return null;
	}
	
}
