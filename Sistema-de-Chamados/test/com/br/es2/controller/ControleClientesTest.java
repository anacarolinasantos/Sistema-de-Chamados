package com.br.es2.controller;

import com.br.es2.model.entities.ClienteEmpresa;
import com.br.es2.model.entities.Empresa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 31525962
 */
public class ControleClientesTest {
    
    public ControleClientes cc;
    
    public ControleClientesTest() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream("clientes.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            HashMap<Long, ClienteEmpresa> cache = new HashMap<>();

            long cpf = 7;
            
            cache.put(cpf, new ClienteEmpresa(1, new Empresa(100001, "Mackenzie"), cpf, "Paula", 37666570));

            oos.writeObject(cache);

            oos.flush();
            fos.flush();

            oos.close();
        }
    }
    
    @After
    public void tearDown() {
        File file = new File("clientes.dat");
        file.delete();
    }
    
    @Test
    public void testGetClienteDAO() {
        
    }
    
    @Test
    public void testIncluirNovoCliente() {
        
    }
    
    @Test
    public void testIncluirClienteJaCadastrado() {
        
    }
    
    @Test
    public void testValidarCPFComTamanhoCorreto() {
        
    }
    
    @Test
    public void testValidarCPFComTamanhoIncorreto() {
        
    }
}
