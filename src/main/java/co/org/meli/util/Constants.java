package co.org.meli.util;

public class Constants {

	private Constants() {
		
	}
	
	public static final String IP_FORMAT = "^\\d{1,3}+\\.+\\d{1,3}+\\.+\\d{1,3}+\\.+\\d{1,3}$";
	public static final String API_IP_COUNTRY_URL = "https://api.ipfind.com/?ip={ip}";
	
	public static final String MONEDA_CONVERSION_DESTINO = "USD";
	
	public static final String API_FIXER_KEY = "yAHTJBIFia7IQe3fErhCeuQlwCQn37Hh";
	public static final String API_FIXER_URL = "https://api.apilayer.com/fixer/convert";
	public static final String API_FIXER_PARAMS = "to={to}&from={from}&amount={amount}";
}
