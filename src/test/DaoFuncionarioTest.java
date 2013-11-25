package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.funcionario.Caixa;
import models.funcionario.Estoquista;
import models.funcionario.Funcionario;
import models.funcionario.Gerente;
import models.funcionario.Vendedor;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOFuncionario;

public class DaoFuncionarioTest {

	public DAOFuncionario daoFuncionario; 
	public Funcionario funcionario;
	public Funcionario funcionario2;
	
	@Before
	public void setUp(){
		daoFuncionario = new DAOFuncionario();
		funcionario = new Funcionario();
		funcionario2 = new Funcionario();
	}
	
	@Test
	public void testCadastroFuncionario() {
		funcionario.setNome("Felipe");
		funcionario.setRg("111111111");
		funcionario.setCpf("00000000000");
		funcionario.setDataNascimento("04/02/1991");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(10000);
		daoFuncionario.criarFuncionario(funcionario);
		assertTrue(daoFuncionario.checaRg("111111111"));
		assertEquals(funcionario.getNome(), daoFuncionario.buscarFuncionario("111111111").getNome());
		assertEquals(funcionario.getCargo(), daoFuncionario.buscarFuncionario("111111111").getCargo());
		assertEquals(funcionario.getCpf(), daoFuncionario.buscarFuncionario("111111111").getCpf());
		assertEquals(funcionario.getDataNascimento(), daoFuncionario.buscarFuncionario("111111111").getDataNascimento());
		assertEquals(funcionario.getRg(), daoFuncionario.buscarFuncionario("111111111").getRg());
		assertEquals(funcionario.getSalario(), daoFuncionario.buscarFuncionario("111111111").getSalario(), 0);
		
		daoFuncionario.apagarFuncionario("111111111");
		assertTrue(!daoFuncionario.checaRg("111111111"));
		
	}
	

	
	@Test
	public void testBuscarRg(){
		funcionario.setNome("Felipe");
		funcionario.setRg("111111111");
		funcionario.setCpf("00000000000");
		funcionario.setDataNascimento("04/02/1991");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(10000);
		daoFuncionario.criarFuncionario(funcionario);
		
		assertEquals(daoFuncionario.buscarRg(funcionario.getRg()), funcionario.getRg());
		
		daoFuncionario.apagarFuncionario("111111111");
		
		assertTrue(!daoFuncionario.checaRg("111111111"));
		assertEquals(daoFuncionario.buscarRg(funcionario.getRg()), null);
		
	}

