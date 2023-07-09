package softCoin.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import softCoin.entity.BordaEntity;
import softCoin.entity.MaterialEntity;
import softCoin.entity.PaisEntity;
import softCoin.service.BordaService;
import softCoin.service.MaterialService;
import softCoin.service.PaisService;
import softCoin.util.CaixaConfirmacaoUtil;

public class TelaCadastrosGerais extends JFrame {

	private JPanel contentPane;
	private JTextField textCadastro;
	private JComboBox cbTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TelaCadastrosGerais frame = new TelaCadastrosGerais(0, "", 0);
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
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaCadastrosGerais(int i, String nome, int tipo) {
		setTitle("Cadastros Gerais");

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 711, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Cadastros Gerais");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(245, 0, 190, 64);
		contentPane.add(lblTitulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(245, 62, 190, 2);
		contentPane.add(separator);

		textCadastro = new JTextField();
		textCadastro.setBounds(60, 108, 465, 37);
		contentPane.add(textCadastro);
		textCadastro.setColumns(10);
		textCadastro.setText(nome);

		cbTipo = new JComboBox();
		cbTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbTipo.setModel(new DefaultComboBoxModel(new String[] { "Borda", "Material", "País" }));
		cbTipo.setBounds(524, 107, 93, 38);
		cbTipo.setSelectedIndex(tipo);
		if (i != 0) {
			cbTipo.setEnabled(false);
		}
		contentPane.add(cbTipo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica se o cadastro possui id ou se é um novo cadastro
				if (i == 0) {
					salvar();
				} else {
					String nome = textCadastro.getText();
					editar(i, nome, tipo);
				}
			}
		});
		btnSalvar.setBounds(480, 259, 113, 37);
		contentPane.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Função Limpar aqui
				textCadastro.setText("");
			}
		});
		btnLimpar.setBounds(333, 259, 113, 37);
		contentPane.add(btnLimpar);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					TelaAdmin admin = new TelaAdmin();
					admin.setVisible(true);
					admin.setLocationRelativeTo(null);
					setVisible(false);
				}
			}
		});
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-back-arrow-50.png"));
		btnVoltar.setRolloverEnabled(false);
		btnVoltar.setBounds(21, 11, 40, 37);
		contentPane.add(btnVoltar);

		JButton btnAjuda = new JButton("");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaixaConfirmacaoUtil.chamarTelaAjuda(
						"\"Dica: Para inserir informações utilizando uma Caixa de Seleção siga estas instruções:\r\n"
								+ "\r\n"
								+ "Selecione o tipo de informação a ser inserida: Na Caixa de Seleção, escolha o tipo de informação que você deseja inserir. Por exemplo, se você deseja inserir o país de origem da moeda, selecione 'País' na Caixa de Seleção.\r\n"
								+ "\r\n"
								+ "Insira os detalhes: Após selecionar o tipo de informação, uma Caixa de Texto correspondente será exibida. Digite os detalhes relevantes nessa Caixa de Texto. Por exemplo, se você selecionou 'País', digite o nome do país de origem da moeda.\r\n"
								+ "\r\n"
								+ "Salvar os detalhes: Clique no botão 'Salvar' para armazenar as informações inseridas. Certifique-se de que todos os campos necessários sejam preenchidos corretamente antes de salvar.\r\n"
								+ "\r\n"
								+ "Limpar campos: Se desejar começar novamente ou corrigir um erro, você pode clicar no botão 'Limpar' para remover todos os dados inseridos e reiniciar o formulário.\r\n"
								+ "\r\n"
								+ "Lembre-se de selecionar corretamente o tipo de informação na Caixa de Seleção antes de inserir os detalhes correspondentes na Caixa de Texto. Isso garantirá que as informações sejam armazenadas corretamente no sistema.\r\n"
								+ "\r\n"
								+ "Caso tenha dúvidas ou encontre dificuldades durante o processo de inserção de informações, não hesite em entrar em contato com nossa equipe de suporte Através do e-mail:suporteCoin@gmail.com para obter assistência adicional.");
			}
		});
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-help-30.png"));
		btnAjuda.setRolloverEnabled(false);
		btnAjuda.setBounds(615, 11, 40, 37);
		contentPane.add(btnAjuda);
	}

	public void salvar() {
		// Recolhendo opção de cadastro do comboBox
		String selecionado = cbTipo.getSelectedItem().toString();
		// Recolhendo dados do campo texto
		String cadastro = textCadastro.getText().trim();
		switch (selecionado) {

		case "Material":
			if (cadastro.isBlank() == false && cadastro.length() <= 50) {
				MaterialEntity materialEntity = new MaterialEntity();
				MaterialService materialService = new MaterialService();
				materialEntity.setNomeMaterial(cadastro);
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Salvar material ? ") == JOptionPane.YES_OPTION) {
					boolean cadastrou = materialService.salvar(materialEntity);
					if (cadastrou == false) {
						JOptionPane.showMessageDialog(null, "Material já cadastrado!");
					} else {
						JOptionPane.showMessageDialog(null, "Material cadastrado com sucesso!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, digite um material antes!");
			}
			break;
		case "Borda":
			if (cadastro.isBlank() == false && cadastro.length() <= 50) {
				BordaEntity bordaEntity = new BordaEntity();
				BordaService bordaService = new BordaService();
				bordaEntity.setTipoBorda(cadastro);
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Salvar Borda ? ") == JOptionPane.YES_OPTION) {
					boolean cadastrou = bordaService.salvar(bordaEntity);
					if (cadastrou == false) {
						JOptionPane.showMessageDialog(null, "Borda já cadastrada!");
					} else {
						JOptionPane.showMessageDialog(null, "Borda cadastrada com sucesso!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, digite uma Borda antes!");
			}
			break;

		case "País":
			if (cadastro.isBlank() == false && cadastro.length() <= 50) {
				PaisEntity paisEntity = new PaisEntity();
				PaisService paisService = new PaisService();
				paisEntity.setNomePais(cadastro);
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Salvar País ? ") == JOptionPane.YES_OPTION) {
					boolean cadastrou = paisService.salvar(paisEntity);
					if (cadastrou == false) {
						JOptionPane.showMessageDialog(null, "País já cadastrado!");
					} else {
						JOptionPane.showMessageDialog(null, "País cadastrado com sucesso!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, digite um País antes!");
			}

			break;

		default:

		}
	}

	public void editar(int i, String nome, int tipo) {

		switch (tipo) {

		case 0:
			if (nome.isBlank() == false && nome.length() <= 50) {
				BordaEntity bordaEntity = new BordaEntity();
				BordaService bordaService = new BordaService();
				bordaEntity.setId(i);
				bordaEntity.setTipoBorda(nome);
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Salvar edições ? ") == JOptionPane.YES_OPTION) {
					boolean cadastrou = bordaService.salvar(bordaEntity);
					if (cadastrou == false) {
						JOptionPane.showMessageDialog(null, "Borda já cadastrada!");
					} else {
						JOptionPane.showMessageDialog(null, "Borda atualizada com sucesso!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, digite uma Borda antes!");
			}
			break;

		case 1:
			if (nome.isBlank() == false && nome.length() <= 50) {
				MaterialEntity materialEntity = new MaterialEntity();
				MaterialService materialService = new MaterialService();
				materialEntity.setId(i);
				materialEntity.setNomeMaterial(nome);
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Salvar edições ? ") == JOptionPane.YES_OPTION) {
					boolean cadastrou = materialService.salvar(materialEntity);
					if (cadastrou == false) {
						JOptionPane.showMessageDialog(null, "Material já cadastrado!");
					} else {
						JOptionPane.showMessageDialog(null, "Material atualizado com sucesso!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, digite um material antes!");
			}
			break;

		case 2:
			if (nome.isBlank() == false && nome.length() <= 50) {
				PaisEntity paisEntity = new PaisEntity();
				PaisService paisService = new PaisService();
				paisEntity.setId(i);
				paisEntity.setNomePais(nome);
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Salvar edições ? ") == JOptionPane.YES_OPTION) {
					boolean cadastrou = paisService.salvar(paisEntity);
					if (cadastrou == false) {
						JOptionPane.showMessageDialog(null, "País já cadastrado!");
					} else {
						JOptionPane.showMessageDialog(null, "País atualizado com sucesso!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, digite um País antes!");
			}
			break;

		default:

		}
	}
}
