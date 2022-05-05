package co.org.meli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import co.org.meli.controller.IPController;
import co.org.meli.exception.GeneralException;
import co.org.meli.util.ValidarFormatoIp;

@SpringBootTest
@AutoConfigureMockMvc
class MeliIpTestApplicationTests {
	
	@Autowired
	private IPController ipcontroller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		assertThat(ipcontroller).isNotNull();
	}
	
	@Test
	void validarFormatoIpsValido() {
		String[] ips = {"192.168.10.24", "192.168.100.240", "192.8.0.0", "0.0.0.0"};
		for (String ip : ips) {
			assertDoesNotThrow(() -> {
				new ValidarFormatoIp(ip);
			});
		}
	}
	
	@Test
	void validarFormatoIpInvalido() {
		String[] ips = {"1922.168.10.24", "192e.168.100.240", "192.8.0.0111", "00.0.0", "asdasd", "19259484", "1596.5454.5468.6587", "192a8.0.0"};
		for (String ip : ips) {
			assertThrows(GeneralException.class, () -> {
				new ValidarFormatoIp(ip);
			});			
		}
	}
	
	//@Test
	void validarControladorObtenerPais() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/pais/186.154.57.235")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("SI")));
	}

	//@Test
	void validarControladorAgregarListaNegra() throws Exception {
		this.mockMvc.perform(put("http://localhost:8080/blacklist/186.154.57.235")).andDo(print()).andExpect(status().isOk());
	}
}
