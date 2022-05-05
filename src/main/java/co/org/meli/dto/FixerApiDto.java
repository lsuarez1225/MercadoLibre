package co.org.meli.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FixerApiDto {
	
	@SerializedName(value = "result")
	private String result;
	
	@SerializedName(value = "success")
	private boolean success;
}
