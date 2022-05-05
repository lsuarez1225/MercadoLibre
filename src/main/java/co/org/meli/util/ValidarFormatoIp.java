package co.org.meli.util;

import java.util.regex.Pattern;

import co.org.meli.exception.GeneralException;
import lombok.Getter;

@Getter
public class ValidarFormatoIp {
	
	public ValidarFormatoIp(String ip) throws GeneralException {
		
		Pattern pattern = Pattern.compile(Constants.IP_FORMAT); 
		
		if (!pattern.matcher(ip).find()) {
			throw new GeneralException("Formato de ip inválido");
		}
	}
}
