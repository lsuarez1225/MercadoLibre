package co.org.meli.service;

import org.springframework.http.ResponseEntity;

public interface LlamarApi {

	public ResponseEntity<String> llamar(String url);
}
