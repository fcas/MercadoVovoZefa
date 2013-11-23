package models.funcionario;

public interface IFuncionario {

	public String getNome();

	public String getRg();

	public String getCpf();

	public String getDataNascimento();

	public String getCargo();

	public double getSalario();

	public void setNome(String novoNome);

	public void setRg(String novoRg);

	public void setCpf(String novoCpf);

	public void setDataNascimento(String novaDataNascimento);

	public void setCargo(String novoCargo);

	public void setSalario(double novoSalario);

}
