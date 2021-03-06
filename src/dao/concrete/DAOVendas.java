package dao.concrete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.IDaoVendas;
import models.venda.IVenda;
import models.venda.Venda;

public class DAOVendas implements IDaoVendas {

	private static Connection con;
	private static Statement comando;
	private String usuario;
	private String senha;
	
	public DAOVendas(){
		usuario = "travis";
		senha = "";
		conectar();
	}
	private void conectar() {

		try {
			con = ConnectMySql.conexao("jdbc:mysql://localhost/tes", usuario,
					senha, MySQLDAOFactory.MYSQL);
			comando = con.createStatement();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void criarVenda(IVenda venda) {

		String insert_venda = "INSERT INTO vendas (subtotal, vendedorRg, dataVenda, qtdVendas) VALUES ("
				+ ""
				+ venda.getSubtotal()
				+ ","
				+ "'"
				+ venda.getVendedorRG()
				+ "',"
				+ "'"
				+ venda.getDataVenda()
				+ "',"
				+ ""
				+ venda.getQtdVendas() + ");";
		try {
			comando.executeUpdate(insert_venda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(insert_venda);

	}

	public void editarVenda(IVenda venda) {

		String insert_venda = "UPDATE vendas SET " + "subtotal="
				+ venda.getSubtotal() + ",vendedorRg='" + venda.getVendedorRG()
				+ "',dataVenda='" + venda.getDataVenda() + "',qtdVendas="
				+ venda.getQtdVendas() + " WHERE id=" + venda.getID() + ";";
		try {
			comando.executeUpdate(insert_venda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void apagarVenda(int id) {

		String remove_venda = "DELETE FROM vendas WHERE id = " + id + ";";
		try {
			comando.executeUpdate(remove_venda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void apagarTudo(){
		String remove_venda = "DELETE FROM vendas;";
		try {
			comando.executeUpdate(remove_venda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List listarVendas() {

		List list_vendas = new ArrayList();
		ResultSet result;

		try {
			result = comando.executeQuery("SELECT * FROM vendas");

			while (result.next()) {
				IVenda le = new Venda();
				le.setID((result.getInt("id")));
				le.setSubtotal(result.getDouble("subtotal"));
				le.setVendedorRG(result.getString("vendedorRg"));
				le.setDataVenda(result.getString("dataVenda"));
				le.setQtdVendas(result.getInt("qtdVendas"));
				list_vendas.add(le);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list_vendas;

	}

	public int buscarVendaPorNome(String data){
		ResultSet result = null;
		int le = -1;

		try {
			result = comando.executeQuery("SELECT * FROM vendas WHERE dataVenda = '"
					+ data + "';");
			if (result.next()) {
				le = result.getInt("id");
				
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return le;
	}
	
	public IVenda buscarVenda(int ID) {
		ResultSet result = null;
		IVenda le = new Venda();

		try {
			result = comando.executeQuery("SELECT * FROM vendas WHERE id = "
					+ ID + ";");
			if (result.next()) {
				le.setID((result.getInt("id")));
				le.setSubtotal(result.getDouble("subtotal"));
				le.setVendedorRG(result.getString("vendedorRg"));
				le.setDataVenda(result.getString("dataVenda"));
				le.setQtdVendas(result.getInt("qtdVendas"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return le;
	}

	public int buscarIdVenda(int id) {

		ResultSet buscarId = null;

		try {
			buscarId = comando.executeQuery("SELECT id" + " FROM vendas"
					+ " WHERE id=" + id);
			if (buscarId.next()) {
				return Integer.parseInt(buscarId.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;

	}

}
