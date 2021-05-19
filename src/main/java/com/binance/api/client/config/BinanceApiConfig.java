package com.binance.api.client.config;

/**
 * Configuration used for Binance operations.
 * For testnet check https://testnet.binance.vision
 */
public class BinanceApiConfig {

	private static String REST_API_BASE_URL = "https://api.binance.com";
	private static String STREAM_API_BASE_URL = "wss://stream.binance.com:9443/ws";
	private static String ASSET_INFO_API_BASE_URL = "https://binance.com/";

	/**
	 * REST API base URL.
	 */
	public static String getApiBaseUrl() {
		return REST_API_BASE_URL;
	}

	public static void setRestApiBaseUrl(String restApiBaseUrl) {
		REST_API_BASE_URL = restApiBaseUrl;
	}

	/**
	 * Streaming API base URL.
	 */
	public static String getStreamApiBaseUrl() {
		return STREAM_API_BASE_URL;
	}

	public static void setStreamApiBaseUrl(String streamApiBaseUrl) {
		STREAM_API_BASE_URL = streamApiBaseUrl;
	}

	/**
	 * Asset info base URL.
	 */
	public static String getAssetInfoApiBaseUrl() {
		return ASSET_INFO_API_BASE_URL;
	}

	public static void setAssetInfoApiBaseUrl(String assetInfoApiBaseUrl) {
		ASSET_INFO_API_BASE_URL = assetInfoApiBaseUrl;
	}
}