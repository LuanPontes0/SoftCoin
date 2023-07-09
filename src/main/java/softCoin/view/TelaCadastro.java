package softCoin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import softCoin.entity.ColecaoEntity;
import softCoin.entity.PessoaEntity;
import softCoin.service.ColecaoService;
import softCoin.service.PessoaService;
import softCoin.util.CaixaConfirmacaoUtil;
import softCoin.util.DateFormatter;
import softCoin.util.EmailValidatorUtil;
import softCoin.util.ValidaCpfUtil;

public class TelaCadastro extends JFrame {
	private DateFormatter dateFormatter;
	private PessoaService pessoaService;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textRepEmail;
	private JPasswordField textSenha;
	private JPasswordField textRepitaSenha;
	private JFormattedTextField textCpf;
	private JFormattedTextField textTelefone;
	private JFormattedTextField textDataNascimento;
	private static TelaCadastro frame;
	private JRadioButton rdbAdm;
	private EmailValidatorUtil emailValidatorUtil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PessoaEntity pessoaEntity = new PessoaEntity();
					frame = new TelaCadastro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

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
	public TelaCadastro(PessoaEntity pessoaEntity) throws ParseException {
		setTitle("Tela de Cadastro de Usuário");
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		dateFormatter = new DateFormatter();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 623, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		textCpf.setToolTipText("Insira seu CPF aqui");
		textCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCpf.setHorizontalAlignment(SwingConstants.LEFT);
		textCpf.setText("");
		textCpf.setBounds(418, 64, 179, 20);
		contentPane.add(textCpf);
		textCpf.setText(pessoaEntity.getCpf());

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 183, 587, 2);
		contentPane.add(separator);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(379, 67, 29, 14);
		contentPane.add(lblCpf);

