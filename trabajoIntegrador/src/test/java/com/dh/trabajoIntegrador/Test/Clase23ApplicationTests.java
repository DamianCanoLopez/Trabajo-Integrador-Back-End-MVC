package com.dh.trabajoIntegrador.Test;
import com.dh.trabajoIntegrador.entity.Paciente;
import com.dh.trabajoIntegrador.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class Clase23ApplicationTests {
	@Autowired
	private PacienteService pacienteService;

	@Test
	public void buscarPacienteId1(){
		//PacienteService pacienteService=new PacienteService();
		Optional<Paciente> pacienteBuscado=pacienteService.buacarPaciente(1l);
		Assertions.assertEquals("Baspineiro",pacienteBuscado.get().getApellido());
	}
	@Test
	void contextLoads() {
	}

}
