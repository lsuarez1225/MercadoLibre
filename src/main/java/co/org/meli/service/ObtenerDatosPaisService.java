package co.org.meli.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.org.meli.dto.CountryApiDto;
import co.org.meli.dto.FixerApiDto;
import co.org.meli.dto.PaisRespuestaDto;
import co.org.meli.exception.GeneralException;
import co.org.meli.service.impl.CountryApiService;
import co.org.meli.service.impl.FixerApiService;
import co.org.meli.util.Constants;

@Service
public class ObtenerDatosPaisService {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	CountryApiService countryApiService;
	FixerApiService fixerApiService;
	BloquearIpService bloquearIpService;
	
	public ObtenerDatosPaisService(BloquearIpService bloquearIpService) {
		this.bloquearIpService = bloquearIpService;
		countryApiService = new CountryApiService();
		fixerApiService = new FixerApiService();
	}
	
	public PaisRespuestaDto obtenerDatos(String ip) throws GeneralException {
		
		if (bloquearIpService.consultar(ip) > 0) {
			throw new GeneralException("IP bloqueada!");
		}
		
		PaisRespuestaDto respuesta = new PaisRespuestaDto();
		try {
		
			String urlApiCountry = Constants.API_IP_COUNTRY_URL.replace("{ip}", ip);
			ResponseEntity<String> apiCountryResponse = countryApiService.llamar(urlApiCountry);
			CountryApiDto countryApiResponseDto = gson.fromJson(apiCountryResponse.getBody(), CountryApiDto.class);
	
			respuesta.setNombrePais(countryApiResponseDto.getCountryName());
			respuesta.setMonedaLocal(countryApiResponseDto.getCurrency());
			respuesta.setCodigoISO(countryApiResponseDto.getCountryCode());
			
			String paramsApiFixer = Constants.API_FIXER_PARAMS.replace("{to}", Constants.MONEDA_CONVERSION_DESTINO)
					.replace("{from}", respuesta.getMonedaLocal())
					.replace("{amount}", "1");
			
			ResponseEntity<String> countryApiResponse = fixerApiService.llamar(paramsApiFixer);
			FixerApiDto fixerApiResponseDto = gson.fromJson(countryApiResponse.getBody(), FixerApiDto.class);
			respuesta.setCotizacionUSD(fixerApiResponseDto.getResult());
		}
		catch (RestClientException e) {
			throw new GeneralException("Error consumiendo API:" + e.getMessage());
		}
		
		return respuesta;
	}
}
