package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.foreign.ValueLayout;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll//verifica se esta tudo ok com banco virtual/memoria
    void start(){

        usuarioRepository.deleteAll();

        usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com","13465278" +
                "","https://i.imgur.com/FETvs20.jpg"));

        usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com","13465278" +
                "","https://i.imgur.com/FETvs20.jpg"));

        usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com","13465278" +
                "","https://i.imgur.com/FETvs20.jpg"));

        usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com","13465278" +
                "","https://i.imgur.com/FETvs20.jpg"));

    }


    @Test
    @DisplayName("Retorna 1 usuário")
    public void deveRetornarUmUsuario(){

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
        assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
    }

    @Test
    @DisplayName("Retorna 3 usuarios")
    public void deveRetornarTresUsuarios(){

        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
        assertEquals(3, listaDeUsuarios.size());
        assertEquals(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
        assertEquals(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
        assertEquals(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
    }

    @AfterAll
    public void end(){
        usuarioRepository.deleteAll();
    }









}
