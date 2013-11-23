package dao.concrete;

public class MySQLDAOFactory extends DAOFactory {

	private DAOFuncionario daoFuncionario;
	private DAOMercadoria daoMercadoria;
	private DAOVendas daoVendas;
	
	public DAOFuncionario createDAOFuncionario() {
		daoFuncionario = new DAOFuncionario();
		return daoFuncionario;
	}
	
	public DAOMercadoria createDAOMercadoria() {
		daoMercadoria = new DAOMercadoria();
		return daoMercadoria;
	}
	
	public DAOVendas createDAOVendas() {
		daoVendas = new DAOVendas();
		return daoVendas;
	}
	
}