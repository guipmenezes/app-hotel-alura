package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexao.ConnectionFactory;
import Modelo.Reservas;

public class ReservaDAO {

	private Connection connection;
	public Reservas reserva;
	public ResultSet rs;
	public ArrayList<Reservas> lista = new ArrayList<>();

	public ReservaDAO() {
	}

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Reservas reserva) {
		String sql = "INSERT INTO reservas(DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES (?,?,?,?)";

		try {
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setDate(1, reserva.getDataEntrada());
			pstm.setDate(2, reserva.getDataSaida());
			pstm.setInt(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPagamento());

			pstm.executeUpdate();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}

			pstm.close();
		} catch (Exception e) {
			System.out.println("Erro" + e);
		}
	}

	public ArrayList<Reservas> buscarReserva(String nome) {
		String sql = "SELECT * FROM hospedes INNER JOIN reservas ON reservas.id = hospedes.id WHERE nome = ?";
		connection = new ConnectionFactory().recuperarConexao();

		try {
			PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, nome);

			rs = pstm.executeQuery();

			while (rs.next()) {
				Reservas reservaBusca = new Reservas();
				reservaBusca.setId(rs.getInt("id"));
				reservaBusca.setDataEntrada(rs.getDate("data_entrada"));
				reservaBusca.setDataSaida(rs.getDate("data_saida"));
				reservaBusca.setValor(rs.getInt("valor"));
				reservaBusca.setFormaPagamento(rs.getString("forma_pagamento"));

				lista.add(reservaBusca);
			}

			pstm.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Reserva pesquisar: " + e);
		}

		return lista;
	}

	public void editaReserva(Reservas reserva) {
		String sql = "UPDATE reservas SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?";
		connection = new ConnectionFactory().recuperarConexao();

		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setDate(1, reserva.getDataEntrada());
			pstm.setDate(2, reserva.getDataSaida());
			pstm.setInt(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPagamento());
			pstm.setInt(5, reserva.getId());

			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL Error: " + e);
		}
	}

	public void deletaReserva(Integer id) {
		String sql = "DELETE FROM reservas WHERE id = ?";
		connection = new ConnectionFactory().recuperarConexao();

		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);

			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro SQL: " + e);
		}
	}
}
