package com.br.es2.model.entities;

import org.junit.Test;

/**
 *
 * @author Bárbara
 */
public class TecnicoTest {

    @Test
    public void testCadastrarTecnico() {
        //cenário onde se cadastra um novo técnico
    }

    @Test
    public void testEditarTecnico() {
        //cenário onde se edita os dados do técnico
    }

    @Test(expected = Exception.class)
    public void testVerificarNomeDoTecnicoENulo() throws Exception {
        //cenário onde se verifica se o nome do técnico é nulo
    }

    @Test(expected = Exception.class)
    public void testVerificaTelefoneDoTecnicoENegativo() throws Exception {
        //cenário onde se verifica se o telefone do técnico é negativo
    }
}
