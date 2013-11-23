package models.venda;

public interface IVenda {

	public int getID();

	public double getSubtotal();

	public String getVendedorRG();

	public String getDataVenda();

	public int getQtdVendas();

	public void setID(int iD);

	public void setSubtotal(double subtotal);

	public void setVendedorRG(String vendedorRG);

	public void setDataVenda(String dataVenda);

	public void setQtdVendas(int qtdVendas);

}