package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Modelo.Hospedes;

public class HospedesDAO {
	
	private Connection connection;
	
	public HospedesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Hospedes hospede) {
		try {
			String sql = "INSERT INTO hospedes(NOME, SOBRENOME, DATA_NASCIMENTO, NACIONALIDADE, TELEFONE) VALUES(?,?,?,?,?,?)";
			
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(2, hospede.getNome());
			pstm.setString(3, hospede.getSobrenome());
			pstm.setDate(4, hospede.getDataNascimento());
			pstm.setString(5, hospede.getNacionalidade());
			pstm.setString(6, hospede.getTelefone());
			
			pstm.executeUpdate();
			
			try (ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					hospede.setId(rst.getInt(1));
				}
			}
			
		} catch (Exception e) {
			
		}
	}

}
