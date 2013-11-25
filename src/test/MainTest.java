package test;

import static org.junit.Assert.*;
import gui.Main;

import java.util.ArrayList;

import models.venda.Venda;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOVendas;

public class MainTest {

	public Main main; 
	public Venda venda;
	
	@Before
	public void setUp(){
		main = new Main();
		venda = new Venda();
	}
	
	@Test
	public void testCadastrarFuncionario(){
		
	}
}
