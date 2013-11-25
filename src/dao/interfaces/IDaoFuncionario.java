package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import models.funcionario.IFuncionario;

public interface IDaoFuncionario {

	public void criarFuncionario(IFuncionario funcionario) throws SQLException ;

	public void editarFuncionario(IFuncionario funcionario);

	public void apagarFuncionario(String rg) throws ClassNotFoundException;

	public List listarFuncionario();

	public void atualizarSalario(IFuncionario funcionario);

	public IFuncionario buscarFuncionario(String rg);

	public int quantidadeVendas(IFuncionario funcionario);

	public String buscarRg(String rg);

	public boolean checaRg(String rg);

	public double buscarSalario(IFuncionario funcionario);

}
