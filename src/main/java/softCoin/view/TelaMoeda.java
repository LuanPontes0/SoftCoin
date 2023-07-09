package softCoin.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import softCoin.entity.BordaEntity;
import softCoin.entity.MaterialEntity;
import softCoin.entity.MoedaEntity;
import softCoin.entity.PaisEntity;
import softCoin.service.BordaService;
import softCoin.service.MaterialService;
import softCoin.service.MoedaService;
import softCoin.service.PaisService;
import softCoin.util.CaixaConfirmacaoUtil;
import softCoin.util.FormataValorUtil;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TelaMoeda extends JFrame {

	private JPanel contentPane;
	private JTextField textTitulo;
	private JFormattedTextField textAnoFace;
	private JTextField textValorFace;
	private JTextField textCatalogo;
	private JFormattedTextField textPeso;
	private JFormattedTextField textEspessura;
	private JFormattedTextField textDiametro;
	private List<BordaEntity> bordas;
	private List<MaterialEntity> materiais;
	private List<PaisEntity> paises;
	private BordaService bordaService;
	private MaterialService materialService;
	private PaisService paisService;
	private DefaultComboBoxModel<BordaEntity> bordasSelecionadas;
	private DefaultComboBoxModel<MaterialEntity> materiaisSelecionados;
	private JComboBox<PaisEntity> cbPais;
	private JComboBox<BordaEntity> cbBorda;
	private JComboBox<MaterialEntity> cbMaterial;
	private JComboBox<MaterialEntity> cbMateriaisSelecionados;
	private JComboBox<BordaEntity> cbBordasSelecionadas;
	private List<BordaEntity> bordasMoeda;
	private List<MaterialEntity> materiaisMoeda;
	private MoedaService moedaService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoedaEntity moedaEntity = new MoedaEntity(0, null, 0, 0, null, null, null, null, 0, 0, 0, null,
							null);
					TelaMoeda frame = new TelaMoeda(moedaEntity);
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
	public TelaMoeda(MoedaEntity moedaEntity) throws ParseException {
		setTitle("Cadastrar Moeda");
		// Inicia os services e listas
		try {
			moedaService = new MoedaService();
			bordaService = new BordaService();
			materialService = new MaterialService();
			paisService = new PaisService();
			bordas = bordaService.listar();
			materiais = materialService.listar();
			paises = paisService.listar();
			bordasMoeda = new ArrayList<>();
			materiaisMoeda = new ArrayList<>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
						"Deseja mesmo sair ? alterações não serão salvas!") == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 664, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel("Cadastrar Moeda");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLogo.setBounds(237, 11, 177, 25);
		contentPane.add(lblLogo);

		JSeparator separator = new JSeparator();
		separator.setBounds(164, 47, 317, 2);
		contentPane.add(separator);

		JLabel lblTitulo = new JLabel("Título: ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(15, 78, 62, 14);
		contentPane.add(lblTitulo);

		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTitulo.setBounds(70, 77, 323, 20);
		contentPane.add(textTitulo);
		textTitulo.setColumns(10);
		textTitulo.setText(moedaEntity.getTitulo());

		JLabel lblAnoFace = new JLabel("Ano de Face:");
		lblAnoFace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAnoFace.setBounds(403, 78, 110, 14);
		contentPane.add(lblAnoFace);

		textAnoFace = new JFormattedTextField(new MaskFormatter("####"));
		textAnoFace.setColumns(10);
		textAnoFace.setBounds(516, 78, 98, 20);
		contentPane.add(textAnoFace);
		textAnoFace.setText(Integer.toString(moedaEntity.getAnoFace()));

		JLabel lblValorDeFace = new JLabel("Valor de Face:");
		lblValorDeFace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorDeFace.setBounds(10, 124, 118, 20);
		contentPane.add(lblValorDeFace);

		textValorFace = new JTextField();
		if (moedaEntity.getValorFace() != 0) {
			// textValorFace.setText(FormataValorUtil.getCurrency(moedaEntity.getValorFace()));
			textValorFace.setText((Double.toString(moedaEntity.getValorFace())));

		}
		/*
		 * textValorFace.addFocusListener(new FocusAdapter() {
		 * 
		 * @Override public void focusLost(FocusEvent e) { if
		 * (textValorFace.getText().isBlank() != true) { String valorAtual =
		 * textValorFace.getText().replace("$", "").replace(",", ".").replace("R", "")
		 * .trim().replaceAll("[^\\d.]", "");
		 * 
		 * if (valorAtual.isEmpty() != true) { int indexUltimoPonto =
		 * valorAtual.lastIndexOf(".");
		 * 
		 * if (indexUltimoPonto != -1) { valorAtual = valorAtual.replace(".",
		 * "").substring(0, indexUltimoPonto) + valorAtual.substring(indexUltimoPonto);
		 * } if (valorAtual.matches(".*[a-zA-Z].*") != true) {
		 * textValorFace.setText(FormataValorUtil.getCurrency(Double.parseDouble(
		 * valorAtual))); } else { textValorFace.setText(""); }
		 * System.out.println(valorAtual); } }
		 * 
		 * } });
		 */

		textValorFace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textValorFace.setColumns(10);
		textValorFace.setBounds(131, 127, 67, 20);

		contentPane.add(textValorFace);

		JLabel lblTipoDaBorda = new JLabel("Tipo Da Borda:");
		lblTipoDaBorda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDaBorda.setBounds(208, 122, 134, 25);
		contentPane.add(lblTipoDaBorda);

		// Popula combo box bordas
		DefaultComboBoxModel<BordaEntity> listaBordas = new DefaultComboBoxModel<>();
		for (BordaEntity bordaEntity : bordas) {

			listaBordas.addElement(bordaEntity);

		}

		cbBorda = new JComboBox<>(listaBordas);

		// Seleciona bordas e injeta na lista de bordas selecionadas

		cbBorda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbBorda.setBounds(334, 126, 179, 22);
		contentPane.add(cbBorda);

		JLabel lblTipoDoMaterial = new JLabel("Tipo Do Material:");
		lblTipoDoMaterial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoDoMaterial.setBounds(10, 174, 141, 25);
		contentPane.add(lblTipoDoMaterial);

		// Popula combo box materiais
		DefaultComboBoxModel<MaterialEntity> listaMateriais = new DefaultComboBoxModel<>();
		for (MaterialEntity materialEntity : materiais) {
			listaMateriais.addElement(materialEntity);

		}

		cbMaterial = new JComboBox<>(listaMateriais);

		cbMaterial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbMaterial.setBounds(161, 178, 179, 22);
		contentPane.add(cbMaterial);

		JLabel lblCatalogo = new JLabel("Código do catálogo:");
		lblCatalogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCatalogo.setBounds(246, 234, 164, 25);
		contentPane.add(lblCatalogo);

		textCatalogo = new JTextField();
		textCatalogo.setColumns(10);
		textCatalogo.setBounds(412, 239, 177, 20);
		contentPane.add(textCatalogo);
		textCatalogo.setText(moedaEntity.getCodCatalogo());

		JLabel lblPais = new JLabel("País:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPais.setBounds(10, 234, 37, 25);
		contentPane.add(lblPais);

		// Popula combo box paises
		DefaultComboBoxModel<PaisEntity> listaPaises = new DefaultComboBoxModel<>();
		for (PaisEntity paisEntity : paises) {
			listaPaises.addElement(paisEntity);

		}
		cbPais = new JComboBox<>(listaPaises);

		cbPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbPais.setBounds(57, 238, 179, 22);
		cbPais.setSelectedItem(moedaEntity.getPaisOrigem());
		contentPane.add(cbPais);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeso.setBounds(422, 174, 43, 25);
		contentPane.add(lblPeso);

		textPeso = new JFormattedTextField(new MaskFormatter("###.## g"));
		textPeso.setColumns(10);
		textPeso.setBounds(475, 179, 122, 20);
		textPeso.setText(Double.toString(moedaEntity.getPeso()));
		contentPane.add(textPeso);
		System.out.println(moedaEntity.getPeso());
		System.out.println(Double.toString(moedaEntity.getPeso()));

		JLabel lblEspessura = new JLabel("Espessura:");
		lblEspessura.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEspessura.setBounds(219, 286, 90, 25);
		contentPane.add(lblEspessura);

		textEspessura = new JFormattedTextField(new MaskFormatter("###.## mm"));
		textEspessura.setColumns(10);
		textEspessura.setBounds(304, 291, 110, 20);
		contentPane.add(textEspessura);
		textEspessura.setText(Double.toString(moedaEntity.getEspessura()));

		JLabel lblDiametro = new JLabel("diâmetro:");
		lblDiametro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiametro.setBounds(10, 286, 78, 25);
		contentPane.add(lblDiametro);

		textDiametro = new JFormattedTextField(new MaskFormatter("###.## mm"));

		textDiametro.setColumns(10);
		textDiametro.setBounds(88, 291, 110, 20);
		contentPane.add(textDiametro);
		textDiametro.setText(Double.toString(moedaEntity.getDiametro()));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 334, 628, 2);
		contentPane.add(separator_1);

		JButton btnSalvar = new JButton("Salvar");
		// Ativado ao clicar no botão salvar
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (moedaEntity.getId() == 0) {
					salvaMoeda(0);
				} else {
					salvaMoeda(moedaEntity.getId());
				}
			}
		});
		btnSalvar.setBounds(502, 367, 112, 40);
		contentPane.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(369, 367, 112, 40);
		contentPane.add(btnLimpar);

		JButton btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
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
		btnSair.setContentAreaFilled(false);
		btnSair.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-back-arrow-50.png"));
		btnSair.setBounds(20, 11, 40, 32);
		contentPane.add(btnSair);

		JButton btnAjuda = new JButton("");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaixaConfirmacaoUtil.chamarTelaAjuda("Cadastro de Moedas\r\n" + "\r\n"
						+ "Título: [Campo de texto para inserir o título da moeda]\r\n" + "\r\n"
						+ "Ano de Face: [Campo numérico para inserir o ano de face da moeda]\r\n" + "\r\n"
						+ "Valor de Face (R$): [Campo numérico para inserir o valor de face da moeda]\r\n" + "\r\n"
						+ "Tipo de Borda: [Caixa de seleção (ComboBox) com opções de tipo de borda disponíveis]\r\n"
						+ "\r\n"
						+ "Borda Selecionados: [Caixa de seleção (ComboBox) para exibir os tipos de borda selecionados]\r\n"
						+ "\r\n" + "[Botão \"+\" para adicionar um tipo de borda]\r\n"
						+ "[Botão \"-\" para remover um tipo de borda]\r\n" + "\r\n"
						+ "Tipo de Material: [Caixa de seleção (ComboBox) com opções de tipo de material disponíveis]\r\n"
						+ "\r\n"
						+ "Materiais selecionados : [Caixa de seleção (ComboBox) para exibir os tipos de Materiais selecionados ]\r\n"
						+ "\r\n" + "\r\n" + "[Botão \"+\" para adicionar um tipo de material]\r\n"
						+ "[Botão \"-\" para remover um tipo de material]\r\n" + "\r\n"
						+ "Peso da Moeda (g): [Campo numérico para inserir o peso da moeda]\r\n" + "\r\n"
						+ "País: [Caixa de seleção (ComboBox) para selecionar o país de origem da moeda]\r\n" + "\r\n"
						+ "Código de Catálogo: [Campo de texto para inserir o código de catálogo da moeda]\r\n" + "\r\n"
						+ "Diâmetro (mm): [Campo numérico para inserir o diâmetro da moeda]\r\n" + "\r\n"
						+ "Espessura (mm): [Campo numérico para inserir a espessura da moeda]\r\n" + "\r\n"
						+ "[Botão \"Limpar\" para limpar todos os campos do formulário]\r\n" + "\r\n"
						+ "Certifique-se de preencher todos os campos relevantes com precisão e fornecer as informações corretas sobre a moeda que deseja cadastrar. Utilize campos numéricos para o ano de face e valor de face, e selecione as opções apropriadas nos campos de caixa de seleção para o tipo de borda, tipo de material e país.\r\n"
						+ "\r\n"
						+ "Após preencher todos os campos, você pode clicar em um botão \"Salvar\" para salvar as informações da moeda no sistema."
						+ "\r\n" + "\r\n"
						+ "Caso tenha dúvidas ou encontre dificuldades durante o processo de inserção de informações, não hesite em entrar em contato com nossa equipe de suporte Através do e-mail:suporteCoin@gmail.com para obter assistência adicional.\r\n");
			}
		});
		btnAjuda.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-help-30.png"));
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setBounds(565, 286, 32, 27);
		contentPane.add(btnAjuda);

		bordasSelecionadas = new DefaultComboBoxModel<>();
		cbBordasSelecionadas = new JComboBox<>(bordasSelecionadas);

		// carrega bordas da moeda no modo edição
		if (moedaEntity.getBordas() != null) {
			System.out.println(moedaEntity.getBordas());
			for (BordaEntity bordaEntity : moedaEntity.getBordas()) {
				bordasSelecionadas.addElement(bordaEntity);
				bordasMoeda.add(bordaEntity);
			}
		}

		cbBordasSelecionadas.setBounds(15, 376, 155, 22);
		contentPane.add(cbBordasSelecionadas);

		materiaisSelecionados = new DefaultComboBoxModel<>();
		cbMateriaisSelecionados = new JComboBox<>(materiaisSelecionados);

		// carrega materiais da moeda no modo edição
		if (moedaEntity.getMateriais() != null) {
			System.out.println(moedaEntity.getMateriais());
			for (MaterialEntity materialEntity : moedaEntity.getMateriais()) {
				materiaisSelecionados.addElement(materialEntity);
				materiaisMoeda.add(materialEntity);
			}
		}

		cbMateriaisSelecionados.setBounds(185, 376, 141, 22);
		contentPane.add(cbMateriaisSelecionados);

		JLabel lblBordas = new JLabel("Bordas Selecionadas");
		lblBordas.setBounds(15, 351, 121, 14);
		contentPane.add(lblBordas);

		JLabel lblMateriais = new JLabel("Materiais selecionados");
		lblMateriais.setBounds(185, 351, 141, 14);
		contentPane.add(lblMateriais);

		JButton btnRemoverBorda = new JButton("-");
		btnRemoverBorda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					BordaEntity bordaRemovida = (BordaEntity) cbBordasSelecionadas.getSelectedItem();
					if (bordaRemovida != null) {
						bordasSelecionadas.removeElement(bordaRemovida);
						bordasMoeda.remove(bordaRemovida);

					} else {
						JOptionPane.showMessageDialog(null, "Você não possui nenhuma borda para remover");
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnRemoverBorda.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemoverBorda.setBounds(57, 403, 46, 23);
		contentPane.add(btnRemoverBorda);

		JButton btnRemoverMaterial = new JButton("-");
		btnRemoverMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MaterialEntity materialRemovido = (MaterialEntity) cbMateriaisSelecionados.getSelectedItem();
					if (materialRemovido != null) {
						materiaisSelecionados.removeElement(materialRemovido);
						materiaisMoeda.remove(materialRemovido);
					} else {
						JOptionPane.showMessageDialog(null, "Você não possui nenhuma material para remover");
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnRemoverMaterial.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemoverMaterial.setBounds(234, 403, 46, 23);
		contentPane.add(btnRemoverMaterial);

		JButton btnAdicionarMaterial = new JButton("+");
		btnAdicionarMaterial.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionarMaterial.setBounds(350, 178, 46, 23);
		btnAdicionarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaterialEntity materialSelecionado = (MaterialEntity) cbMaterial.getSelectedItem();
				if (materiaisSelecionados.getIndexOf(materialSelecionado) == -1) {
					materiaisSelecionados.addElement(materialSelecionado);
					materiaisSelecionados.setSelectedItem(materialSelecionado);
					materiaisMoeda.add(materialSelecionado);
				} else {
					JOptionPane.showMessageDialog(null, "Este material já foi adicionado!");
				}

			}
		});
		contentPane.add(btnAdicionarMaterial);

		JButton btnAdicionarBorda = new JButton("+");
		btnAdicionarBorda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BordaEntity bordaSelecionada = (BordaEntity) cbBorda.getSelectedItem();
				if (bordasSelecionadas.getIndexOf(bordaSelecionada) == -1) {
					bordasSelecionadas.addElement(bordaSelecionada);
					bordasSelecionadas.setSelectedItem(bordaSelecionada);
					bordasMoeda.add(bordaSelecionada);
				} else {
					JOptionPane.showMessageDialog(null, "Esta borda já foi adicionada!");
				}

			}
		});
		btnAdicionarBorda.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionarBorda.setBounds(528, 126, 46, 23);
		contentPane.add(btnAdicionarBorda);
	}

	public void salvaMoeda(long id) {
		int erro = 0;

		MoedaEntity moeda = new MoedaEntity();
		String diametro = textDiametro.getText().replace(".", "").replace("m", "");
		String espessura = textEspessura.getText().replace(".", "").replace("m", "");
		String peso = textPeso.getText().replace(".", "").replace("g", "");

		if (textTitulo.getText().isBlank() == true || textTitulo.getText().length() > 100) {
			JOptionPane.showMessageDialog(null, "Campo titulo vazio ou acima de 100 caracteres");
			erro = 1;
			return;

		}
		if (textAnoFace.getText().isBlank() == true) {
			JOptionPane.showMessageDialog(null, "Por favor insira um ano de face para a moeda");
			erro = 1;
			return;
		}
		if (textValorFace.getText().isBlank() == true) {

			JOptionPane.showMessageDialog(null, "Por favor insira um valor de face para a moeda");
			erro = 1;
			return;
		}
		if (textCatalogo.getText().isBlank() == true || textCatalogo.getText().length() > 50) {
			JOptionPane.showMessageDialog(null, "código de catálogo vazio ou acima de 50 caracteres");
			erro = 1;
			return;
		}
		if (peso.isBlank() == true) {
			JOptionPane.showMessageDialog(null, "Por favor insira um peso para a moeda");
			erro = 1;
			return;
		}
		if (espessura.isBlank() == true) {
			JOptionPane.showMessageDialog(null, "Por favor insira uma espessura para a moeda");
			erro = 1;
			return;
		}
		if (diametro.isBlank() == true) {
			JOptionPane.showMessageDialog(null, "Por favor insira um diametro para a moeda");
			erro = 1;
			return;
		}
		if (bordasMoeda.isEmpty() == true) {
			JOptionPane.showMessageDialog(null, "Por favor insira uma borda para a moeda");
			erro = 1;
			return;
		}
		if (materiaisMoeda.isEmpty() == true) {
			JOptionPane.showMessageDialog(null, "Por favor insira um material para a moeda");
			erro = 1;
			return;
		}
		String mensagem = "Deseja Salvar a moeda ?";
		if (erro == 0) {
			if (id != 0) {
				moeda.setId(id);
				mensagem = "Deseja Salvar as alterações?";
			}
			moeda.setTitulo(textTitulo.getText().trim());
			moeda.setAnoFace(Integer.parseInt(textAnoFace.getText()));
			moeda.setValorFace(Double.parseDouble(textValorFace.getText()));
			moeda.setCodCatalogo(textCatalogo.getText());
			moeda.setPaisOrigem((PaisEntity) cbPais.getSelectedItem());
			moeda.setBordas(bordasMoeda);
			moeda.setMateriais(materiaisMoeda);
			moeda.setDiametro(Double.parseDouble(diametro));
			moeda.setEspessura(Double.parseDouble(espessura));
			moeda.setPeso(Double.parseDouble(peso));
			if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(mensagem) == JOptionPane.YES_OPTION) {
				boolean salvou = moedaService.salvar(moeda);
				if (salvou == true) {
					if (id == 0) {
						JOptionPane.showMessageDialog(null, "Moeda Salvada com sucesso!");
						TelaRelatorio telaRelatorio = new TelaRelatorio();
						telaRelatorio.setVisible(true);
						telaRelatorio.setLocationRelativeTo(null);
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Moeda atualizada com sucesso!");
						TelaRelatorio telaRelatorio = new TelaRelatorio();
						telaRelatorio.setVisible(true);
						telaRelatorio.setLocationRelativeTo(null);
						setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar a moeda!");
				}

			}
		}

	}

	public void limparCampos() {

		if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Deseja limpar os campos ?") == JOptionPane.YES_OPTION) {
			textTitulo.setText("");
			textAnoFace.setText("");
			textValorFace.setText("");
			textCatalogo.setText("");
			textDiametro.setText("");
			textEspessura.setText("");
			textPeso.setText("");
			cbBordasSelecionadas.removeAllItems();
			cbMateriaisSelecionados.removeAllItems();
			bordasMoeda.clear();
			materiaisMoeda.clear();
		} else {
			return;
		}
	}
}
