package Controller;

import java.sql.Connection;
import java.util.ArrayList;

import Conexao.ConnectionFactory;
import DAO.HospedesDAO;
import Modelo.Hospedes;

public class HospedesController {

	private HospedesDAO hospedesDAO;
	
	public HospedesController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedesDAO = new HospedesDAO(connection);
	}
	
	public void salvarHospede(Hospedes hospede) {
		this.hospedesDAO.salvar(hospede);
	}
 }
