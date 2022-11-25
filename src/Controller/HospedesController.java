package Controller;

import java.sql.Connection;

import Conexao.ConnectionFactory;
import DAO.HospedesDAO;
import Modelo.Hospedes;
import Modelo.Reservas;

public class HospedesController {

	private HospedesDAO hospedesDAO;
	
	public HospedesController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedesDAO = new HospedesDAO(connection);
	}
	
	public void salvarHospede(Hospedes hospede) {
		this.hospedesDAO.salvar(hospede);
	}
	
	public void buscarHospede(Hospedes hospede) {
		this.hospedesDAO.buscar(hospede);
	}
 }
