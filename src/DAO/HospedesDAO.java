package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Modelo.Hospedes;
import Modelo.Reservas;

public class HospedesDAO {
	
	private Connection connection;
	
	public HospedesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Hospedes hospede) {
		try {
			String sql = "INSERT INTO hospedes(nome, sobrenome, data_nascimento, nacionalidade, telefone) VALUES(?,?,?,?,?)";
			//Para as outras ações de get por ex, é necessário fazer uma função buscar passando uma string pra fazer a busca no bd
			
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, hospede.getDataNascimento());
			pstm.setString(4, hospede.getNacionalidade());
			pstm.setString(5, hospede.getTelefone());
			
			if(pstm.executeUpdate() > 0) {
				System.out.println("Foi cadastrado com sucesso");
			} else {
				System.out.println("Não foi possível cadastrar o hospede");
			}
			
			try (ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					hospede.setId(rst.getInt(1));
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível cadastrar o hospede");
		}
	}
	
	public void buscar(Hospedes hospede) {
		try {
			String sql = "SELECT * FROM hospedes WHERE nome = ? OR sobrenome = ?";
			
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
		} catch(Exception e) {
			System.out.println("Não foi possível encontrar o hospede.");
		}
	}

}
