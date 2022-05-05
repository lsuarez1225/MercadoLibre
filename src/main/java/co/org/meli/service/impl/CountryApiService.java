package co.org.meli.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.org.meli.service.LlamarApi;

public class CountryApiService implements LlamarApi {
	
	@Override
	public ResponseEntity<String> llamar(String url) throws RestClientException {
		return new RestTemplate().getForEntity(url, String.class);
	}

}
