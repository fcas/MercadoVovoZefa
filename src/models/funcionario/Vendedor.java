package models.funcionario;

public class Vendedor extends Funcionario {

	private int quantidadeVendas;

	public Vendedor() {
		this.cargo = "";
		this.cpf = "";
		this.dataNascimento = "";
		this.nome = "";
		this.rg = "";
		this.salario = 0;
		this.quantidadeVendas = 0;
	}

	public int getQuantidadeVendas() {
		return quantidadeVendas;
	}

	public void setQuantidadeVendas(int novaQuantidadeVendas) {
		this.quantidadeVendas = novaQuantidadeVendas;
	}

	public void incrementarQtdVendas() {
		quantidadeVendas++;
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

}
