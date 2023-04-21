package com.example.construequiposmusk;

import com.example.construequiposmusk.exception.EquipoNoEncontradoException;
import com.example.construequiposmusk.model.Equipo;
import com.example.construequiposmusk.service.EquipoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EquipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipoService equipoService;

    @Test
    public void obtenerEquipo_conUnIdExistente_deberiaRetornarUnEquipo() throws Exception {
        Equipo equipo = new Equipo();
        equipo.setEquipoId(1L);
        equipo.setNombre("Equipo de prueba");
        equipo.setDisponible(true);

        when(equipoService.obtenerEquipo(1L)).thenReturn(equipo);
    }

    @Test
    public void obtenerEquipo_conUnIdNoExistente_deberiaRetornarUnError400() throws Exception {
        when(equipoService.obtenerEquipo(2L)).thenThrow(new EquipoNoEncontradoException("Equipo no encontrado"));

        mockMvc.perform(get("/equipos/2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Equipo no encontrado"));
    }
}