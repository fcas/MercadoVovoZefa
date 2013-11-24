package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.venda.Venda;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOVendas;


public class DaoVendasTest {


	public DAOVendas daoVendas; 
	public Venda venda;
	
	@Before
	public void setUp(){
		daoVendas = new DAOVendas();
		venda = new Venda();
	}
	
	@Test
	public void testCadastrarVenda(){

		daoVendas.apagarTudo();
		venda.setDataVenda("hoje");
		venda.setQtdVendas(2);
		venda.setSubtotal(50);
		venda.setVendedorRG("111111111");
		
		daoVendas.criarVenda(venda);
		
		assertEquals(1, daoVendas.listarVendas().size());
		
	}
	
	@Test
	public void testeBuscarVenda(){

		daoVendas.apagarTudo();
		venda.setDataVenda("hoje");
		venda.setQtdVendas(2);
		venda.setSubtotal(50);
		venda.setVendedorRG("111111111");
		
		daoVendas.criarVenda(venda);
		int id = daoVendas.buscarVendaPorNome("hoje");
		System.out.println(id);
		
		assertEquals(venda.getQtdVendas(), daoVendas.buscarVenda(id).getQtdVendas());
		assertEquals(venda.getSubtotal(), daoVendas.buscarVenda(id).getSubtotal(), 0);
		assertEquals(venda.getVendedorRG(), daoVendas.buscarVenda(id).getVendedorRG());
		
		assertEquals(id, daoVendas.buscarIdVenda(id));
		daoVendas.apagarTudo();
		assertEquals(-1, daoVendas.buscarVendaPorNome("hoje"));
		assertEquals(-1, daoVendas.buscarIdVenda(id));
	}
	
	@Test
	public void testApagarVenda(){
		daoVendas.apagarTudo();
		venda.setDataVenda("hoje");
		venda.setQtdVendas(2);
		venda.setSubtotal(50);
		venda.setVendedorRG("111111111");
		
		daoVendas.criarVenda(venda);
		int id = daoVendas.buscarVendaPorNome("hoje");
		
		daoVendas.apagarVenda(id);
		
		assertEquals(0, daoVendas.listarVendas().size());
	}

	
}
