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

	private void conectar() {

		try {
			con = ConnectMySql.conexao("jdbc:mysql://localhost/tes", "root",
					"123456", MySQLDAOFactory.MYSQL);
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

			conectar();
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
			conectar();
			comando.executeUpdate(insert_venda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void apagarVenda(int id) {

		String remove_venda = "DELETE FROM vendas WHERE id = " + id + ";";
		try {
			conectar();
			comando.executeUpdate(remove_venda);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List listarVendas() {

		conectar();
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

	public IVenda buscarVenda(int ID) {
		ResultSet result = null;
		IVenda le = new Venda();

		try {
			conectar();
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
			conectar();
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
