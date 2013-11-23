package models.mercadoria;

public interface IMercadoria {

	public double getPreco();

	public int getID();

	public String getNome();

	public String getCor();

	public String getTamanho();

	public String getProduto();

	public void setPreco(double novoPreco);

	public void setNome(String novoNome);

	public void setCor(String novaCor);

	public void setTamanho(String novoTamanho);

	public void setProduto(String novoProduto);

}
