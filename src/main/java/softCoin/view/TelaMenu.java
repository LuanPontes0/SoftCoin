package softCoin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import softCoin.entity.ColecaoEntity;
import softCoin.entity.PessoaEntity;
import softCoin.service.ColecaoService;
import softCoin.service.PessoaService;

import javax.swing.ImageIcon;
import java.awt.Cursor;

public class TelaMenu extends JFrame {

	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private static TelaMenu frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaMenu();
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

	public TelaMenu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 218, 218));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		separator.setBounds(0, 69, 874, 31);
		contentPane.add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(24, 100, 818, 389);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnMinhasColecoes = new JButton("");
		btnMinhasColecoes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMinhasColecoes.setBorderPainted(false);
		btnMinhasColecoes.setContentAreaFilled(false);
		btnMinhasColecoes.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-moedas-64.png"));
		btnMinhasColecoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaColecao colecao = new TelaColecao(null);
				colecao.setVisible(true);
				colecao.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnMinhasColecoes.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnMinhasColecoes.setBounds(40, 38, 89, 74);
		panel.add(btnMinhasColecoes);

		JButton btnComprasEVendas = new JButton("");
		btnComprasEVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComprasEVendas.setBorderPainted(false);
		btnComprasEVendas.setContentAreaFilled(false);
		btnComprasEVendas.setVerifyInputWhenFocusTarget(false);
		btnComprasEVendas.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-comércio-electrónico-64.png"));
		btnComprasEVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVenda telaVenda = new TelaVenda(null);
				telaVenda.setVisible(true);
				setVisible(false);
			}
		});
		btnComprasEVendas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnComprasEVendas.setBounds(156, 38, 89, 74);
		panel.add(btnComprasEVendas);

		JButton btnConfiguraes = new JButton("");
		btnConfiguraes.setContentAreaFilled(false);
		btnConfiguraes.setBorderPainted(false);
		btnConfiguraes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfiguraes.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-configurações-64.png"));
		btnConfiguraes.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnConfiguraes.setBounds(276, 38, 89, 74);
		panel.add(btnConfiguraes);

		JButton btnSair = new JButton("");
		btnSair.setContentAreaFilled(false);
		btnSair.setBorderPainted(false);
		btnSair.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-log-out-64.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaLogin login = new TelaLogin();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSair.setBounds(696, 49, 89, 74);
		panel.add(btnSair);

		JLabel lblMinhasColecoes = new JLabel("Minhas coleções");
		lblMinhasColecoes.setBounds(40, 123, 100, 14);
		panel.add(lblMinhasColecoes);

		JLabel lblCompraEVenda = new JLabel("Compras e Vendas");
		lblCompraEVenda.setBounds(149, 123, 114, 14);
		panel.add(lblCompraEVenda);

		JLabel lblConfiguracoes = new JLabel("Configurações");
		lblConfiguracoes.setBounds(286, 123, 89, 14);
		panel.add(lblConfiguracoes);

		JLabel lblSair = new JLabel("Sair");
		lblSair.setBounds(734, 123, 46, 14);
		panel.add(lblSair);

		JLabel lblLogo = new JLabel("SoftCoin");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblLogo.setBounds(24, 23, 112, 31);
		contentPane.add(lblLogo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(146, 0, 2, 70);
		contentPane.add(separator_1);

	}

	public TelaMenu(PessoaEntity pessoa) {

		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 218, 218));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		separator.setBounds(0, 69, 874, 31);
		contentPane.add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(24, 100, 818, 389);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnMinhasColecoes = new JButton("");
		btnMinhasColecoes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMinhasColecoes.setBorderPainted(false);
		btnMinhasColecoes.setContentAreaFilled(false);
		btnMinhasColecoes.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-moedas-64.png"));
		btnMinhasColecoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaColecao colecao = new TelaColecao(pessoa);
				colecao.setVisible(true);
				colecao.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnMinhasColecoes.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnMinhasColecoes.setBounds(40, 38, 89, 74);
		panel.add(btnMinhasColecoes);

		JButton btnComprasEVendas = new JButton("");
		btnComprasEVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComprasEVendas.setBorderPainted(false);
		btnComprasEVendas.setContentAreaFilled(false);
		btnComprasEVendas.setVerifyInputWhenFocusTarget(false);
		btnComprasEVendas.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-comércio-electrónico-64.png"));
		btnComprasEVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVenda telaVenda = new TelaVenda(pessoa);
				telaVenda.setVisible(true);
				telaVenda.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnComprasEVendas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnComprasEVendas.setBounds(156, 38, 89, 74);
		panel.add(btnComprasEVendas);

		JButton btnConfiguraes = new JButton("");
		btnConfiguraes.setContentAreaFilled(false);
		btnConfiguraes.setBorderPainted(false);
		btnConfiguraes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfiguraes.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-configurações-64.png"));
		btnConfiguraes.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnConfiguraes.setBounds(276, 38, 89, 74);
		panel.add(btnConfiguraes);

		JButton btnSair = new JButton("");
		btnSair.setContentAreaFilled(false);
		btnSair.setBorderPainted(false);
		btnSair.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-log-out-64.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaLogin login = new TelaLogin();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSair.setBounds(701, 38, 89, 74);
		panel.add(btnSair);

		JLabel lblMinhasColecoes = new JLabel("Minhas coleções");
		lblMinhasColecoes.setBounds(40, 123, 100, 14);
		panel.add(lblMinhasColecoes);

		JLabel lblCompraEVenda = new JLabel("Compras e Vendas");
		lblCompraEVenda.setBounds(149, 123, 114, 14);
		panel.add(lblCompraEVenda);

		JLabel lblConfiguracoes = new JLabel("Configurações");
		lblConfiguracoes.setBounds(286, 123, 89, 14);
		panel.add(lblConfiguracoes);

		JLabel lblLogo = new JLabel("SoftCoin");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblLogo.setBounds(24, 23, 112, 31);
		contentPane.add(lblLogo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(146, 0, 2, 70);
		contentPane.add(separator_1);

		JLabel lblSair = new JLabel("Sair");
		lblSair.setBounds(734, 123, 46, 14);
		panel.add(lblSair);

	}
}
