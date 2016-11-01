package com.br.es2.model.entities;

import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class PessoaTest {

    @Test
    public void testCadastrarPessoa() {
        //cenário onde se cadastra um pessoa no sistema
    }

    @Test
    public void testEditarPessoa() {
        //cenário onde se edita os dados de uma pessoa do sistema
    }

    @Test(expected = NullPointerException.class)
    public void testVerificarNomeDaPessoaENulo() throws Exception {
        //cenário onde verifica se o nome da pessoa é nulo
    }

    @Test(expected = NullPointerException.class)
    public void testVerificarTelefoneDaPessoaENegativo() throws Exception {
        //cenário onde se verifica se o telefone da pessoa é negativo
    }
}