	@Test
	public void testEditarFuncionario(){
		funcionario.setNome("Felipe");
		funcionario.setRg("111111111");
		funcionario.setCpf("00000000000");
		funcionario.setDataNascimento("04/02/1991");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(10000);
		
		daoFuncionario.criarFuncionario(funcionario);
		
		funcionario2.setNome("Felipe2");
		funcionario2.setRg("111111111");
		funcionario2.setCpf("00000000002");
		funcionario2.setDataNascimento("04/02/1992");
		funcionario2.setCargo("Gerente2");
		funcionario2.setSalario(10002);
		
		daoFuncionario.editarFuncionario(funcionario2);
		
		assertEquals(funcionario2.getNome(), daoFuncionario.buscarFuncionario("111111111").getNome());
		assertEquals(funcionario2.getCargo(), daoFuncionario.buscarFuncionario("111111111").getCargo());
		assertEquals(funcionario2.getCpf(), daoFuncionario.buscarFuncionario("111111111").getCpf());
		assertEquals(funcionario2.getDataNascimento(), daoFuncionario.buscarFuncionario("111111111").getDataNascimento());
		assertEquals(funcionario2.getRg(), daoFuncionario.buscarFuncionario("111111111").getRg());
		assertEquals(funcionario2.getSalario(), daoFuncionario.buscarFuncionario("111111111").getSalario(), 0);

		
		
		daoFuncionario.apagarFuncionario("111111111");
		assertTrue(!daoFuncionario.checaRg("111111111"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAtualizaSalario(){
		funcionario.setNome("Felipe");
		funcionario.setRg("111111111");
		funcionario.setCpf("00000000000");
		funcionario.setDataNascimento("04/02/1991");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(10000);
		
		daoFuncionario.criarFuncionario(funcionario);
		
		funcionario2.setNome("Felipe2");
		funcionario2.setRg("111111111");
		funcionario2.setCpf("00000000002");
		funcionario2.setDataNascimento("04/02/1992");
		funcionario2.setCargo("Gerente2");
		funcionario2.setSalario(10002);
		
		daoFuncionario.atualizarSalario(funcionario2);
		

		assertEquals(funcionario2.getSalario(), daoFuncionario.buscarSalario(funcionario), 0);
		
		daoFuncionario.apagarFuncionario("111111111");
		assertTrue(!daoFuncionario.checaRg("111111111"));
	}
	
	@Test
	public void testBuscarSalario(){
		funcionario.setNome("Felipe");
		funcionario.setRg("111111111");
		funcionario.setCpf("00000000000");
		funcionario.setDataNascimento("04/02/1991");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(10000);
		
		daoFuncionario.criarFuncionario(funcionario);
		
		assertEquals(daoFuncionario.buscarSalario(funcionario), funcionario.getSalario(), 0);
		
		daoFuncionario.apagarFuncionario("111111111");
		assertTrue(!daoFuncionario.checaRg("111111111"));
		assertEquals(daoFuncionario.buscarSalario(funcionario), -1, 0);
	}
	
	@Test
	public void testeListaFuncionarios(){
		funcionario.setNome("Felipe");
		funcionario.setRg("111111111");
		funcionario.setCpf("00000000000");
		funcionario.setDataNascimento("04/02/1991");
		funcionario.setCargo("Gerente");
		funcionario.setSalario(10000);
		
		daoFuncionario.criarFuncionario(funcionario);
		
		assertEquals(1, daoFuncionario.listarFuncionario().size());
		daoFuncionario.apagarFuncionario("111111111");
	}
	
	@Test
	public void testInstanciarFuncionarios(){
		daoFuncionario.apagarTodos();
		Caixa c = new Caixa();
		Estoquista e = new Estoquista();
		Gerente g = new Gerente();
		Vendedor v = new Vendedor();
		
		c.setCargo("caixa");
		c.setCpf("11111111111");
		c.setDataNascimento("data");
		c.setNome("caixa");
		c.setRg("111111111");
		c.setSalario(1500);
		daoFuncionario.criarFuncionario(c);
		
		e.setCargo("estoquista");
		e.setCpf("11111111112");
		e.setDataNascimento("data");
		e.setNome("estoquista");
		e.setRg("111111112");
		e.setSalario(1500);

		daoFuncionario.criarFuncionario(e);
		
		g.setCargo("gerente");
		g.setCpf("11111111113");
		g.setDataNascimento("data");
		g.setNome("gerente");
		g.setRg("111111113");
		g.setSalario(1500);
		daoFuncionario.criarFuncionario(g);
		 
		v.setCargo("vendedor");
		v.setCpf("11111111114");
		v.setDataNascimento("data");
		v.setNome("vendedor");
		v.setRg("111111114");
		v.setQuantidadeVendas(50);
		v.incrementarQtdVendas();
		assertEquals(51, v.getQuantidadeVendas());
		v.setSalario(1500);
		daoFuncionario.criarFuncionario(v);
		
		assertEquals(c.getRg(), daoFuncionario.buscarRg(c.getRg()));
		assertEquals(e.getRg(), daoFuncionario.buscarRg(e.getRg()));
		assertEquals(g.getRg(), daoFuncionario.buscarRg(g.getRg()));
		assertEquals(v.getRg(), daoFuncionario.buscarRg(v.getRg()));
		

		daoFuncionario.apagarTodos();
	}
	

	
}
