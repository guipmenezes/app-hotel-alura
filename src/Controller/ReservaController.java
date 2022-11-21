package Controller;

import java.sql.Connection;

import Conexao.ConnectionFactory;
import DAO.ReservaDAO;
import Modelo.Reservas;

public class ReservaController {

	private ReservaDAO reservaDAO;
	
	public ReservaController(){
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	public void salvaReserva(Reservas reserva) {
		this.reservaDAO.salvar(reserva);
	}
 }
