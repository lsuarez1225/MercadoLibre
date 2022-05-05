package co.org.meli.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.org.meli.dto.BlacklistRespuestaDto;
import co.org.meli.exception.GeneralException;
import co.org.meli.model.BlackList;
import co.org.meli.repository.BloquearIpRepository;

@Service
public class BloquearIpService {

	@Autowired
	BloquearIpRepository repository;
	
	public BlacklistRespuestaDto agregar(String ip) throws GeneralException {
		
		Date fecha = new Date(System.currentTimeMillis());
		
		BlackList blackList = new BlackList();
		blackList.setIp(ip);
		blackList.setFecha(fecha);
		
		BlackList blackListResultado = repository.saveAndFlush(blackList);
		if (Long.toString(blackListResultado.getId()).isEmpty()) {
			throw new GeneralException("Error al insertar datos en base de datos");
		}
		
		return new BlacklistRespuestaDto("Agregado con éxito!");
	}
	
	public long consultar(String ip) {

		BlackList blackList = new BlackList();
		blackList.setIp(ip);
		
		return repository.consultarIp(ip);
	}
}
