package br.com.cmabreu.services;

import java.math.BigInteger;
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
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@EnableScheduling
public class UserService {
	private Logger logger = LoggerFactory.getLogger( UserService.class );
	private Web3j web3;
	
	@Value("${bscscan.key}")
	private String bscScanApiKey;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;   	
	
	@Autowired
	private RESTService restService;

	public void sendToUser( Object payload) {
		simpMessagingTemplate.convertAndSend( "/tokens", payload );
	}
	
	@PostConstruct
	public void init() {
		try {
			web3 = Web3j.build( new HttpService( "https://bsc-dataseed.binance.org/" ) ); 
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
				
				try {
					Transaction txResp = getTransactionByHash( txHash );
					if( txResp != null ) {
						
						System.out.println( txResp.getFrom() );
						
						if( txResp.getCreates() != null ) {
							String tokenContract = txResp.getCreates();
							logger.info("New token in contract " + tokenContract );
							getTokenData( tokenContract );
						}
					} else {
						logger.error("Contract not found in TX " + txHash );
					}
					
				} catch ( Exception e ) {
					logger.error( e.getMessage() );
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
		
		logger.info("  > [ " + symbol + " ]   " + name );
	}
	
	public Transaction getTransactionByHash( String transactionHash ) throws Exception {
		Optional<Transaction> otx =  web3.ethGetTransactionByHash(transactionHash).send().getTransaction();
		if( otx.isPresent() ) {
			return otx.get();
		}
		return null;
	}
	
	
}
