package co.org.meli.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.org.meli.service.LlamarApi;
import co.org.meli.util.Constants;

public class FixerApiService implements LlamarApi {

	@Override
	public ResponseEntity<String> llamar(String url) throws RestClientException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("apikey", Constants.API_FIXER_KEY);
		
		HttpEntity<String> requestEntity = new HttpEntity<>(url, headers);
		
		return new RestTemplate().exchange(Constants.API_FIXER_URL, HttpMethod.GET, requestEntity, String.class);
	}

}
