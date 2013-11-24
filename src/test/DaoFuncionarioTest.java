package test;

import static org.junit.Assert.assertTrue;
import models.funcionario.Funcionario;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOFuncionario;

public class DaoFuncionarioTest {

	public DAOFuncionario daoFuncionario; 
	public Funcionario funcionario;
	
	@Before
	public void setUp(){
		daoFuncionario = new DAOFuncionario();
		funcionario = new Funcionario();
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
		daoFuncionario.apagarFuncionario("111111111");
		assertTrue(!daoFuncionario.checaRg("111111111"));
	}

}
