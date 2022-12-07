package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexao.ConnectionFactory;
import Modelo.Hospedes;

public class HospedesDAO {

	private Connection connection;
	public PreparedStatement pstm;
	public ResultSet rs;
	public Hospedes hospede;
	public ArrayList<Hospedes> lista = new ArrayList<>();

	public HospedesDAO() {
	}

	public HospedesDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Hospedes hospede) {
		String sql = "INSERT INTO hospedes(nome, sobrenome, data_nascimento, nacionalidade, telefone) VALUES(?,?,?,?,?)";

		try {
			pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, hospede.getDataNascimento());
			pstm.setString(4, hospede.getNacionalidade());
			pstm.setString(5, hospede.getTelefone());

			pstm.executeUpdate();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					hospede.setId(rst.getInt(1));
				}
			}

			pstm.close();

		} catch (Exception e) {
			throw new RuntimeException("Não foi possível cadastrar o hospede");
		}
	}

	public ArrayList<Hospedes> buscar(String nome) {
		String sql = "SELECT * FROM hospedes WHERE nome = ?";
		connection = new ConnectionFactory().recuperarConexao();

		try {
			pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, nome);

			rs = pstm.executeQuery();

			while (rs.next()) {
				Hospedes hospedeBusca = new Hospedes();
				hospedeBusca.setId(rs.getInt("id"));
				hospedeBusca.setNome(rs.getString("nome"));
				hospedeBusca.setSobrenome(rs.getString("sobrenome"));
				hospedeBusca.setDataNascimento(rs.getDate("data_nascimento"));
				hospedeBusca.setNacionalidade(rs.getString("nacionalidade"));
				hospedeBusca.setTelefone(rs.getString("telefone"));

				lista.add(hospedeBusca);
			}

			pstm.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Hospede pesquisar: " + e);
		}

		return lista;
	}

	public void editarHospede(Hospedes hospede) {
		String sql = "UPDATE hospedes SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ? WHERE id = ?";
		connection = new ConnectionFactory().recuperarConexao();
		
		try{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, hospede.getDataNascimento());
			pstm.setString(4, hospede.getNacionalidade());
			pstm.setString(5, hospede.getTelefone());
			pstm.setInt(6, hospede.getId());
			
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Hospede pesquisar: " + e);
		}
	}
	
	public void deletaHospede(Integer id) {
		String sql = "DELETE FROM hospedes WHERE id = ?";
		connection = new ConnectionFactory().recuperarConexao();
		
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			
			pstm.execute();
			pstm.close();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: " + e);		
		}
	}

}
