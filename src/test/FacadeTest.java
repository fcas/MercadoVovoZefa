package test;



import static org.junit.Assert.*;

import java.util.ArrayList;

import models.funcionario.Funcionario;
import models.mercadoria.Mercadoria;
import models.venda.Venda;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOMercadoria;
import dao.concrete.DAOVendas;
import facade.Facade;


public class FacadeTest {

	public Facade facade; 
	public Venda venda;
	public Funcionario funcionario;
	public Mercadoria mercadoria;
	public DAOMercadoria daomercadoria;
	public DAOVendas daovendas;
	
	@Before
	public void setUp(){
		facade = new Facade(0);
		venda = new Venda();
		funcionario = new Funcionario();
		mercadoria = new Mercadoria();
		daomercadoria = new DAOMercadoria();
		daovendas = new DAOVendas();
	}
	
	@Test
	public void testCriaFuncionario(){
		funcionario.setCargo("cargo");
		funcionario.setCpf("11012548");
		funcionario.setDataNascimento("hoje");
		funcionario.setNome("dalay");
		funcionario.setRg("111111111");
		funcionario.setSalario(1000);
		
		facade.criarFuncionario(funcionario);
		assertEquals(1, facade.listarFuncionarios().size());
		facade.apagarFuncionario(funcionario);
		assertEquals(0, facade.listarFuncionarios().size());
		
	}
	
	@Test
	public void editarFuncionario(){
		funcionario.setCargo("cargo");
		funcionario.setCpf("11012548");
		funcionario.setDataNascimento("hoje");
		funcionario.setNome("dalay");
		funcionario.setRg("111111111");
		funcionario.setSalario(1000);
		
		Funcionario f2 = new Funcionario();
		f2.setNome("Felipe");
		f2.setCargo("cargo");
		f2.setCpf("11012548");
		f2.setDataNascimento("hoje");
		f2.setRg("111111111");
		f2.setSalario(1000);
		
		facade.criarFuncionario(funcionario);
		facade.editarFuncionario(f2);
		
		assertEquals(f2.getNome(), facade.buscarFuncionario("111111111").getNome());
		assertEquals(f2.getRg(), facade.buscarRg(f2.getRg()));

		facade.apagarFuncionario("111111111");

		assertEquals(0, facade.listarFuncionarios().size());
	}
	
	@Test
	public void testAtualizarSalario(){

		
		daomercadoria.apagarTudo();
		funcionario.setCargo("cargo");
		funcionario.setCpf("11012548");
		funcionario.setDataNascimento("hoje");
		funcionario.setNome("dalay");
		funcionario.setRg("111111111");
		funcionario.setSalario(1000);
		facade.criarFuncionario(funcionario);
		
		assertEquals(funcionario.getSalario(), facade.buscarSalario(funcionario), 0);
		
		Funcionario f2 = new Funcionario();
		f2.setNome("Felipe");
		f2.setCargo("cargo");
		f2.setCpf("11012548");
		f2.setDataNascimento("hoje");
		f2.setRg("111111111");
		f2.setSalario(250);
		
		facade.atualizarSalario(f2);
		
		assertEquals(f2.getSalario(), facade.buscarSalario(f2), 0);
		
		facade.apagarFuncionario("111111111");

		assertEquals(0, facade.listarFuncionarios().size());
		
	}
	
	@Test
	public void testCriarMercadoria(){

		
		daomercadoria.apagarTudo();
		mercadoria.setCor("roxo");
		mercadoria.setNome("mercadoria");
		mercadoria.setPreco(2.50);
		mercadoria.setProduto("produto");
		mercadoria.setTamanho("grande");
		
		facade.criarMercadoria(mercadoria);
		int id = daomercadoria.buscaIdPorNome("mercadoria");
		assertEquals(1, facade.listarMercadorias().size());
		
		facade.apagarMercadoria(id);
		

		assertEquals(0, facade.listarMercadorias().size());
		
	}
	
	@Test
	public void testApagarMercadoria(){

		daomercadoria.apagarTudo();
		mercadoria.setCor("roxo");
		mercadoria.setNome("mercadoria");
		mercadoria.setPreco(2.50);
		mercadoria.setProduto("produto");
		mercadoria.setTamanho("grande");
		
		facade.criarMercadoria(mercadoria);
		int id = daomercadoria.buscaIdPorNome("mercadoria");
		
		assertEquals(mercadoria.getProduto(), facade.buscarMercadoria(id).getProduto());
		
		facade.apagarMercadoria(mercadoria);
		daomercadoria.apagarTudo();
		assertEquals(-1, facade.buscarIdMercadoria(id));
	}
	
	@Test
	public void testEditarMercadoria(){
		daomercadoria.apagarTudo();
		mercadoria.setCor("roxo");
		mercadoria.setNome("mercadoria");
		mercadoria.setPreco(2.50);
		mercadoria.setProduto("produto");
		mercadoria.setTamanho("grande");
		
		facade.criarMercadoria(mercadoria);
		int id = daomercadoria.buscaIdPorNome("mercadoria");
		
		Mercadoria m2 = new Mercadoria();
		m2.setCor("preto");
		m2.setNome("mercadoria");
		m2.setPreco(2.50);
		m2.setProduto("produto");
		m2.setTamanho("grande");
		m2.setID(id);
		
		facade.editarMercadoria(m2);
		
		assertEquals(m2.getCor(), facade.buscarMercadoria(id).getCor());
		
	}
	
	@Test
	public void testCriarVenda(){
		daovendas.apagarTudo();
		venda.setDataVenda("hoje");
		venda.setQtdVendas(20);
		venda.setSubtotal(2.50);
		venda.setVendedorRG("111111111");
		
		facade.criarVenda(venda);
		
		assertEquals(1, facade.listarVendas().size());
		
		facade.apagarVenda(venda);

		daovendas.apagarTudo();

		assertEquals(0, facade.listarVendas().size());
		
	}
	
	@Test
	public void testEditarVenda(){
		daovendas.apagarTudo();
		venda.setDataVenda("hoje");
		venda.setQtdVendas(20);
		venda.setSubtotal(2.50);
		venda.setVendedorRG("111111111");
		
		
		facade.criarVenda(venda);
		int id = daovendas.buscarVendaPorNome("hoje");
		Venda v2 = new Venda();
		v2.setDataVenda("hoje");
		v2.setQtdVendas(30);
		v2.setSubtotal(2.50);
		v2.setVendedorRG("111111111");
		v2.setID(id);
		facade.editarVenda(v2);
		assertEquals(v2.getQtdVendas(), facade.buscarVenda(id).getQtdVendas());
		
		daovendas.apagarTudo();
		
		assertEquals(-1, facade.buscarIdMercadoria(id));
	}
	
	@Test
	public void TestApagarVenda(){
		daovendas.apagarTudo();
		venda.setDataVenda("hoje");
		venda.setQtdVendas(20);
		venda.setSubtotal(2.50);
		venda.setVendedorRG("111111111");
		
		
		facade.criarVenda(venda);
		int id = daovendas.buscarVendaPorNome("hoje");
		
		assertEquals(id, facade.buscarIdVenda(id));
		
		facade.apagarVenda(id);
		
		assertEquals(-1, facade.buscarIdVenda(id));
	}
	
	
	
}
	
