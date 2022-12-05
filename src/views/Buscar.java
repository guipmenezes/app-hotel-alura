package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.HospedesDAO;
import DAO.ReservaDAO;
import Modelo.Hospedes;
import Modelo.Reservas;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	public HospedesDAO hospedeDAO;
	private JTable tbReservas;
	private JTable tbHospedes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagensView/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		DefaultTableCellRenderer cellCentralizador = new DefaultTableCellRenderer() {
			public void setValue(Object value) {
				setHorizontalAlignment(JLabel.CENTER);
				super.setValue(value);
			}
		};

		JScrollPane scrollPaneReserva = new JScrollPane();
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagensView/reservado.png")),
				scrollPaneReserva, null);

		tbReservas = new JTable();
		tbReservas.setCellSelectionEnabled(true);
		tbReservas.setColumnSelectionAllowed(true);
		tbReservas.setShowGrid(false);
		tbReservas.setBorder(null);
		tbReservas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Numero da Reserva", "Data Check In", "Data Checkout", "Valor", "Forma pgto" }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, String.class, Integer.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneReserva.setViewportView(tbReservas);

		tbReservas.getColumn("Numero da Reserva").setCellRenderer(cellCentralizador);
		tbReservas.getColumn("Data Check In").setCellRenderer(cellCentralizador);
		tbReservas.getColumn("Data Checkout").setCellRenderer(cellCentralizador);
		tbReservas.getColumn("Valor").setCellRenderer(cellCentralizador);
		tbReservas.getColumn("Forma pgto").setCellRenderer(cellCentralizador);

		JScrollPane scrollPaneHospede = new JScrollPane();
		panel.addTab("Hospedes", new ImageIcon(Buscar.class.getResource("/imagensView/pessoas.png")), scrollPaneHospede,
				null);

		tbHospedes = new JTable();
		tbHospedes.setCellSelectionEnabled(true);
		tbHospedes.setShowGrid(false);
		tbHospedes.setColumnSelectionAllowed(true);
		tbHospedes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Numero de Hospede", "Nome",
				"Sobrenome", "Data de Nascimento", "Nacionalidade", "Telefone" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneHospede.setViewportView(tbHospedes);

		tbHospedes.getColumn("Numero de Hospede").setCellRenderer(cellCentralizador);
		tbHospedes.getColumn("Nome").setCellRenderer(cellCentralizador);
		tbHospedes.getColumn("Sobrenome").setCellRenderer(cellCentralizador);
		tbHospedes.getColumn("Data de Nascimento").setCellRenderer(cellCentralizador);
		tbHospedes.getColumn("Nacionalidade").setCellRenderer(cellCentralizador);
		tbHospedes.getColumn("Telefone").setCellRenderer(cellCentralizador);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagensView/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarValores();
				buscarReserva();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editaHospede();
			}
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	private void buscarValores() {
		try {
			String entradaTexto = (txtBuscar.getText());

			HospedesDAO hospedesBusca = new HospedesDAO();

			DefaultTableModel model = (DefaultTableModel) tbHospedes.getModel();
			model.setNumRows(0);

			ArrayList<Hospedes> lista = hospedesBusca.buscar(entradaTexto);

			for (int num = 0; num < lista.size(); num++) {
				model.addRow(new Object[] { lista.get(num).getId(), lista.get(num).getNome(),
						lista.get(num).getSobrenome(), lista.get(num).getDataNascimento(),
						lista.get(num).getNacionalidade(), lista.get(num).getTelefone() });
			}

		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(contentPane, "Listas valores: " + e);
		}
	}

	private void buscarReserva() {
		try {
			String entradaTexto = (txtBuscar.getText());

			ReservaDAO reservaBusca = new ReservaDAO();

			DefaultTableModel model = (DefaultTableModel) tbReservas.getModel();
			model.setNumRows(0);

			ArrayList<Reservas> lista = reservaBusca.buscarReserva(entradaTexto);

			for (int num = 0; num < lista.size(); num++) {
				model.addRow(new Object[] { lista.get(num).getId(), lista.get(num).getDataEntrada(),
						lista.get(num).getDataSaida(), lista.get(num).getValor(), lista.get(num).getFormaPagamento() });
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(contentPane, "Lista Reservas: " + e);
		}
	}

	private void editaHospede() {
		try {
			HospedesDAO hospedeEdita = new HospedesDAO();
			
			int setar = tbHospedes.getSelectedRow();

			DefaultTableModel model = (DefaultTableModel) tbHospedes.getModel();
			Integer id = Integer.parseInt(model.getValueAt(setar, 0).toString());
			String nome = model.getValueAt(setar, 1).toString();
			String sobrenome = model.getValueAt(setar, 2).toString();
			Date dataNascimento = Date.valueOf(model.getValueAt(setar, 3).toString());
			String nacionalidade = model.getValueAt(setar, 4).toString();
			String telefone = model.getValueAt(setar, 5).toString();

			Hospedes hospedeEditado = new Hospedes();
			hospedeEditado.setId(id);
			hospedeEditado.setNome(nome);
			hospedeEditado.setSobrenome(sobrenome);
			hospedeEditado.setDataNascimento(dataNascimento);
			hospedeEditado.setNacionalidade(nacionalidade);
			hospedeEditado.setTelefone(telefone);
			
			hospedeEdita.editarHospede(hospedeEditado);
			JOptionPane.showMessageDialog(contentPane, "Os dados foram atualizados com sucesso.");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Não foi possível editar: " + e);
		}
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	public JTable getTbReservas() {
		return tbReservas;
	}
}
