package com.performance_go.CRUD_Farmacia.controller;

import com.performance_go.CRUD_Farmacia.model.Categoria;
import com.performance_go.CRUD_Farmacia.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @BeforeAll
    void start(){
        categoriaRepository.deleteAll();
    }
    @Test
    @DisplayName("Cadastrar Uma Categoria")
    public void deveCriarUmaCategoria() {

        HttpEntity<Categoria> corpoRequisicao = new HttpEntity<>(new Categoria(0l, "Categoria", "descrição"));

        ResponseEntity<Categoria> corpoCategoria = testRestTemplate.exchange("/categorias", HttpMethod.POST, corpoRequisicao, Categoria.class);

        assertEquals(HttpStatus.CREATED, corpoCategoria.getStatusCode());
    }

    @Test
    @DisplayName("Não deve permitir duplicação do Usuário")
    public void naoDeveDuplicarUsuario(){

        Categoria categoria = new Categoria(0l, "Categoria", "Descrição");

        HttpEntity<Categoria> corpoRequisicao = new HttpEntity<>(new Categoria(0l, "Categoria", "descrição"));

        ResponseEntity<Categoria> corpoResposta = testRestTemplate.exchange("/categorias", HttpMethod.POST, corpoRequisicao, Categoria.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());

    }
}
