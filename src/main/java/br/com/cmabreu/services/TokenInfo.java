package br.com.cmabreu.services;

public class TokenInfo {
	private String hash;
	private String name;
	private String symbol;
	private Boolean isMine;

	public TokenInfo(String hash, String name, String symbol, boolean isMine) {
		super();
		this.hash = hash;
		this.name = name;
		this.symbol = symbol;
		this.isMine = isMine;
	}

	public String getHash() {
		return hash;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public Boolean getIsMine() {
		return isMine;
	}
	
}
