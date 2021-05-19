package br.com.cmabreu.services;

public class TokenInfo {
	private String hash;
	private String name;
	private String symbol;

	public TokenInfo(String hash, String name, String symbol) {
		super();
		this.hash = hash;
		this.name = name;
		this.symbol = symbol;
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
}
