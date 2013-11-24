package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.funcionario.Funcionario;
import models.mercadoria.Mercadoria;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOMercadoria;

public class DaoMercadoriaTest {


	public DAOMercadoria daoMercadoria; 
	public Mercadoria mercadoria;
	
	@Before
	public void setUp(){
		daoMercadoria = new DAOMercadoria();
		mercadoria = new Mercadoria();
	}
	
	@Test
	public void testCadastroMercadoria(){
		mercadoria.setCor("azul");
		mercadoria.setNome("teclado");
		mercadoria.setPreco(2.50);
		mercadoria.setProduto("teclado importado");
		mercadoria.setTamanho("grande");
		daoMercadoria.criarMercadoria(mercadoria);
//		int id = daoMercadoria.buscaIdPorNome("teclado");
//		System.out.println(id);
		assertEquals(1, daoMercadoria.listarMercadorias().size());
		
		
		daoMercadoria.apagarMercadoria(daoMercadoria.buscaIdPorNome(mercadoria.getNome()));
		assertEquals(0, daoMercadoria.listarMercadorias().size());
		
		assertEquals(-1, daoMercadoria.buscaIdPorNome(mercadoria.getNome()));
		
		daoMercadoria.apagarTudo();
		
		
	}
	
	@Test
	public void testBuscarMercadoria(){
		mercadoria.setCor("azul");
		mercadoria.setNome("teclado");
		mercadoria.setPreco(2.50);
		mercadoria.setProduto("teclado importado");
		mercadoria.setTamanho("grande");
		daoMercadoria.criarMercadoria(mercadoria);
		
		int id = daoMercadoria.buscaIdPorNome("teclado");
		System.out.println(id);
		
		assertEquals(mercadoria.getCor(), daoMercadoria.buscarMercadoria_ID(id).getCor());
		assertEquals(mercadoria.getProduto(), daoMercadoria.buscarMercadoria_ID(id).getProduto());
		assertEquals(mercadoria.getPreco(), daoMercadoria.buscarMercadoria_ID(id).getPreco(), 0);
		assertEquals(mercadoria.getTamanho(), daoMercadoria.buscarMercadoria_ID(id).getTamanho());
		
		assertEquals(id, daoMercadoria.buscarIdMercadoria(id));
		daoMercadoria.apagarTudo();
		
		assertEquals(-1, daoMercadoria.buscarIdMercadoria(id));
	}
	
	public void testEditarMercadoria(){
		
	}
	

}