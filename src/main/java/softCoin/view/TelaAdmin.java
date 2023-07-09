package softCoin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import softCoin.entity.MoedaEntity;
import java.awt.Cursor;

public class TelaAdmin extends JFrame {

	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private static TelaAdmin frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaAdmin();
					frame.setLocationRelativeTo(null);

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
	public TelaAdmin() {
		setTitle("Menu Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 218, 218));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		separator.setBounds(0, 69, 874, 31);
		contentPane.add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 100, 844, 389);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton lblCadastrarMoeda = new JButton("");
		lblCadastrarMoeda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCadastrarMoeda.setBorderPainted(false);
		lblCadastrarMoeda.setContentAreaFilled(false);
		lblCadastrarMoeda.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-barato-2-64.png"));
		lblCadastrarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMoeda telaMoeda = null;
				try {
					MoedaEntity moeda = new MoedaEntity(0, null, 0, 0, null, null, null, null, 0, 0, 0, null, null);
					telaMoeda = new TelaMoeda(moeda);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
				telaMoeda.setVisible(true);
				telaMoeda.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		lblCadastrarMoeda.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblCadastrarMoeda.setBounds(20, 27, 100, 85);
		panel.add(lblCadastrarMoeda);

		JButton btnRelatorio = new JButton("");
		btnRelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorio relatorio = new TelaRelatorio();
				relatorio.setVisible(true);
				relatorio.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnRelatorio.setBorderPainted(false);
		btnRelatorio.setContentAreaFilled(false);
		btnRelatorio.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-relatório-de-negócios-64.png"));
		btnRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRelatorio.setBounds(153, 27, 100, 85);
		panel.add(btnRelatorio);

		JButton btnCadastrosGerais = new JButton("");
		btnCadastrosGerais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrosGerais.setBorderPainted(false);
		btnCadastrosGerais.setContentAreaFilled(false);
		btnCadastrosGerais.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-adicionar-propriedade-64.png"));
		btnCadastrosGerais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrosGerais gerais = new TelaCadastrosGerais(0, "", 0);
				gerais.setLocationRelativeTo(null);
				gerais.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastrosGerais.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnCadastrosGerais.setBounds(280, 32, 100, 80);
		panel.add(btnCadastrosGerais);

		JButton btnSair = new JButton("");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.setBorderPainted(false);
		btnSair.setContentAreaFilled(false);
		btnSair.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-log-out-64.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TelaLogin login = new TelaLogin();
					login.setVisible(true);
					login.setLocationRelativeTo(null);
					setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSair.setBounds(721, 279, 89, 74);
		panel.add(btnSair);

		JLabel lblNewLabel = new JLabel("Cadastrar Moeda");
		lblNewLabel.setBounds(20, 116, 102, 29);
		panel.add(lblNewLabel);

		JLabel lblVisualizarCAD = new JLabel("Visualizar Cadastros ");
		lblVisualizarCAD.setBounds(141, 123, 129, 14);
		panel.add(lblVisualizarCAD);

		JLabel lblCadastrosGerais = new JLabel("Cadastros Gerais");
		lblCadastrosGerais.setBounds(290, 123, 100, 14);
		panel.add(lblCadastrosGerais);

		JButton btnComprasEVendas = new JButton("");
		btnComprasEVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComprasEVendas.setBorderPainted(false);
		btnComprasEVendas.setContentAreaFilled(false);
		btnComprasEVendas.setIcon(new ImageIcon("C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-comércio-electrónico-64.png"));
		btnComprasEVendas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnComprasEVendas.setBounds(418, 32, 89, 68);
		panel.add(btnComprasEVendas);

		JLabel lblComprasEVendas = new JLabel("Compras e Vendas");
		lblComprasEVendas.setBounds(418, 123, 113, 14);
		panel.add(lblComprasEVendas);

		JLabel lblSair = new JLabel("Sair");
		lblSair.setBounds(764, 364, 46, 14);
		panel.add(lblSair);

		JLabel lblLogo = new JLabel("SoftCoin");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblLogo.setBounds(24, 23, 112, 31);
		contentPane.add(lblLogo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(146, 0, 2, 68);
		contentPane.add(separator_1);

		JLabel lblAdmin = new JLabel("ADMIN");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAdmin.setBounds(55, 50, 57, 14);
		contentPane.add(lblAdmin);
	}

}
