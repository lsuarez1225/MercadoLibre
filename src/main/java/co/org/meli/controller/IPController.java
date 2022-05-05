package co.org.meli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import co.org.meli.exception.GeneralException;
import co.org.meli.service.BloquearIpService;
import co.org.meli.service.ObtenerDatosPaisService;
import co.org.meli.util.ValidarFormatoIp;

@Controller
public class IPController {
	
	ObtenerDatosPaisService obtenerDatosPaisService;
	BloquearIpService bloquearIpService;
	
	public IPController(ObtenerDatosPaisService obtenerDatosPaisService, BloquearIpService bloquearIpService) {
		this.bloquearIpService = bloquearIpService;
		this.obtenerDatosPaisService = obtenerDatosPaisService;
	}
	
	@GetMapping(value = "/pais/{ip}")
	public ResponseEntity<Object> obtenerPais(@PathVariable("ip") String ip) {
		try {
			new ValidarFormatoIp(ip);
			return ResponseEntity.ok(obtenerDatosPaisService.obtenerDatos(ip));
		}
		catch (GeneralException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/blacklist/{ip}")
	public ResponseEntity<Object> bloquearIP(@PathVariable("ip") String ip) {
		try {
			new ValidarFormatoIp(ip);
			return ResponseEntity.ok(bloquearIpService.agregar(ip));
		}
		catch (GeneralException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
