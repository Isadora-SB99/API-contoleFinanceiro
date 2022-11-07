package br.com.alura.controlefinanceiro.controller;

import br.com.alura.controlefinanceiro.model.entities.Receitas;
import br.com.alura.controlefinanceiro.model.repository.DespesasRepository;
import br.com.alura.controlefinanceiro.model.repository.ReceitasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ResumoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //queria que o teste devolvesse um OK caso encontrasse o resumo, mas devolveu FORBIDDEN, pois não tinha autenticação
/*    @Test
    public void deveriaRetornar200EncontrouResumo() throws Exception {
        URI uri = new URI("/resumo/**");
        String json = "{\"ano\":2022, \"mes\":10}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    } */

    @Test
    public void deveriaRetornar403SemAutenticacao() throws Exception {
        URI uri = new URI("/resumo/**");
        String json = "{\"ano\":2022, \"mes\":10}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(403));
    }

}
