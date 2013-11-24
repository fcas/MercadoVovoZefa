package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.funcionario.Funcionario;

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
	
}
