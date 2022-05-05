package co.org.meli.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PaisRespuestaDto {

	private String nombrePais;
	private String codigoISO;
	private String monedaLocal;
	private String cotizacionUSD;
}
