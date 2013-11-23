package models.mercadoria;

public class Mercadoria implements IMercadoria {

	private double preco;

	private int ID;

	private String nome;

	private String cor;

	private String tamanho;

	private String produto;

	public Mercadoria() {
		this.preco = 0;
		this.ID = 0;
		this.nome = "";
		this.cor = "";
		this.tamanho = "";
		this.produto = "";
	}

	public double getPreco() {
		return preco;
	}

	public int getID() {
		return ID;
	}

	public String getNome() {
		return nome;
	}

	public String getCor() {
		return cor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public String getProduto() {
		return produto;
	}

	public void setPreco(double novoPreco) {
		this.preco = novoPreco;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void setCor(String novaCor) {
		this.cor = novaCor;
	}

	public void setTamanho(String novoTamanho) {
		this.tamanho = novoTamanho;
	}

	public void setProduto(String novoProduto) {
		this.produto = novoProduto;
	}

}
