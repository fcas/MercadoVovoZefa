package models.funcionario;

public class Funcionario implements IFuncionario {

	protected String nome;

	protected String rg;

	protected String cpf;

	protected String dataNascimento;

	protected String cargo;

	protected double salario;

	public Funcionario() {
		this.cargo = "";
		this.cpf = "";
		this.dataNascimento = "";
		this.nome = "";
		this.rg = "";
		this.salario = 0;
	}

	public String getNome() {
		return nome;
	}

	public String getRg() {
		return rg;
	}

	public String getCpf() {
		return cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getCargo() {
		return cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setSalario(double novoSalario) {
		this.salario = novoSalario;
	}

}
