package test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import exceptions.OpcaoIlegalException;
import gui.Main;
import gui.MainArquivo;
import models.funcionario.IFuncionario;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

	public Main gui;
	public MainArquivo main;

	@Before
	public void setUp() {
		gui = new Main();
		try {
			main = new MainArquivo();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		main.executar();
	}

	@Test
	public void testCadastrarFuncionario() throws OpcaoIlegalException {
			
////		gui.setIn(stdin); // menu funcionario
//		gui.MenuPrincipal();
//		//gui.setOpcao(1); // cadastrat funcionario
//		String data = "1/n";
//		InputStream stdin = System.in;
//		  System.setIn(new ByteArrayInputStream(data.getBytes()));
//		  System.setIn(stdin);
//		  try {
//			gui.setOpcao(stdin.read());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		funcionario.setNome("Rodrigo");
//		funcionario.setRg("111111111");
//		funcionario.setCpf("00000000000");
//		funcionario.setDataNascimento("01/01/1992");
//		funcionario.setCargo("Gerente");
//		funcionario.setSalario(10000);
//		gui.setFuncionario(funcionario);
//		gui.CadastrarFuncionario();
//		gui.RemoverFuncionario();
//		gui.setRg("111111111");
//		assertTrue(gui.RemoverFuncionario());
	}
}
