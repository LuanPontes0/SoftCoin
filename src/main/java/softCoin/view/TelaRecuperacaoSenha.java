package softCoin.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import softCoin.entity.PessoaEntity;
import softCoin.service.PessoaService;
import softCoin.util.CaixaConfirmacaoUtil;
import softCoin.util.EmailValidatorUtil;
import softCoin.util.ValidaCpfUtil;

public class TelaRecuperacaoSenha extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JFormattedTextField textCPF;
	private JPasswordField txtSenha;
	private JPasswordField txtRepitaSenha;
	private JFormattedTextField textTelefone;
	private PessoaService pessoaService = new PessoaService();
	private EmailValidatorUtil emailValidatorUtil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRecuperacaoSenha frame = new TelaRecuperacaoSenha();
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
	 * 
	 * @throws ParseException
	 */
	public TelaRecuperacaoSenha() throws ParseException {
		setTitle("Recuperação de senha");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Recuperação de senha");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(154, 22, 235, 25);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(164, 58, 204, 2);
		contentPane.add(separator);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(77, 103, 70, 14);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(144, 103, 259, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCPF.setBounds(77, 146, 56, 20);
		contentPane.add(lblCPF);

		textCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		textCPF.setColumns(10);
		textCPF.setBounds(144, 146, 259, 20);
		contentPane.add(textCPF);

		JLabel lblNovaSenha = new JLabel("Nova senha:");
		lblNovaSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNovaSenha.setBounds(44, 221, 104, 20);
		contentPane.add(lblNovaSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(144, 224, 259, 20);
		contentPane.add(txtSenha);

		JLabel lblRepitaSenha = new JLabel("Repita senha:");
		lblRepitaSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRepitaSenha.setBounds(32, 264, 104, 20);
		contentPane.add(lblRepitaSenha);

		txtRepitaSenha = new JPasswordField();
		txtRepitaSenha.setBounds(144, 264, 259, 20);
		contentPane.add(txtRepitaSenha);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 311, 566, 2);
		contentPane.add(separator_1);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recuperSenha();
			}
		});
		btnSalvar.setBounds(187, 324, 164, 42);
		contentPane.add(btnSalvar);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin login = new TelaLogin();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnVoltar.setBorderPainted(false);
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-back-arrow-50.png"));
		btnVoltar.setBounds(10, 11, 40, 36);
		contentPane.add(btnVoltar);

		JButton btnAjuda = new JButton("");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaixaConfirmacaoUtil.chamarTelaAjuda(
						"Se você esqueceu sua senha e deseja recuperá-la, siga estas dicas úteis:\r\n" + "\r\n"
								+ "Informações de identificação: Certifique-se de fornecer as informações de identificação corretas durante o processo de recuperação de senha. Isso inclui o seu endereço de e-mail registrado, CPF e número de telefone associado à sua conta. Verifique se você digitou esses detalhes com precisão para garantir que possamos localizar corretamente sua conta.\r\n"
								+ "\r\n"
								+ "Senha nova: Quando solicitado a inserir uma nova senha, escolha uma senha segura e exclusiva. Certifique-se de que sua nova senha tenha 8 caracteres de comprimento. Tente usar uma combinação de letras maiúsculas, letras minúsculas, números e caracteres especiais para tornar a senha mais forte e difícil de adivinhar.\r\n"
								+ "\r\n"
								+ "Confirmação da senha: Digite a senha novamente no campo de confirmação para garantir que não haja erros de digitação. Certifique-se de que as duas entradas da senha correspondam exatamente.\r\n"
								+ "\r\n"
								+ "Suporte oferecido: Em caso de dúvidas ou dificuldades durante o processo de recuperação de senha, não hesite em entrar em contato conosco. Envie um e-mail para suporteCoin@gmail.com e nossa equipe de suporte estará pronta para ajudar e fornecer assistência adicional.\r\n"
								+ "\r\n"
								+ "Agradecemos por utilizar nossos serviços e esperamos que você tenha sucesso na recuperação da sua senha!");
			}
		});
		btnAjuda.setBorderPainted(false);
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-help-30.png"));
		btnAjuda.setBounds(449, 209, 40, 40);
		contentPane.add(btnAjuda);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTelefone.setBounds(44, 177, 70, 20);
		contentPane.add(lblTelefone);

		textTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		textTelefone.setColumns(10);
		textTelefone.setBounds(144, 177, 259, 20);
		contentPane.add(textTelefone);
	}

	public void recuperSenha() {

		int achou = 0;

		// Recuperando a senha do campo
		char[] senhaDigitada = txtSenha.getPassword();
		char[] senhaRepetida = txtRepitaSenha.getPassword();
		String senhaUser = new String(senhaDigitada);
		String senhaUserRepetida = new String(senhaRepetida);

		// Limpando variaveis char
		Arrays.fill(senhaDigitada, ' ');
		Arrays.fill(senhaRepetida, ' ');

		String emailUser = textEmail.getText();

		String cpfUser = textCPF.getText().replace(".", "").replace("-", "");

		// Recuperando campo telefone e formatando
		String telefoneUser = textTelefone.getText().replace("(", "").replace("-", "").replace(")", "");

		boolean emailValida = false;

		try {
			emailValidatorUtil = new EmailValidatorUtil();
			emailValida = emailValidatorUtil.validar(emailUser);
		} catch (Exception e) {
			System.out.println("DEU ERRO NO VALIDADOR DE EMAIL ");
		}

		if (emailUser.isBlank() == true || emailUser.indexOf("@") == -1 || emailUser.length() > 200
				|| textEmail.getText().indexOf("@") >= emailUser.length() - 1 || emailValida == false) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor informe um e-mail valido");
			return;
		}

		if (telefoneUser.isBlank() == true || telefoneUser.length() != 11) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor, informe um telefone valido!");
			return;
		}

		if (ValidaCpfUtil.verificarFormatoCPF(cpfUser) == false) {

			JOptionPane.showMessageDialog(null, "Campo CPF vazio ou CPF informado é inválido!");
			achou = 1;
			return;

		}
		if (senhaUser.isBlank() == true || senhaUser.length() != 8) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor, insira uma senha de 8 caracteres!");
			return;
		}
		if (senhaUserRepetida.isBlank() == true || senhaUserRepetida.equals(senhaUser) == false) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "As duas senhas precisam ser iguais!");
			return;
		}
		if (achou == 0) {
			// Pessoa que esta recuperando a senha
			PessoaEntity pessoa = new PessoaEntity();

			// Cadastro existente no sistema

			PessoaEntity user = new PessoaEntity();

			pessoa.setEmail(emailUser);
			pessoa.setTelefone(telefoneUser);
			pessoa.setCpf(cpfUser);

			user = pessoaService.pesquisar(pessoa);

			if (user == null) {
				JOptionPane.showMessageDialog(null, "Desculpe, mas parece que não conseguimos encontrar uma"
						+ " correspondência para os dados que você forneceu\nEm caso de dúvidas, entre em contato com nosso suporte!\r\n"
						+ "\nsuporteCoin@gmail.com");
				return;
			} else {
				if (user.getEmail().equals(pessoa.getEmail()) == true && user.getCpf().equals(pessoa.getCpf()) == true
						&& user.getTelefone().equals(pessoa.getTelefone()) == true) {
					user.setSenha(senhaUser);
					try {
						pessoaService.salvar(user);
						JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Ocorreu um erro ao alterar a senha!, por favor, tente novamente mais tarde!");

					}

				} else {
					JOptionPane.showMessageDialog(null, "Desculpe, mas parece que não conseguimos encontrar uma"
							+ " correspondência para os dados que você forneceu\nEm caso de dúvidas, entre em contato com nosso suporte!\r\n"
							+ "\nsuporteCoin@gmail.com");
					return;
				}
			}
		}
	}
}
