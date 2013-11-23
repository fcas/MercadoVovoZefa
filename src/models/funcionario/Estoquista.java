package models.funcionario;

public class Estoquista extends Funcionario {

	public Estoquista() {
		this.cargo = "";
		this.cpf = "";
		this.dataNascimento = "";
		this.nome = "";
		this.rg = "";
		this.salario = 0;
	}

	public void setNome(String nome) {
		super.setNome(nome);
	}

	public void setRg(String rg) {
		super.setRg(rg);
	}

	public void setCpf(String cpf) {
		super.setCpf(cpf);
	}

	public void setDataNascimento(String dataNascimento) {
		super.setDataNascimento(dataNascimento);
	}

	public void setCargo(String cargo) {
		super.setCargo(cargo);
	}

	public void setSalario(double salario) {
		super.setSalario(salario);
	}

	public double getSalario() {
		return 0;
	}

}
