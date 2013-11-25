package dao.interfaces;

import java.util.List;

import models.mercadoria.IMercadoria;

public interface IDaoMercadoria {

	public void criarMercadoria(IMercadoria mercadoria);

	public void editarMercadoria(IMercadoria mercadoria);

	public void apagarMercadoria(int ID);

	public IMercadoria buscarMercadoria_ID(int ID);

	public List listarMercadorias();

	public int buscarIdMercadoria(int id);
	
	public void apagarTudo();
	
	public int buscaIdPorNome(String nome);

}
