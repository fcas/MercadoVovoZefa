package dao.interfaces;

import java.util.List;

import models.venda.IVenda;

public interface IDaoVendas {

	public void criarVenda(IVenda venda);

	public void editarVenda(IVenda venda);

	public void apagarVenda(int id);

	public List listarVendas();

	public IVenda buscarVenda(int ID);

	public int buscarIdVenda(int id);

}
