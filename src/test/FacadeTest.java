package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.venda.Venda;

import org.junit.Before;
import org.junit.Test;

import dao.concrete.DAOVendas;


public class FacadeTest {

	public DAOVendas daoVendas; 
	public Venda venda;
	
	@Before
	public void setUp(){
		daoVendas = new DAOVendas();
		venda = new Venda();
	}
	
}
	
