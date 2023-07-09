package softCoin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import softCoin.entity.PessoaEntity;
import softCoin.service.PessoaService;
import softCoin.util.DateFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLogin extends JFrame {

	private JLabel lblEmail;
	private JTextField textEmail;
	private JLabel lblSenha;
	private JButton btnCadastrar;
	private JButton btnRecuperarSenha;
	private JPasswordField textSenha;
	private static PessoaService pessoaService;
	private static List<PessoaEntity> pessoas;
	public static TelaLogin telaLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaLogin = new TelaLogin();
					telaLogin.setVisible(true);
					telaLogin.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 */

	public TelaLogin() {
		setTitle("Login");

		pessoaService = new PessoaService();
		pessoas = pessoaService.listar();

		setBounds(100, 100, 522, 313);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(115, 91, 43, 14);
		panel.add(lblEmail);

		textEmail = new JTextField();

		textEmail.setBounds(168, 88, 203, 20);
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(textEmail);
		textEmail.setColumns(10);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(115, 134, 43, 14);
		panel.add(lblSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				efetuarLogin();
			}
		});
		btnEntrar.setBounds(156, 188, 104, 23);
		panel.add(btnEntrar);

		btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TelaCadastro telaCadastro = new TelaCadastro();
					telaCadastro.setVisible(true);
					telaCadastro.setLocationRelativeTo(null);
					setVisible(false);

				} catch (ParseException e1) {

					System.out.println(e1.getMessage());
					e1.printStackTrace();

				}
			}
		});
		btnCadastrar.setBounds(288, 188, 116, 23);
		panel.add(btnCadastrar);

		btnRecuperarSenha = new JButton("Recuperar senha");

		btnRecuperarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TelaRecuperacaoSenha recuperacaoSenha = new TelaRecuperacaoSenha();
					recuperacaoSenha.setVisible(true);
					recuperacaoSenha.setLocationRelativeTo(null);
					setVisible(false);

				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnRecuperarSenha.setBounds(205, 227, 147, 23);
		panel.add(btnRecuperarSenha);

		JLabel lblEsqueceuSenha = new JLabel("Esqueceu sua senha ?");
		lblEsqueceuSenha.setBounds(55, 231, 130, 14);
		panel.add(lblEsqueceuSenha);

		JLabel lblLogo = new JLabel("SoftCoin");
		lblLogo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		lblLogo.setBounds(216, 22, 102, 30);
		panel.add(lblLogo);

		textSenha = new JPasswordField();
		textSenha.setBounds(168, 131, 203, 20);
		panel.add(textSenha);
	}

	public void efetuarLogin() {

		String emailUser = textEmail.getText();
		char[] senha = textSenha.getPassword();
		String senhaUser = new String(senha);
		Arrays.fill(senha, ' ');
		int achou = 0;
		int i = 0;
		for (PessoaEntity pessoa : pessoas) {
			i += 1;
			if (emailUser.equalsIgnoreCase(pessoa.getEmail()) == true) {
				achou = 1;
				if (senhaUser.equals(pessoa.getSenha()) == true) {

					if (pessoa.isAdm() == true) {
						TelaAdmin menuAdmin = new TelaAdmin();
						menuAdmin.setVisible(true);
						menuAdmin.setLocationRelativeTo(null);
						setVisible(false);
					} else {
						TelaMenu menu = new TelaMenu(pessoa);
						menu.setVisible(true);
						menu.setLocationRelativeTo(null);
						setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuário/Senha invalidos!");
				}
			} else if (achou == 0 && i == pessoas.size()) {
				JOptionPane.showMessageDialog(null, "Usuário/Senha invalidos!");
			}

		}

	}

}
