package models.venda;

public class Venda implements IVenda {

	private int ID;
	
	private double subtotal;

	private String vendedorRG;

	private String dataVenda;

	private int qtdVendas;

	public Venda() {
		this.ID = 0;
		this.subtotal = 0;
		this.vendedorRG = "";
		this.dataVenda = "";
		this.qtdVendas = 0;
	}

	public int getID() {
		return ID;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public String getVendedorRG() {
		return vendedorRG;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public int getQtdVendas() {
		return qtdVendas;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public void setVendedorRG(String vendedorRG) {
		this.vendedorRG = vendedorRG;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public void setQtdVendas(int novaQtdVendas) {
		qtdVendas = novaQtdVendas;
	}

}