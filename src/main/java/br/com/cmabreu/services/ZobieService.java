package br.com.cmabreu.services;

import java.io.File;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

@Service
public class ZobieService {
	private Web3j web3;
	
	@PostConstruct
	public void init() {
		//web3 = Web3j.build(new HttpService("https://data-seed-prebsc-1-s1.binance.org:8545"));
	
		try {
			//createWallet( "antares2" );
			//balance("0xcD44Ed01B57a1Fc8Ef883be47ec57F3FF2e47417");
		} catch( Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void createWallet( String password ) throws Exception {
		
		
		Bip39Wallet wallet = WalletUtils.generateBip39Wallet(password, new File("/wallets") );
		Credentials credentials = WalletUtils.loadBip39Credentials( password, wallet.getMnemonic() );	

		ECKeyPair privateKey = credentials.getEcKeyPair();

		System.out.println("Your New Account : " + credentials.getAddress() );
		System.out.println("Mneminic Code: " + wallet.getMnemonic() );		
		
		System.out.println("Private Key: " + privateKey.getPrivateKey().toString(16) );
		System.out.println("Public Key : " + privateKey.getPublicKey().toString(16) );		
	}

	
	public void transfer( Credentials credentials, String toAddress, double amount) throws Exception {
		TransactionReceipt transactionReceipt = Transfer.sendFunds( web3, credentials, toAddress, BigDecimal.valueOf( amount ), Convert.Unit.ETHER).send();
		System.out.println( transactionReceipt.getTransactionHash() );
	}
	
	public void balance(String address) throws Exception {
		EthGetBalance ethGetBalance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
		System.out.println("Balance BNB    : " + Convert.fromWei( ethGetBalance.getBalance().toString() , Unit.ETHER ) );		
	}
	
}
