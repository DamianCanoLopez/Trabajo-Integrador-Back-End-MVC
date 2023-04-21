package com.dh.trabajoIntegrador.Test;

import com.dh.trabajoIntegrador.dto.TurnoDTO;
import com.dh.trabajoIntegrador.entity.Domicilio;
import com.dh.trabajoIntegrador.entity.Odontologo;
import com.dh.trabajoIntegrador.entity.Paciente;
import com.dh.trabajoIntegrador.service.OdontologoService;
import com.dh.trabajoIntegrador.service.PacienteService;
import com.dh.trabajoIntegrador.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    private void cargarDatos(){
        Paciente pacienteAgregado=pacienteService
                .guardarPaciente(new Paciente("Rodolfo","Baspineiro","4458",
                        LocalDate.of(2022,12,7),
                        new Domicilio("Calle a",514,"Localidad a","Provincia a"),"prueba@gmail.com"));
        Odontologo odontologoAgregado=odontologoService
                .guardar(new Odontologo("MP56418","Ezequiel","Baspineiro"));
        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2022,12,8));
        turnoDTO.setOdontologoId(odontologoAgregado.getId());
        turnoDTO.setPacienteId(pacienteAgregado.getId());
        turnoService.guardarTurno(turnoDTO);
    }
    @Test
    public void listarTurnosTest() throws Exception {
        cargarDatos();
        MvcResult respuesta=mockMvc.perform
                (MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
