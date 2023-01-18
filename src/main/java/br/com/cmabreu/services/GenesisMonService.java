package br.com.cmabreu.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.util.json.JSONParser;
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

import br.com.cmabreu.tokens.MyToken;

@Service
@EnableScheduling
public class GenesisMonService {
	private Logger logger = LoggerFactory.getLogger( GenesisMonService.class );
	private Web3j web3;
	private List<TokenInfo> tokens;
	private final String bscScanApiKey = "PRGH4EEVRARQM68YX3IA4ZVBAYTADKHHQJ";
	private Calendar calendar = Calendar.getInstance();
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private final int CHECK_TX_INTERVAL = 5;
	
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
	
	
	// @Scheduled(fixedDelay = 60000 )
	private void save() {
		if ( this.tokens.size() == 0 ) return;
		try {
			JSONArray arr = new JSONArray(this.tokens);
	        //FileWriter file = new FileWriter("/data/" + UUID.randomUUID().toString().replace("-", "") + ".json" );
			FileWriter file = new FileWriter("/data/data.json" );
	        file.write( arr.toString() );
	        file.close();
		} catch ( Exception e) {
			//
		}
	}
	
	@PostConstruct
	public void init() {
		
		Date tenDaysBefore = DateUtils.addDays(new Date(), -10);
		calendar.setTime(tenDaysBefore);
		System.out.println("Iniciando em "+ dateFormat.format( calendar.getTime() ) );
		
		try {
			new File("/data").mkdirs();
			String endpoint = "https://bsc-mainnet.web3api.com/v1/38SCJC71VPWUVN7UB72FE7PW9UXMV6FHSE/";
			
			this.tokens = new ArrayList<TokenInfo>();
			web3 = Web3j.build( new HttpService( endpoint ) ); 
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			logger.info( clientVersion );
			
			if( new File("/data/data.json").exists() ) {
				try {
					JSONParser parser = new JSONParser( new FileReader("/data/data.json") );
					this.tokens.addAll( (List<TokenInfo>)parser.parse() );
				} catch ( Exception e) {
					e.printStackTrace();
				}
			}			
			
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private String getLastBlock() {
		//  BigInteger blockNum = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send()
	    //  	.getBlock().getNumber(); 
		return get( "https://api.bscscan.com/api?module=proxy&action=eth_blockNumber" );
	}
	
	private String getBlockInTime( String timestamp ) {
		// https://api.bscscan.com/api?module=block&action=getblocknobytime&timestamp=1601510400&closest=after&apikey=PRGH4EEVRARQM68YX3IA4ZVBAYTADKHHQJ
		return get( "https://api.bscscan.com/api?module=block&action=getblocknobytime&timestamp=" + timestamp + "&closest=after&apikey=" + bscScanApiKey );
		
		
		// https://api.bscscan.com/api?module=proxy&action=eth_getBlockByNumber&tag=946207&boolean=true&apikey=PRGH4EEVRARQM68YX3IA4ZVBAYTADKHHQJ
	}
	
	@Scheduled(fixedDelay = CHECK_TX_INTERVAL * 1000 )
	private void getTransactions() {
		String contractToSearch = "0x715d400f88c167884bbcc41c5fea407ed4d2f8a0";
		String methodSignatureToSearch = "0x4e71d92d"; // Claim
		
		
		calendar.add( Calendar.SECOND, CHECK_TX_INTERVAL );
		String nNow = String.valueOf( calendar.getTime().getTime()  ).substring(0, 10);
		String blockNumber = getBlockInTime(nNow);

		JSONObject lb = new JSONObject( blockNumber );
		String lbNumber = "0x" + Integer.toHexString( Integer.valueOf( lb.getString("result") ) );
		String lbTX = getLastBlockTransactionsContent( lbNumber );
		JSONObject lbTXObj = new JSONObject( lbTX );
		JSONArray transactions = lbTXObj.getJSONObject("result").getJSONArray("transactions");

		for( int x=0; x < transactions.length(); x++ ) {
			JSONObject tx = transactions.getJSONObject( x );
			//String from = "";
			//String to = "";
			String txHash = tx.getString("hash");
			
			try {
				String inputData = tx.getString("input");
				if( inputData.contains( methodSignatureToSearch) ) {
					saveTransaction(txHash, tx);
				}
			} catch ( Exception e ) {
				
			}
			
			/*
			try { from = tx.getString("from"); } catch ( Exception e ) { }
			try { to = tx.getString("to"); } catch ( Exception e ) {}
			System.out.println("Comp " + contractToSearch + " -> " + from );
			System.out.println("Comp " + contractToSearch + " -> " + to );
			if( from.equals( contractToSearch ) || to.equals( contractToSearch ) ) {
				saveTransaction(txHash, tx);
			}
			*/
			
		}
		
	}
	
	private void saveTransaction( String tx, JSONObject data ) {
		System.out.println("Gravando " + tx );
		try {
	        //FileWriter file = new FileWriter("/data/" + UUID.randomUUID().toString().replace("-", "") + ".json" );
			FileWriter file = new FileWriter("/data/" + tx + ".json" );
	        file.write( data.toString() );
	        file.close();
		} catch ( Exception e) {
			System.out.println("Erro " + e.getMessage() );
		}
	}
	
	
	private String get( String url ) {
		return restService.doRequestGet( url );		
	}
	
	// @Scheduled(fixedDelay = 5000 )
	private void checkBlock() {
		String lastBlock = getLastBlock();
		JSONObject lb = new JSONObject( lastBlock );
		String lbNumber = lb.getString("result");
		String lbTX = getLastBlockTransactionsContent( lbNumber );
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
	
	private String getLastBlockTransactionsContent( String blockNumber ) {
		return this.get("https://api.bscscan.com/api?module=proxy&action=eth_getBlockByNumber&tag=" + blockNumber + "&boolean=true&apikey=" + bscScanApiKey );
	}
	private String getLastBlockTransactionsHashes( String blockNumber ) {
		return this.get("https://api.bscscan.com/api?module=proxy&action=eth_getBlockByNumber&tag=" + blockNumber + "&boolean=false&apikey=" + bscScanApiKey );
	}
	
	/*
	public double similar( String addr ) {
		try {
			EthGetCode ethGetCode = web3.ethGetCode(addr, DefaultBlockParameterName.LATEST ).send();
			String theCode = ethGetCode.getCode().toString();
			SimilarityStrategy strategy = new JaroWinklerStrategy();
			StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
			return service.score(moonRatCode, theCode);  		
		} catch ( Exception e ) {
			//
		}
		return 0;
	}
	*/
	
	private boolean isMine( String address, Credentials credentials ) {
		try {
			MyToken token = MyToken.load(address, web3, credentials, new DefaultGasProvider() );
			BigInteger id = token.getContractId().send();
			System.out.println( id );
			return id == BigInteger.valueOf(86583620);
		} catch( Exception e) {
			//
		}
		return false;
	}
	
	public void getTokenData( String address ) throws Exception {
		String pk = "0x5bbbef76458bf30511c9ee6ed56783644eb339258d02656755c68098c4809130";
		Credentials credentials = Credentials.create(pk);
		ERC20 javaToken = ERC20.load(address, web3, credentials, new DefaultGasProvider());				
		String symbol = javaToken.symbol().send();
		String name = javaToken.name().send();
		//BigInteger decimal = javaToken.decimals().send();

		// System.out.println("Similaridade: " + address + "  " +  similar( address ) + "%");
		boolean im = isMine( address, credentials );
		
		TokenInfo tf = new TokenInfo( address, name, symbol, im );
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
