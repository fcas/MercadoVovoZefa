package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.funcionario.Funcionario;
import models.funcionario.IFuncionario;
import models.mercadoria.IMercadoria;
import models.mercadoria.Mercadoria;
import models.venda.IVenda;
import models.venda.Venda;
import exceptions.OpcaoIlegalException;
import facade.Facade;

public class Main {

	private Facade facade;
	private Scanner in;
	private int opcao;
	private String RG;

	public Main() {
		facade = new Facade(0);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * PARTE RELACIONADA A FUNCIONARIO
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private void CadastrarFuncionario() {

		System.out.println("Carregando tela Cadastro Funcionario:");

		IFuncionario funcionario = new Funcionario();
		String buscaRg = null;

		// coleta dados

		boolean cadastrado = false;

		do {
			String rg = setRg();
			buscaRg = facade.buscarRg(rg);

			if (buscaRg == null || buscaRg.equals("")) {
				String nome = funcionarioNome();
				String cpf = setCPF();
				String dataNascimento = setData();
				String cargo = funcionarioCargo();
				double salario = funcionarioSalario();

				// seta o funcionario
				funcionario.setNome(nome);
				funcionario.setRg(rg);
				funcionario.setCpf(cpf);
				funcionario.setDataNascimento(dataNascimento);
				funcionario.setCargo(cargo);
				funcionario.setSalario(salario);
				
				try {
					// tenta adicionar ao banco
					System.out.println("Aguarde enquanto tentamos cadastrar.");
					facade.criarFuncionario(funcionario);
					cadastrado = true;		
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			} else {
				System.out.println("O Rg já existe no banco de dados");
			}
		} while (!cadastrado);
	}

	private void MenuFuncionarios() throws OpcaoIlegalException {

		opcao = -1;
		boolean voltar = false;

		in = new Scanner(System.in);

		while (!voltar) {
			System.out.println("~~~~~ Menu Funcionarios ~~~~~");
			System.out.println("O que gostaria de fazer?");
			System.out.println("0 - Voltar");
			System.out.println("1 - Cadastrar Novo Funcionario");
			System.out.println("2 - Atualizar Salario");
			System.out.println("3 - Calcular Bonificacao");
			System.out.println("4 - Remover Funcionario");
			System.out.println("5 - Listar Funcionarios");
			System.out.println("6 - Buscar Funcionario");
			System.out.println("7 - Editar funcionario");
			System.out.println("8 - Reajuste salarial");

			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				MenuPrincipal();
				break;
			case 1:
				CadastrarFuncionario();
				break;
			case 2:
				AtualizarSalario();
				break;
			case 3:
				System.out.println(CalcularBonificacao());
				break;
			case 4:
				RemoverFuncionario();
				break;
			case 5:
				ListarFuncionarios();
				break;
			case 6:
				BuscarFuncionario();
				break;
			case 7:
				EditarFuncionario();
				break;
			case 8:
				ReajusteSalarial();
				break;
			default:
				throw new OpcaoIlegalException();
			}
		}

		in.close();
	}

	private void ReajusteSalarial() {

		List listFuncionarios = new ArrayList();
		Funcionario aux;

		listFuncionarios = facade.listarFuncionarios();

		if (listFuncionarios.size() > 0) {

			for (int i = 0; i < listFuncionarios.size(); i++) {
				aux = (Funcionario) listFuncionarios.get(i);
				aux.setSalario(QuantificarReajuste(aux));
				facade.atualizarSalario(aux);
			}
		} else {
			System.out.println("Nao ha funcionarios cadastrados no sistema");
		}

	}

	public double QuantificarReajuste(IFuncionario funcionario) {
		double pCaixa = 0.05;
		double pEstoquista = 0.05;
		double pGerente = 0.09;
		double pVendedor = 0.07;
		double novoSalario = 0;

		if (funcionario.getCargo().equals("Caixa")) {
			novoSalario = funcionario.getSalario() + funcionario.getSalario()
					* pCaixa;
		} else if (funcionario.getCargo().equals("Estoquista")) {
			novoSalario = funcionario.getSalario() + funcionario.getSalario()
					* pEstoquista;
		} else if (funcionario.getCargo().equals("Gerente")) {
			novoSalario = funcionario.getSalario() + funcionario.getSalario()
					* pGerente;
		} else {
			novoSalario = funcionario.getSalario() + funcionario.getSalario()
					* pVendedor;
		}

		return novoSalario;
	}

	private void EditarFuncionario() {

		System.out.println("Carregando tela Editar Funcionario:");
		IFuncionario funcionario = new Funcionario();
		String buscaRg, nome = null, rg = null, cpf = null, cargo = null, dataNascimento = null;
		double salario = -1;
		boolean editado = false;

		do {
			System.out.println("Informe o rg do funcionario");
			rg = setRg();
			buscaRg = facade.buscarRg(rg);

			if (buscaRg != null) {
				// coleta dados
				System.out.println("Informe o novo nome");
				nome = funcionarioNome();
				System.out.println("Informe o novo rg");
				rg = setRg();
				System.out.println("Informe o novo cpf");
				cpf = setCPF();
				System.out.println("Informe a nova data de nascimento");
				dataNascimento = setData();
				System.out.println("Informe o novo cargo");
				cargo = funcionarioCargo();
				System.out.println("Informe o novo salário");
				salario = funcionarioSalario();
				editado = true;
			} else {
				System.out
						.println("Usuário não cadastrado no sistema, informa outro RG");
			}
		} while (!editado);

		// seta o funcionario
		funcionario.setNome(nome);
		funcionario.setRg(rg);
		funcionario.setCpf(cpf);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setCargo(cargo);
		funcionario.setSalario(salario);

		// tenta adicionar ao banco
		System.out.println("Aguarde enquanto editamos funcionario.");
		facade.editarFuncionario(funcionario);

	}

	private void BuscarFuncionario() {

		IFuncionario funcionario = new Funcionario();
		Funcionario aux;
		String buscaRg = null;
		boolean achouFuncionario = false;
		do {
			String rg = setRg();
			buscaRg = facade.buscarRg(rg);

			if (buscaRg != null) {
				// seta o funcionario
				funcionario.setRg(rg);

				aux = (Funcionario) facade.buscarFuncionario(rg);

				System.out
						.println("Nome|RG|CPF|DataNascimento|Cargo|Salario \n \n");

				System.out.print(aux.getNome() + "   ");
				System.out.print(aux.getRg() + "   ");
				System.out.print(aux.getCpf() + "   ");
				System.out.print(aux.getDataNascimento() + "   ");
				System.out.print(aux.getCargo() + "   ");
				System.out.print(aux.getSalario() + "\n");
			} else {
				System.out
						.println("Funcionario nao encontrado, tente outro RG");
			}
		} while (!achouFuncionario);
	}

	private void ListarFuncionarios() {

		List listFuncionarios = new ArrayList();
		Funcionario aux;

		listFuncionarios = facade.listarFuncionarios();

		if (listFuncionarios.size() > 0) {

			System.out
					.println("Nome|RG|CPF|DataNascimento|Cargo|Salario \n \n");

			for (int i = 0; i < listFuncionarios.size(); i++) {
				aux = (Funcionario) listFuncionarios.get(i);
				System.out.print(aux.getNome() + "   ");
				System.out.print(aux.getRg() + "   ");
				System.out.print(aux.getCpf() + "   ");
				System.out.print(aux.getDataNascimento() + "   ");
				System.out.print(aux.getCargo() + "   ");
				System.out.println(aux.getSalario() + "\n");
			}
		} else {
			System.out.println("Nao ha funcionarios cadastrados no sistema");
		}

	}

	private void RemoverFuncionario() {

		System.out.println("Carregando tela Remover Funcionario:");

		IFuncionario funcionario = new Funcionario();
		String buscaRg = null;
		boolean removido = false;

		do {
			// coleta dados
			String rg = setRg();
			buscaRg = facade.buscarRg(rg);

			if (buscaRg != null) {

				// seta o funcionario
				funcionario.setRg(rg);

				// tenta adicionar ao banco
				System.out
						.println("Aguarde enquanto removemos o usuario do sistema.");
				facade.apagarFuncionario(funcionario);
				removido = true;
			}
		} while (!removido);
	}

	private double CalcularBonificacao() {

		double salario;
		double bonificacao = -1;
		String buscaRg = null;
		boolean calculou = false;

		System.out.println("Carregando tela Calcular Bonificacao:");

		IFuncionario funcionario = new Funcionario();

		do {
			// coleta dados
			String rg = setRg();
			// seta o funcionario;
			funcionario.setRg(rg);
			buscaRg = facade.buscarRg(rg);

			if (buscaRg != null) {

				// tenta adicionar ao banco
				System.out
						.println("Aguarde enquanto tentamos calcular a bonificacao.");
				System.out.println("Quantidade de vendas:"
						+ facade.quantidadeVendas(funcionario));
				salario = facade.buscarSalario(funcionario);
				bonificacao = salario + salario
						* (0.01 * facade.quantidadeVendas(funcionario));
			} else {
				System.out.println("RG invalido");
			}
		} while (!calculou);

		return bonificacao;

	}

	private double CalcularReajuste(String rg) {

		double novoSalario = 0;

		IFuncionario funcionario = new Funcionario();

		funcionario = facade.buscarFuncionario(rg);
		if (funcionario != null) {
			novoSalario = QuantificarReajuste(funcionario);
		}
		return novoSalario;

	}

	private void AtualizarSalario() {

		System.out.println("Carregando tela Atualizar Salario:");

		IFuncionario funcionario = new Funcionario();
		double salarioAtual = -1;
		boolean aumento = false;
		boolean rgValido = false;

		// coleta dados
		do {
			String rg = setRg();
			String buscaRg = facade.buscarRg(rg);

			if (buscaRg == null || buscaRg.equals("")) {
				System.out
						.println("RG invalido. Digite o RG de um funcionario ja cadastrado.");
			} else {
				rgValido = true;
			}
		} while (!rgValido);

		salarioAtual = facade.buscarSalario(funcionario);

		do {
			double novoSalario = funcionarioSalario();
			if (novoSalario > salarioAtual) {
				funcionario.setSalario(novoSalario);
				System.out
						.println("Aguarde enquanto tentamos atualizar o salario.");
				facade.atualizarSalario(funcionario);
				aumento = true;
			} else {
				System.out.println("O novo salario deve ser maior que o atual");
			}
		} while (!aumento);

	}

	private String funcionarioNome() {

		String Nome = null;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite nome do funcionario:");
				Nome = in.next();
				if (!(Nome.equals(""))) {
					valido = true;
				}

				System.out.println("Nome = " + Nome + "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Nome confirmado.");

					return Nome;
				} else {
					break;
				}
			}
		}

		in.close();

		return Nome;
	}

	private String setRg() {
		RG = null;
		boolean confirma = false;
		boolean valido = false;

		String resposta;

		in = new Scanner(System.in);

		while (!confirma) { // enquanto o usuario nao confirmar
			valido = false;
			while (!valido) { // enquanto o RG nao for valido
				System.out
						.println("Digite RG do funcionario (nove digitos sem ponto):");
				RG = in.next();
				if (RG.length() == 9) {
					valido = true;
				}
			}

			System.out.println("RG = " + RG + "\nTem certeza? (s/n)");
			resposta = in.next();
			if (resposta.equals("s") || resposta.equals("S")) {
				confirma = true;
				return RG;
			} else {
				confirma = false;
			}
		}

		in.close();

		return RG;
	}

	private String setCPF() {
		String CPF = null;
		boolean confirma = false;
		boolean valido = false;
		String resposta;

		in = new Scanner(System.in);

		while (!confirma) { // enquanto o usuario nao confirmar
			valido = false;
			while (!valido) { // enquanto o CPF nao for valido
				System.out
						.println("Digite CPF do funcionario (onze digitos sem ponto e sem hifen):");
				CPF = in.next();
				if (CPF.length() == 11) {
					valido = true;
				}
			}

			System.out.println("CPF = " + CPF + "\nTem certeza? (s/n)");
			resposta = in.next();
			if (resposta.equals("s") || resposta.equals("S")) {
				confirma = true;
				return CPF;
			} else {
				confirma = false;
			}
		}

		in.close();

		return CPF;
	}

	private String setData() {
		String dataString = null;
		boolean confirma = false;
		boolean valido = false;

		String resposta;

		in = new Scanner(System.in);

		while (!confirma) { // enquanto o usuario nao confirmar
			valido = false;
			while (!valido) { // enquanto o CPF nao for valido
				System.out.println("Informe a data (DD/MM/AAAA)");
				dataString = in.next();
				if (dataString.length() == 10) { // se tem o tamanho certo
					int[] dataInt = quebraData(dataString); // quebra a linha
					if ((dataInt.length == 3) && (checaData(dataInt))) { // se
																			// esta
																			// no
																			// formato
																			// certo
																			// e
																			// eh
																			// uma
																			// data
																			// valida
						valido = true;
					}
				}
			}

			System.out.println("Data de Nascimento = " + dataString
					+ "\nTem certeza? (s/n)");
			resposta = in.next();
			if (resposta.equals("s") || resposta.equals("S")) {
				confirma = true;
				return dataString;
			} else {
				confirma = false;
			}
		}

		in.close();

		return dataString;
	}

	private int[] quebraData(String dataString) {
		String[] aux = dataString.split("/");
		int[] dataInt = new int[3];
		for (int i = 0; i < 3; i++) {
			dataInt[i] = Integer.parseInt(aux[i]);

		}
		return dataInt;
	}

	private boolean checaData(int[] dataInt) {
		int dia = dataInt[0];
		int mes = dataInt[1];
		int ano = dataInt[2];
		boolean bissexto = false;
		if (ano >= 1896 && ano <= 2020) { // se for um ano v�lido
			if (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) { // se
																			// for
																			// bissexto
				bissexto = true;
			}

			if (mes >= 1 && mes <= 12) { // se for um mes valido

				if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8
						|| mes == 10 || mes == 12) { // se for um mes com 31
														// dias
					if (dia >= 1 && dia <= 31) { // se tiver 31 dias
						return true;
					}
				} else

				if (mes == 4 || mes == 6 || mes == 9 || mes == 11) { // se for
																		// um
																		// mes
																		// com
																		// 30
																		// dias
					if (dia >= 1 && dia <= 30) { // se tiver ate 30 dias
						return true;
					}
				} else

				if (mes == 2) { // se for o mes 2
					if (bissexto) { // se for bissexto
						if (dia >= 1 && dia <= 29) { // se tem ate 29 dias
							return true;
						}
					} else { // se nao for bissexto
						if (dia >= 1 && dia <= 28) { // se tem ate 28 dias
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	private String funcionarioCargo() {
		String cargo = null;
		int opcao = 0;
		boolean confirma = false;
		boolean valido = false;
		String resposta;

		in = new Scanner(System.in);

		while (!confirma) { // enquanto o usuario nao confirmar
			valido = false;
			System.out.println("Selecione cargo do funcionario:");
			while (!valido) { // enquanto o Cargo nao for valido
				System.out.println("1-Caixa");
				System.out.println("2-Estoquista");
				System.out.println("3-Gerente");
				System.out.println("4-Vendedor");
				opcao = in.nextInt();
				switch (opcao) {
				case 1:
					cargo = "Caixa";
					valido = true;
					break;
				case 2:
					cargo = "Estoquista";
					valido = true;
					break;
				case 3:
					cargo = "Gerente";
					valido = true;
					break;
				case 4:
					cargo = "Vendedor";
					valido = true;
					break;
				default:
					System.out
							.println("Comando invalido.\nPor favor responda \"1\", \"2\", \"3\" ou \"4\".\n\n");
					valido = false;
					break;
				}
			}

			System.out.println("Cargo = " + cargo + "\nTem certeza? (s/n)");
			resposta = in.next();
			if (resposta.equals("s") || resposta.equals("S")) {
				confirma = true;
				return cargo;
			} else {
				confirma = false;
			}
		}

		in.close();

		return cargo;
	}

	private double funcionarioSalario() {

		double salario = -1;
		boolean confirma = false;
		boolean valido = false;
		String resposta;

		in = new Scanner(System.in);

		while (!confirma) { // enquanto o usuario nao confirmar
			valido = false;
			while (!valido) { // enquanto o Salario nao for valido
				System.out.println("Digite salario do funcionario:");
				salario = in.nextDouble();
				if (salario > 0) {
					valido = true;
				}
			}

			System.out.println("Salario = " + salario + "\nTem certeza? (s/n)");
			resposta = in.next();
			if (resposta.equals("s") || resposta.equals("S")) {
				confirma = true;
				return salario;
			} else {
				confirma = false;
			}
		}

		in.close();

		return salario;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * PARTE RELACIONADA A MERCADORIA
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private void MenuMercadoria() throws OpcaoIlegalException {

		opcao = -1;
		boolean voltar = false;

		in = new Scanner(System.in);

		while (!voltar) {
			System.out.println("~~~~~ Menu Mercadorias ~~~~~");
			System.out.println("O que gostaria de fazer?");
			System.out.println("0 - Voltar");
			System.out.println("1 - Cadastrar Nova Mercadoria");
			System.out.println("2 - Apagar mercadoria");
			System.out.println("3 - Buscar mercadoria");
			System.out.println("4 - Listar mercadorias");

			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				MenuPrincipal();
				break;
			case 1:
				CadastrarMercadoria();
				break;
			case 2:
				ApagarMercadoria();
				break;
			case 3:
				BuscarMercadoria();
				break;
			case 4:
				ListarMercadorias();
			default:
				throw new OpcaoIlegalException();
			}
		}

		in.close();
	}

	private void ListarMercadorias() {

		List listMercadorias = new ArrayList();
		Mercadoria aux;

		listMercadorias = facade.listarMercadorias();

		if (listMercadorias.size() > 0) {

			System.out.println("ID|Nome|Preco|Cor|Tamanho|Produto \n \n");

			for (int i = 0; i < listMercadorias.size(); i++) {
				aux = (Mercadoria) listMercadorias.get(i);
				System.out.print(aux.getID() + "   ");
				System.out.print(aux.getNome() + "   ");
				System.out.print(aux.getPreco() + "   ");
				System.out.print(aux.getCor() + "   ");
				System.out.print(aux.getTamanho() + "   ");
				System.out.println(aux.getProduto() + "\n");
			}
		} else {
			System.out.println("Nao ha mercadorias cadastradas");
		}

	}

	private void BuscarMercadoria() {

		Mercadoria aux;
		in = new Scanner(System.in);
		int buscarId = -1;
		boolean achou = false;

		do {
			System.out.println("Informe o id da mercadoria");
			int id = in.nextInt();
			buscarId = facade.buscarIdMercadoria(id);

			if (buscarId == id) {
				aux = (Mercadoria) facade.buscarMercadoria(id);
				System.out.println("ID|Nome|Preco|Cor|Tamanho|Produto \n");
				System.out.print(aux.getID() + "   ");
				System.out.print(aux.getNome() + "   ");
				System.out.print(aux.getPreco() + "   ");
				System.out.print(aux.getCor() + "   ");
				System.out.print(aux.getProduto() + "   ");
				achou = true;
			} else {
				System.out.println("Mercadoria nao cadastrada");
			}
		} while (!achou);

	}

	private void ApagarMercadoria() {

		System.out.println("Carregando tela Apagar mercadoria:");

		in = new Scanner(System.in);
		int buscarId = -1;
		boolean apagou = false;

		do {
			// coleta dados
			System.out.println("Informe o id da mercadoria");
			int id = in.nextInt();

			buscarId = facade.buscarIdMercadoria(id);

			if (buscarId == id) {
				// tenta apagar do banco
				System.out
						.println("Aguarde enquanto removemos a mercadoria do sistema.");
				facade.apagarMercadoria(id);
				apagou = true;
			} else {
				System.out.println("Mercadoria nao encontrada");
			}
		} while (!apagou);

	}

	private void CadastrarMercadoria() {

		System.out.println("Carregando tela Cadastro Mercadoria:");

		IMercadoria mercadoria = new Mercadoria();

		// coleta dados
		String nome = mercadoriaNome();
		double preco = mercadoriaPreco();
		String cor = mercadoriaCor();
		String tamanho = mercadoriaTamanho();
		String produto = mercadoriaProduto();

		// seta a mercadoria
		mercadoria.setNome(nome);
		mercadoria.setPreco(preco);
		mercadoria.setCor(cor);
		mercadoria.setTamanho(tamanho);
		mercadoria.setProduto(produto);

		// tenta adicionar ao banco
		System.out.println("Aguarde enquanto tentamos cadastrar.");
		facade.criarMercadoria(mercadoria);

	}

	private String mercadoriaProduto() {

		String produto = null;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite nome do produto:");
				produto = in.next();
				if (!(produto.equals(""))) {
					valido = true;
				}

				System.out.println("Produto = " + produto
						+ "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Nome confirmado.");
					return produto;
				} else {
					break;
				}
			}
		}

		in.close();

		return produto;
	}

	private String mercadoriaTamanho() {

		String tamanho = null;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite o tamanho da mercadoria:");
				tamanho = in.next();
				if ((tamanho.equalsIgnoreCase("P"))
						|| (tamanho.equalsIgnoreCase("M"))
						|| (tamanho.equalsIgnoreCase("G"))
						|| (tamanho.equalsIgnoreCase("GG"))) {
					valido = true;
				}

				System.out.println("Tamanho = " + tamanho
						+ "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Tamanho confirmado.");
					return tamanho;
				} else {
					break;
				}
			}
		}

		in.close();

		return tamanho;

	}

	private String mercadoriaCor() {

		String cor = null;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Informe a cor da mercadoria:");
				cor = in.next();
				if (!(cor.equals(""))) {
					valido = true;
				}

				System.out.println("Cor = " + cor + "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Cor confirmada.");
					return cor;
				} else {
					break;
				}
			}
		}

		in.close();

		return cor;
	}

	private double mercadoriaPreco() {

		double preco = -1.0;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite o preco da mercadoria:");
				preco = in.nextDouble();
				if (!(preco > 0.0)) {
					valido = true;
				}

				System.out.println("Preco = " + preco + "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Preco confirmado.");
					return preco;
				} else {
					break;
				}
			}
		}

		in.close();

		return preco;

	}

	private String mercadoriaNome() {

		String Nome = null;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite o nome da mercadoria:");
				Nome = in.next();
				if (!(Nome.equals(""))) {
					valido = true;
				}

				System.out.println("Nome = " + Nome + "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Nome confirmado.");
					return Nome;
				} else {
					break;
				}
			}
		}

		in.close();

		return Nome;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * PARTE RELACIONADA A VENDAS
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private void MenuVendas() throws OpcaoIlegalException {

		opcao = -1;
		boolean voltar = false;

		in = new Scanner(System.in);

		while (!voltar) {
			System.out.println("~~~~~ Menu Vendas ~~~~~");
			System.out.println("O que gostaria de fazer?");
			System.out.println("0 - Voltar");
			System.out.println("1 - Efetuar Venda");
			System.out.println("2 - Editar Venda");
			System.out.println("3 - Apagar Venda");
			System.out.println("4 - Buscar Venda");
			System.out.println("5 - Listar Vendas");

			opcao = in.nextInt();
			switch (opcao) {
			case 0:
				MenuPrincipal();
				break;
			case 1:
				CadastrarVenda();
				break;
			case 2:
				EditarVenda();
				break;
			case 3:
				ApagarVenda();
				break;
			case 4:
				BuscarVenda();
				break;
			case 5:
				ListarVendas();
				break;
			default:
				throw new OpcaoIlegalException();
			}
		}

		in.close();
	}

	private void ListarVendas() {

		List listVendas = new ArrayList();
		Venda aux;

		listVendas = facade.listarVendas();

		if (listVendas.size() > 0) {
			System.out
					.println("ID|Subtotal|VendedorRg|DataVenda|Qtd Vendas \n \n");

			for (int i = 0; i < listVendas.size(); i++) {
				aux = (Venda) listVendas.get(i);
				System.out.print(aux.getID() + "   ");
				System.out.print(aux.getSubtotal() + "   ");
				System.out.print(aux.getVendedorRG() + "   ");
				System.out.print(aux.getDataVenda() + "   ");
				System.out.print(aux.getQtdVendas() + "   " + "\n");
			}
		} else {
			System.out.println("Nao ha mercadorias cadastradas");
		}

	}

	private void BuscarVenda() {

		Venda aux;
		int buscarId = -1;
		int id;
		boolean idValido = false;

		do {
			in = new Scanner(System.in);
			System.out.println("Informe o id da venda");
			id = in.nextInt();
			buscarId = facade.buscarIdVenda(id);
			if (id == buscarId) {
				aux = (Venda) facade.buscarVenda(id);
				System.out
						.println("ID|Subtotal|VendedorRg|DataVenda|Qtd Vendas \n \n");
				System.out.print(aux.getID() + "   ");
				System.out.print(aux.getSubtotal() + "   ");
				System.out.print(aux.getVendedorRG() + "   ");
				System.out.print(aux.getDataVenda() + "   ");
				System.out.print(aux.getQtdVendas() + "   ");
				idValido = true;
			} else {
				System.out.println("Id inválido");
			}
		} while (!idValido);
	}

	private void ApagarVenda() {

		System.out.println("Carregando tela Remover Venda:");

		boolean removida = false;
		int id, buscarId = -1;

		do {
			in = new Scanner(System.in);
			System.out.println("Informe o id da mercadoria a ser removida");
			id = in.nextInt();

			buscarId = facade.buscarIdVenda(id);

			if (buscarId == id) {
				// coleta dados
				// seta o funcionario
				// tenta adicionar ao banco
				System.out
						.println("Aguarde enquanto removemos o usuario do sistema.");
				facade.apagarVenda(buscarId);
				removida = true;
			} else {
				System.out.println("Id não encontrado");
			}
		} while (!removida);

	}

	private void EditarVenda() {

		System.out.println("Carregando tela Editar Venda:");
		IVenda venda = new Venda();
		int buscarId, id, qtdVendas = -1;
		String dataVenda = null, vendedorRg = null;
		double subtotal = -1;
		boolean editada = false;

		in = new Scanner(System.in);

		do {
			id = in.nextInt();
			buscarId = facade.buscarIdVenda(id);

			if (buscarId == id) {
				// coleta dados
				System.out.println("Informe o novo subtotal");
				subtotal = mercadoriaSubtotal();
				System.out.println("Informe o RG do funcionario");
				vendedorRg = setRg();
				System.out.println("Informe a data da venda");
				dataVenda = setData();
				System.out.println("Informe a quantidade da venda");
				qtdVendas = mercadoriaQtdVenda();

				// seta a venda
				venda.setID(buscarId);
				venda.setSubtotal(subtotal);
				venda.setVendedorRG(vendedorRg);
				venda.setDataVenda(dataVenda);
				venda.setQtdVendas(qtdVendas);

				// tenta editar
				System.out.println("Aguarde enquanto editamos a venda.");

				facade.editarVenda(venda);
				editada = true;
			} else {
				System.out.println("Venda nao cadastrada no sistema");
			}
		} while (!editada);

	}

	private void CadastrarVenda() {

		System.out.println("Carregando tela Cadastro Venda:");

		IVenda venda = new Venda();

		// coleta dados
		double subtotal = mercadoriaSubtotal();
		String vendedorRg = setRg();
		String dataVenda = setData();
		int qtdVenda = mercadoriaQtdVenda();

		// seta a mercadoria
		venda.setSubtotal(subtotal);
		venda.setVendedorRG(vendedorRg);
		venda.setDataVenda(dataVenda);
		venda.setQtdVendas(qtdVenda);

		// tenta adicionar ao banco
		System.out.println("Aguarde enquanto tentamos cadastrar.");
		facade.criarVenda(venda);

	}

	private int mercadoriaQtdVenda() {
		int qtdVenda = -1;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite a quantidade da venda:");
				qtdVenda = in.nextInt();
				if (qtdVenda > 0) {
					valido = true;
				}

				System.out.println("Quantidade = " + qtdVenda
						+ "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Quantidade confirmada.");
					return qtdVenda;
				} else {
					break;
				}
			}
		}

		in.close();

		return qtdVenda;

	}

	private double mercadoriaSubtotal() {

		double subtotal = -1.0;
		boolean confirma = false;
		boolean valido = false;

		in = new Scanner(System.in);

		String resposta;

		while (!confirma) {
			valido = false;
			while (!valido) {
				System.out.println("Digite o subtotal:");
				subtotal = in.nextDouble();
				if (subtotal > 0.0) {
					valido = true;
				}

				System.out.println("Nome = " + subtotal
						+ "\nTem certeza? (s/n)");
				resposta = in.next();

				if (resposta.equalsIgnoreCase("s")) {
					confirma = true;
					System.out.println("Subtotal confirmado.");
					return subtotal;
				} else {
					break;
				}
			}
		}

		in.close();

		return subtotal;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * PARTE RELACIONADA AO MENU PRINCIPAL
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private void MenuPrincipal() throws OpcaoIlegalException {
		opcao = -1;
		boolean sair = false;
		in = new Scanner(System.in);

		while (!sair) {
			System.out.println("~~~~~ Menu Principal ~~~~~");
			System.out
					.println("Digite o numero correspondente a opcao desejada.");
			System.out.println("0- Sair do Programa");
			System.out.println("1- Menu Funcionarios");
			System.out.println("2- Menu Mercadorias");
			System.out.println("3- Menu Vendas");
			opcao = in.nextInt();
			switch (opcao) {

			case 0:
				sair = true;
				System.out.println("At� a pr�xima ;)");
				break;
			case 1: // funcionario
				boolean capturouFuncionario = false;
				do {
					try {
						capturouFuncionario = false;
						MenuFuncionarios();
					} catch (OpcaoIlegalException e) {
						capturouFuncionario = true;
						System.out.println("Opcao Invalida.");

					}
				} while (capturouFuncionario);
				break;
			case 2: // mercadoria
				boolean capturouMercadoria = false;
				do {
					try {
						capturouMercadoria = false;
						MenuMercadoria();
					} catch (OpcaoIlegalException e) {
						capturouMercadoria = true;
						System.out.println("Opcao Invalida.");

					}
				} while (capturouMercadoria);

				break;
			case 3: // venda
				boolean capturouVenda = false;
				do {
					try {
						capturouVenda = false;
						MenuVendas();
					} catch (OpcaoIlegalException e) {
						capturouVenda = true;
						System.out.println("Opcao Invalida.");

					}
				} while (capturouVenda);
				break;
			default:
				throw new OpcaoIlegalException();
			}
		}
		in.close();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*
	 * METODO MAIN
	 */
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static void main(String[] args) {
		System.out.println("iniciando...");
		Scanner local = new Scanner(System.in);
		Main main = new Main();
		boolean capturouPrincipal;

		do { // continua tentando rodar o menu enquanto alguma excecao for
				// capturada
			try {
				capturouPrincipal = false;
				main.MenuPrincipal();
			} catch (OpcaoIlegalException e) {
				capturouPrincipal = true;
				System.out.println("Opcao Invalida.");

			}
		} while (capturouPrincipal);

		local.close();

	}

	public int getOpcao() {
		return opcao;
	}

	public void setOpcao(int opcao) {
		this.opcao = opcao;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

}
