package DAO;

import java.sql.Connection;
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
	
	public HospedesDAO() {}
	
	public HospedesDAO(Connection connection){
		this.connection = connection;
	}
	
	public void salvar(Hospedes hospede) {
		try {
			String sql = "INSERT INTO hospedes(nome, sobrenome, data_nascimento, nacionalidade, telefone) VALUES(?,?,?,?,?)";
			//Para as outras ações de get por ex, é necessário fazer uma função buscar passando uma string pra fazer a busca no bd
			
			pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
			
			pstm.close();
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível cadastrar o hospede");
		}
	}
	
	public ArrayList<Hospedes> buscar() {
		String sql = "SELECT * FROM hospedes WHERE nome = ? OR sobrenome = ?";
		connection = new ConnectionFactory().recuperarConexao();
		
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Hospedes hospedeBusca = new Hospedes();
				hospedeBusca.setId(rs.getInt("id"));
				hospedeBusca.setNome(rs.getString("nome"));
				hospedeBusca.setSobrenome(rs.getString("sobrenome"));
				hospedeBusca.setDataNascimento(rs.getDate("data_nascimento"));
				hospedeBusca.setNacionalidade(rs.getString("nacionalidade"));
				
				lista.add(hospedeBusca);
			}
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Hospede pesquisar: " + e);
		}
		
		return lista;
	}

}
