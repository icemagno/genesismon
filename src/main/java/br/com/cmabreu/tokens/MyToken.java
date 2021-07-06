package br.com.cmabreu.tokens;

import java.math.BigInteger;
import java.util.Arrays;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

public class MyToken extends Contract {
	private static String binary = "608060405234801561001057600080fd5b5060b98061001f6000396000f3fe6080604052348015600f57600080fd5b506004361060285760003560e01c80638291286c14602d575b600080fd5b60336047565b604051603e91906060565b60405180910390f35b60006305292944905090565b605a816079565b82525050565b6000602082019050607360008301846053565b92915050565b600081905091905056fea2646970667358221220ae3488e81c7d486cb42b35840ba735619696f40a2b68d3cb3278b26a55fc5d5a64736f6c63430008060033";
    
    @Deprecated
    public static MyToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MyToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MyToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MyToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MyToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MyToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }	
	
	@Deprecated
    protected MyToken(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        super( binary, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MyToken( 
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider) {
        super(binary, contractAddress, web3j, credentials, gasProvider);
    }

    @Deprecated
    protected MyToken(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            BigInteger gasPrice,
            BigInteger gasLimit) {
        super(binary, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MyToken(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            ContractGasProvider gasProvider) {
        super(binary, contractAddress, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<BigInteger> getContractId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function("contractId", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));        
        return executeRemoteCallSingleValueReturn( function, BigInteger.class );
    }	

    
}
