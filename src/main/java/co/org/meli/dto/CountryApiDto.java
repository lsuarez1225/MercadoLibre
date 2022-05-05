package co.org.meli.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CountryApiDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SerializedName(value = "ip_address")
	private String ipAddress;
	
	@SerializedName(value = "country")
	private String countryName;
	
	@SerializedName(value = "country_code")
	private String countryCode;
	
	@SerializedName(value = "currency")
	private String currency;
}