		JLabel lblDadosPessoais = new JLabel("Dados Pessoais");
		lblDadosPessoais.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosPessoais.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDadosPessoais.setBounds(209, 3, 158, 42);
		contentPane.add(lblDadosPessoais);

		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 67, 112, 14);
		contentPane.add(lblNome);

		textNome = new JTextField();
		textNome.setToolTipText("Insira seu nome completo aqui");
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNome.setBounds(120, 64, 247, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		textNome.setText(pessoaEntity.getNomeCompleto());

		JLabel lblDataNascimento = new JLabel("Data De Nascimento:");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataNascimento.setBounds(10, 120, 135, 14);
		contentPane.add(lblDataNascimento);

		textDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		textDataNascimento.setToolTipText("Insira sua data de nascimento aqui");
		textDataNascimento.setText("");
		textDataNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		textDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDataNascimento.setBounds(155, 117, 94, 20);
		contentPane.add(textDataNascimento);
		if (pessoaEntity.getId() != 0) {
			textDataNascimento.setText(dateFormatter.formatarDataBR(pessoaEntity.getDataNascimento()));
		}
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(273, 120, 56, 14);
		contentPane.add(lblTelefone);

		textTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		textTelefone.setText("");
		textTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTelefone.setBounds(339, 119, 179, 20);
		contentPane.add(textTelefone);
		textTelefone.setText(pessoaEntity.getTelefone());

		JLabel lblDadosUser = new JLabel("Dados Da Conta");
		lblDadosUser.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDadosUser.setBounds(209, 183, 158, 42);
		contentPane.add(lblDadosUser);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(20, 243, 41, 14);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(20, 268, 247, 20);
		contentPane.add(textEmail);
		textEmail.setText(pessoaEntity.getEmail());

		JLabel lblRepitaSeuEmail = new JLabel("Repita Seu E-mail");
		lblRepitaSeuEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRepitaSeuEmail.setBounds(20, 299, 110, 14);
		contentPane.add(lblRepitaSeuEmail);

		textRepEmail = new JTextField();
		textRepEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRepEmail.setColumns(10);
		textRepEmail.setBounds(20, 324, 247, 20);
		contentPane.add(textRepEmail);
		textRepEmail.setText(pessoaEntity.getEmail());

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(309, 243, 41, 14);
		contentPane.add(lblSenha);

		textSenha = new JPasswordField();
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSenha.setBounds(309, 268, 247, 20);
		contentPane.add(textSenha);
		textSenha.setText(pessoaEntity.getSenha());

		JLabel lblRepitaSenha = new JLabel("Repita Sua Senha");
		lblRepitaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRepitaSenha.setBounds(306, 299, 113, 14);
		contentPane.add(lblRepitaSenha);

		textRepitaSenha = new JPasswordField();
		textRepitaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRepitaSenha.setBounds(308, 324, 247, 20);
		contentPane.add(textRepitaSenha);
		textRepitaSenha.setText(pessoaEntity.getSenha());

		JButton btnCadastrar = new JButton("Cadastrar-Se");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pessoaEntity.getId() == 0) {
					CadastrarPessoa(1);// Chamando o metodo apos usuario clicar em salvar
				} else {
					editarPessoa(pessoaEntity.getId());
				}
			}
		});
		btnCadastrar.setBackground(new Color(0, 255, 0));
		btnCadastrar.setBounds(429, 395, 112, 23);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					TelaRelatorio relatorio = new TelaRelatorio();
					relatorio.setVisible(true);
					relatorio.setLocationRelativeTo(null);

					setVisible(false);

				}
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-back-arrow-50.png"));
		btnVoltar.setBounds(10, 3, 40, 30);
		contentPane.add(btnVoltar);

		if (pessoaEntity.getId() != 0) {
			rdbAdm = new JRadioButton("Administrador");
			rdbAdm.setEnabled(true);
			rdbAdm.setBounds(13, 395, 109, 23);
			contentPane.add(rdbAdm);
			rdbAdm.setSelected(pessoaEntity.isAdm());
		}

		JButton btnAjuda = new JButton("");
		btnAjuda.setToolTipText("Precisa de ajuda com o cadastro?");
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-help-30.png"));
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaixaConfirmacaoUtil.chamarTelaAjuda(
						"Olá! Estamos felizes em tê-lo(a) como nosso novo usuário. Para facilitar o processo de cadastro em nosso sistema, aqui estão algumas dicas úteis:\r\n"
								+ "\r\n"
								+ "Informações precisas: Ao preencher o formulário de cadastro, certifique-se de fornecer informações precisas e atualizadas. Isso inclui seu nome completo, endereço de e-mail válido e qualquer outra informação solicitada. Isso nos ajudará a manter uma comunicação eficiente e garantir que sua conta esteja corretamente registrada.\r\n"
								+ "\r\n"
								+ "Senha segura: Escolha uma senha forte de 8 caracteres e que seja única para sua conta. Recomendamos utilizar uma combinação de letras maiúsculas e minúsculas, números e caracteres especiais. Evite usar senhas óbvias, como datas de nascimento ou sequências simples. Lembre-se de não compartilhar sua senha com ninguém e atualizá-la periodicamente para manter sua conta protegida.\r\n"
								+ "\r\n"
								+ "Política de privacidade: Leia atentamente nossa política de privacidade para entender como seus dados pessoais serão coletados, armazenados e utilizados. Garantimos a segurança e a confidencialidade de suas informações, mas é importante que você esteja ciente de nossas práticas e como suas informações serão tratadas.\r\n"
								+ "\r\n"
								+ "Verifique seus dados: Antes de finalizar o cadastro, verifique cuidadosamente os dados que você inseriu. Certifique-se de que todas as informações estejam corretas para evitar problemas futuros. Isso inclui seu nome, e-mail, número de telefone e outras informações relevantes.\r\n"
								+ "\r\n"
								+ "Suporte disponível: Se você tiver alguma dúvida ou encontrar qualquer dificuldade durante o processo de cadastro, nossa equipe de suporte está à disposição para ajudar. Entre em contato conosco através do e-mail suporteCoin@gmail.com . Teremos prazer em auxiliá-lo(a) e garantir que seu cadastro seja concluído com sucesso.\r\n"
								+ "\r\n"
								+ "Agradecemos por escolher nosso sistema. Estamos ansiosos para proporcionar uma experiência incrível para você. Seja bem-vindo(a)!");
			}
		});
		btnAjuda.setBounds(552, 196, 30, 25);
		contentPane.add(btnAjuda);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(289, 237, 9, 155);
		contentPane.add(separator_1);
	}

	public TelaCadastro() throws ParseException {
		setTitle("Tela de Cadastro de Usuário");

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		dateFormatter = new DateFormatter();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 623, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		textCpf.setToolTipText("Insira seu CPF aqui");
		textCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCpf.setHorizontalAlignment(SwingConstants.LEFT);
		textCpf.setText("");
		textCpf.setBounds(418, 64, 179, 20);
		contentPane.add(textCpf);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 183, 587, 2);
		contentPane.add(separator);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(379, 67, 29, 14);
		contentPane.add(lblCpf);

		JLabel lblDadosPessoais = new JLabel("Dados Pessoais");
		lblDadosPessoais.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosPessoais.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDadosPessoais.setBounds(209, 3, 158, 42);
		contentPane.add(lblDadosPessoais);

		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 67, 112, 14);
		contentPane.add(lblNome);

		textNome = new JTextField();
		textNome.setToolTipText("Insira seu nome completo aqui");
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNome.setBounds(120, 64, 247, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		JLabel lblDataNascimento = new JLabel("Data De Nascimento:");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataNascimento.setBounds(10, 120, 135, 14);
		contentPane.add(lblDataNascimento);

		textDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		textDataNascimento.setToolTipText("Insira sua data de nascimento aqui");
		textDataNascimento.setText("");
		textDataNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		textDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDataNascimento.setBounds(155, 117, 94, 20);
		contentPane.add(textDataNascimento);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(273, 120, 56, 14);
		contentPane.add(lblTelefone);

		textTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		textTelefone.setText("");
		textTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTelefone.setBounds(339, 119, 179, 20);
		contentPane.add(textTelefone);

		JLabel lblDadosUser = new JLabel("Dados Da Conta");
		lblDadosUser.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblDadosUser.setBounds(209, 183, 158, 42);
		contentPane.add(lblDadosUser);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(20, 243, 41, 14);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(20, 268, 247, 20);
		contentPane.add(textEmail);

		JLabel lblRepitaSeuEmail = new JLabel("Repita Seu E-mail");
		lblRepitaSeuEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRepitaSeuEmail.setBounds(20, 299, 110, 14);
		contentPane.add(lblRepitaSeuEmail);

		textRepEmail = new JTextField();
		textRepEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRepEmail.setColumns(10);
		textRepEmail.setBounds(20, 324, 247, 20);
		contentPane.add(textRepEmail);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(309, 243, 41, 14);
		contentPane.add(lblSenha);

		textSenha = new JPasswordField();
		textSenha.setToolTipText("A senha precisa ter 8 caracteres");
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSenha.setBounds(309, 268, 247, 20);
		contentPane.add(textSenha);

		JLabel lblRepitaSenha = new JLabel("Repita Sua Senha");
		lblRepitaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRepitaSenha.setBounds(306, 299, 113, 14);
		contentPane.add(lblRepitaSenha);

		textRepitaSenha = new JPasswordField();
		textRepitaSenha.setToolTipText("A senha precisa ter 8 caracteres");
		textRepitaSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRepitaSenha.setBounds(308, 324, 247, 20);
		contentPane.add(textRepitaSenha);

		JButton btnCadastrar = new JButton("Cadastrar-Se");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarPessoa(0);// Chamando o metodo apos usuario clicar em salvar

			}
		});
		btnCadastrar.setBackground(new Color(0, 255, 0));
		btnCadastrar.setBounds(429, 395, 112, 23);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					TelaLogin login = new TelaLogin();
					login.setVisible(true);
					login.setLocationRelativeTo(null);

					setVisible(false);

				}
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-back-arrow-50.png"));
		btnVoltar.setBounds(10, 3, 40, 30);
		contentPane.add(btnVoltar);

		JButton btnAjuda = new JButton("");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaixaConfirmacaoUtil.chamarTelaAjuda(
						"Olá! Estamos felizes em tê-lo(a) como nosso novo usuário. Para facilitar o processo de cadastro em nosso sistema, aqui estão algumas dicas úteis:\r\n"
								+ "\r\n"
								+ "Informações precisas: Ao preencher o formulário de cadastro, certifique-se de fornecer informações precisas e atualizadas. Isso inclui seu nome completo, endereço de e-mail válido e qualquer outra informação solicitada. Isso nos ajudará a manter uma comunicação eficiente e garantir que sua conta esteja corretamente registrada.\r\n"
								+ "\r\n"
								+ "Senha segura: Escolha uma senha forte de 8 caracteres e que seja única para sua conta. Recomendamos utilizar uma combinação de letras maiúsculas e minúsculas, números e caracteres especiais. Evite usar senhas óbvias, como datas de nascimento ou sequências simples. Lembre-se de não compartilhar sua senha com ninguém e atualizá-la periodicamente para manter sua conta protegida.\r\n"
								+ "\r\n"
								+ "Política de privacidade: Leia atentamente nossa política de privacidade para entender como seus dados pessoais serão coletados, armazenados e utilizados. Garantimos a segurança e a confidencialidade de suas informações, mas é importante que você esteja ciente de nossas práticas e como suas informações serão tratadas.\r\n"
								+ "\r\n"
								+ "Verifique seus dados: Antes de finalizar o cadastro, verifique cuidadosamente os dados que você inseriu. Certifique-se de que todas as informações estejam corretas para evitar problemas futuros. Isso inclui seu nome, e-mail, número de telefone e outras informações relevantes.\r\n"
								+ "\r\n"
								+ "Suporte disponível: Se você tiver alguma dúvida ou encontrar qualquer dificuldade durante o processo de cadastro, nossa equipe de suporte está à disposição para ajudar. Entre em contato conosco através do e-mail suporteCoin@gmail.com . Teremos prazer em auxiliá-lo(a) e garantir que seu cadastro seja concluído com sucesso.\r\n"
								+ "\r\n"
								+ "Agradecemos por escolher nosso sistema. Estamos ansiosos para proporcionar uma experiência incrível para você. Seja bem-vindo(a)!");
			}
		});
		btnAjuda.setToolTipText("Precisa de ajuda com o cadastro?");
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-help-30.png"));
		btnAjuda.setBounds(552, 196, 30, 25);
		contentPane.add(btnAjuda);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(289, 237, 9, 155);
		contentPane.add(separator_1);
	}

	public void CadastrarPessoa(int i) {
		int achou = 0;

		// Recuperando a senha do campo
		char[] senhaDigitada = textSenha.getPassword();
		char[] senhaRepetida = textRepitaSenha.getPassword();
		String senhaUser = new String(senhaDigitada);
		String senhaUserRepetida = new String(senhaRepetida);

		// Limpando variaveis char
		Arrays.fill(senhaDigitada, ' ');
		Arrays.fill(senhaRepetida, ' ');

		// Recuperando campo Cpf e formatando
		String cpfUser = textCpf.getText().replace(".", "").replace("-", "");

		// Recuperando campo telefone e formatando
		String telefoneUser = textTelefone.getText().replace("(", "").replace("-", "").replace(")", "");

		// Recuperando campo data nascimento
		boolean dataNascimentoValida = false;

		try {
			dataNascimentoValida = dateFormatter.validarDataNascimento(textDataNascimento.getText());
		} catch (com.google.protobuf.TextFormat.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean emailValida = false;

		try {
			emailValidatorUtil = new EmailValidatorUtil();
			emailValida = emailValidatorUtil.validar(textEmail.getText());
		} catch (Exception e) {
			System.out.println("DEU ERRO NO VALIDADOR DE EMAIL ");
		}

		// Validações dos campos do formulario
		if (textNome.getText().isBlank() == true || textNome.getText().length() > 70) {

			JOptionPane.showMessageDialog(null, "Campo nome vazio ou acima de 70 caracteres!");
			achou = 1;
			return;
		}
		if (ValidaCpfUtil.verificarFormatoCPF(cpfUser) == false) {

			JOptionPane.showMessageDialog(null, "Campo CPF vazio ou CPF informado é inválido!");
			achou = 1;
			return;

		}
		if (dataNascimentoValida == false) {

			JOptionPane.showMessageDialog(null, "Data de nascimento inválida !");
			achou = 1;
			return;
		}
		if (telefoneUser.isBlank() == true || telefoneUser.length() != 11) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor, informe um telefone valido!");
			return;
		}
		if (textEmail.getText().isBlank() == true || textEmail.getText().indexOf("@") == -1
				|| textEmail.getText().length() > 200
				|| textEmail.getText().indexOf("@") >= textEmail.getText().length() - 1 || emailValida == false) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor informe um e-mail valido");
			return;
		}
		if (textRepEmail.getText().isBlank() == true || textRepEmail.getText().equals(textEmail.getText()) == false
				|| textEmail.getText().length() > 200 || emailValida == false) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Os dois e-mails precisam ser iguais!");
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
			CaixaConfirmacaoUtil.chamarTermosCondicoes();
			PessoaEntity pessoaEntity = new PessoaEntity();
			pessoaService = new PessoaService();
			pessoaEntity.setNomeCompleto(textNome.getText());
			pessoaEntity.setCpf(cpfUser);
			pessoaEntity.setDataNascimento(dateFormatter.converterParaData(textDataNascimento.getText()));
			pessoaEntity.setEmail(textEmail.getText());
			pessoaEntity.setTelefone(telefoneUser);
			pessoaEntity.setSenha(senhaUser);
			try {

				boolean usuarioExistente = pessoaService.salvar(pessoaEntity);
				if (usuarioExistente == false) {
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					if (i == 0) {
						if (pessoaEntity.getColecoes() == null) {
							String titulo = "moedas";
							ColecaoEntity colecaoEntity = new ColecaoEntity();
							ColecaoService colecaoService = new ColecaoService();
							colecaoEntity.setDono(pessoaEntity);
							colecaoEntity.setTituloColecao(titulo);
							colecaoService.salvar(colecaoEntity);
							pessoaService.salvar(pessoaEntity);
							

						}

						/*
						 * TelaMenu telaMenu = new TelaMenu(pessoaService.pesquisar(pessoaEntity));
						 * telaMenu.setVisible(true); telaMenu.setLocationRelativeTo(null);
						 */
						
						TelaLogin telaLogin = new TelaLogin();
						telaLogin.setVisible(true);
						telaLogin.setLocationRelativeTo(null);
						setVisible(false);;
					} else {
						TelaAdmin telaAdmin = new TelaAdmin();
						telaAdmin.setVisible(true);
						telaAdmin.setLocationRelativeTo(null);
						setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuario ja existente!");
				}

			} catch (Exception e) {
				System.out.println("Ok, deu algum erro ai luan");
				e.printStackTrace();

			}
		}
	}

	public void editarPessoa(int id) {
		int achou = 0;

		// Recuperando a senha do campo
		char[] senhaDigitada = textSenha.getPassword();
		char[] senhaRepetida = textRepitaSenha.getPassword();
		String senhaUser = new String(senhaDigitada);
		String senhaUserRepetida = new String(senhaRepetida);

		// Limpando variaveis char
		Arrays.fill(senhaDigitada, ' ');
		Arrays.fill(senhaRepetida, ' ');

		// Recuperando campo Cpf e formatando
		String cpfUser = textCpf.getText().replace(".", "").replace("-", "");

		// Recuperando campo telefone e formatando
		String telefoneUser = textTelefone.getText().replace("(", "").replace("-", "").replace(")", "");

		// Recuperando campo data nascimento
		boolean dataNascimentoValida = false;
		try {
			dataNascimentoValida = dateFormatter.validarDataNascimento(textDataNascimento.getText());
		} catch (com.google.protobuf.TextFormat.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean emailValida = false;

		try {
			emailValidatorUtil = new EmailValidatorUtil();
			emailValida = emailValidatorUtil.validar(textEmail.getText());
		} catch (Exception e) {
			System.out.println("DEU ERRO NO VALIDADOR DE EMAIL ");
		}

		// Validações dos campos do formulario
		if (textNome.getText().isBlank() == true || textNome.getText().length() > 70) {

			JOptionPane.showMessageDialog(null, "Campo nome vazio ou acima de 70 caracteres!");
			achou = 1;
			return;
		}
		if (ValidaCpfUtil.verificarFormatoCPF(cpfUser) == false) {

			JOptionPane.showMessageDialog(null, "Campo CPF vazio ou CPF informado é inválido!");
			achou = 1;
			return;

		}
		if (dataNascimentoValida == false) {

			JOptionPane.showMessageDialog(null, "Data de nascimento inválida !");
			achou = 1;
			return;
		}
		if (telefoneUser.isBlank() == true || telefoneUser.length() != 11) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor, informe um telefone valido!");
			return;
		}
		if (textEmail.getText().isBlank() == true || textEmail.getText().indexOf("@") == -1
				|| textEmail.getText().length() > 200
				|| textEmail.getText().indexOf("@") >= textEmail.getText().length() - 1 || emailValida == false) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Por favor informe um e-mail valido");
			return;
		}
		if (textRepEmail.getText().isBlank() == true || textRepEmail.getText().equals(textEmail.getText()) == false
				|| textEmail.getText().length() > 200 || emailValida == false) {

			achou = 1;
			JOptionPane.showMessageDialog(null, "Os dois e-mails precisam ser iguais!");
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
			System.out.println(id);
			PessoaEntity pessoaEntity = new PessoaEntity();
			pessoaService = new PessoaService();
			pessoaEntity.setId(id);
			pessoaEntity.setNomeCompleto(textNome.getText());
			pessoaEntity.setCpf(cpfUser);
			pessoaEntity.setDataNascimento(dateFormatter.converterParaData(textDataNascimento.getText()));
			pessoaEntity.setEmail(textEmail.getText());
			pessoaEntity.setTelefone(telefoneUser);
			pessoaEntity.setSenha(senhaUser);
			pessoaEntity.setAdm(rdbAdm.isSelected());
			try {

				boolean usuarioExistente = pessoaService.salvar(pessoaEntity);
				if (usuarioExistente == false) {
					JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
					TelaRelatorio telaRelatorio = new TelaRelatorio();
					telaRelatorio.setVisible(true);
					telaRelatorio.setLocationRelativeTo(null);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "deu erro");
				}

			} catch (Exception e) {
				System.out.println("Ok, deu algum erro ai luan");
				e.printStackTrace();

			}
		}
	}
}
