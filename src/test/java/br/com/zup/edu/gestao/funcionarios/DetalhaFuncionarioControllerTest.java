package br.com.zup.edu.gestao.funcionarios;

import base.SpringBootIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DetalhaFuncionarioControllerTest extends SpringBootIntegrationTest {

    @Autowired
    private FuncionarioRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void deveDetalharFuncionario() throws Exception {
        // cenário
        Funcionario funcionario = new Funcionario("Jordi",
                "994.300.560-26", Cargo.DESENVOLVEDOR, new BigDecimal("3.99"));
        repository.save(funcionario);

        // ação e validação
        mockMvc.perform(GET("/api/funcionarios/{id}", funcionario.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(funcionario.getId()))
                .andExpect(jsonPath("$.nome").value(funcionario.getNome()))
                .andExpect(jsonPath("$.cpf").value(funcionario.getCpf()))
                .andExpect(jsonPath("$.cargo").value(funcionario.getCargo().name()))
        ;
    }

    @Test
    public void naoDeveDetalharFuncionario_quandoNaoEncontrado() throws Exception {
        // cenário
        Funcionario funcionario = new Funcionario("Jordi",
                "994.300.560-26", Cargo.DESENVOLVEDOR, new BigDecimal("3.99"));
        repository.save(funcionario);

        // ação e validação
        mockMvc.perform(GET("/api/funcionarios/{id}", -9999))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("funcionário não encontrado"))
        ;
    }
}