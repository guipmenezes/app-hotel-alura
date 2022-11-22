package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Modelo.Reservas;

public class ReservaDAO {

	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Reservas reserva) {
		try {
			String sql = "INSERT INTO reservas(DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES (?,?,?,?,?)";
			
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setDate(2, reserva.getDataEntrada());
			pstm.setDate(3, reserva.getDataSaida());
			pstm.setString(4, reserva.getValor());
			pstm.setString(5, reserva.getFormaPagamento());
				
			pstm.executeUpdate();
				
			try (ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}
		} catch(Exception e) {
			
		}
	}
}
