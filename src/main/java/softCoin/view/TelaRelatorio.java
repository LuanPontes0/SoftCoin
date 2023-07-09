package softCoin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import softCoin.entity.BordaEntity;
import softCoin.entity.MaterialEntity;
import softCoin.entity.MoedaEntity;
import softCoin.entity.PaisEntity;
import softCoin.entity.PessoaEntity;
import softCoin.service.BordaService;
import softCoin.service.MaterialService;
import softCoin.service.MoedaService;
import softCoin.service.PaisService;
import softCoin.service.PessoaService;
import softCoin.util.CaixaConfirmacaoUtil;
import softCoin.util.DateFormatter;

public class TelaRelatorio extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private PaisService paisService;
	private MaterialService materialService;
	private BordaService bordaService;
	private PessoaService pessoaService;
	private MoedaService moedaService;
	private JComboBox cbTipoRelatorio;
	private JScrollPane scrollPane;
	private DefaultTableModel defaultTableModel;
	private int relatorioAtual;
	private DateFormatter dateFormatter;
	private boolean editarControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorio frame = new TelaRelatorio();
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
	public TelaRelatorio() {
		setTitle("Visualizar Cadastros ");
		try {

			dateFormatter = new DateFormatter();

		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao carregar o dateFormatter: " + e);
		}
		editarControl = false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 584);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(218, 218, 218));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 100, 844, 389);
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 824, 367);
		panel.add(scrollPane);

		JLabel lblLogo = new JLabel("SoftCoin");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblLogo.setBounds(24, 43, 112, 31);
		contentPane.add(lblLogo);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(146, 21, 2, 68);
		contentPane.add(separator_1);

		JTextField textPesquisa = new JTextField();
		textPesquisa.setBounds(275, 23, 436, 35);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);

		JComboBox comboFiltro = new JComboBox();
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] { "Ano fabricação" }));
		comboFiltro.setBackground(new Color(192, 192, 192));
		comboFiltro.setBounds(158, 23, 107, 35);
		contentPane.add(comboFiltro);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(742, 23, 112, 37);
		contentPane.add(btnPesquisar);

		JLabel lblAdmin = new JLabel("ADMIN");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAdmin.setBounds(52, 75, 57, 14);
		contentPane.add(lblAdmin);

		JButton btnVisualizarCad = new JButton("Exibir Cadastros");
		btnVisualizarCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gerarRelatorio();
				editarControl = false;

			}
		});
		btnVisualizarCad.setBounds(681, 500, 146, 34);
		contentPane.add(btnVisualizarCad);

		cbTipoRelatorio = new JComboBox();
		cbTipoRelatorio.setModel(
				new DefaultComboBoxModel(new String[] { "Usuários", "Moedas", "Bordas", "Materiais", "Países" }));
		cbTipoRelatorio.setBounds(498, 506, 164, 22);
		contentPane.add(cbTipoRelatorio);

		JLabel lblTipoCadastro = new JLabel("Categoria do cadastro:");
		lblTipoCadastro.setBounds(352, 510, 136, 14);
		contentPane.add(lblTipoCadastro);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table == null) {
					JOptionPane.showMessageDialog(null, "Por favor, gere um relatorio antes de utilizar esta função !");
				} else {
					inserirCadastro();
				}
			}
		});
		btnInserir.setBounds(20, 506, 89, 23);
		contentPane.add(btnInserir);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table == null) {
					JOptionPane.showMessageDialog(null, "Por favor, gere um relatorio antes de utilizar esta função !");
				} else {

					if (editarControl == false) {
						editarCadastro(table);
						editarControl = true;
					} else {
						JOptionPane.showMessageDialog(null,
								"A função editar já foi ativada, por favor, clique em um cadastro");

					}
				}

			}
		});
		btnEditar.setBounds(131, 506, 89, 23);
		contentPane.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table == null) {
					JOptionPane.showMessageDialog(null, "Por favor, gere um relatorio antes de utilizar esta função !");
				} else {
					removerCadastro(table);
				}

			}
		});
		btnExcluir.setBounds(239, 506, 89, 23);
		contentPane.add(btnExcluir);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdmin admin = new TelaAdmin();
				admin.setVisible(true);
				admin.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Luan\\Downloads\\icons8-back-arrow-50.png"));
		btnNewButton.setBounds(10, 11, 40, 33);
		contentPane.add(btnNewButton);

		gerarRelatorio();

	}

	public void gerarRelatorio() {

		switch (cbTipoRelatorio.getSelectedItem().toString()) {

		case "Usuários":// Preenche o Jtable com usuarios do Banco de dados

			defaultTableModel = new DefaultTableModel();
			defaultTableModel.addColumn("ID_Pessoa");
			defaultTableModel.addColumn("ADM");
			defaultTableModel.addColumn("CPF");
			defaultTableModel.addColumn("Data de Nascimento");
			defaultTableModel.addColumn("E-mail");
			defaultTableModel.addColumn("Nome Completo");
			defaultTableModel.addColumn("Telefone");
			defaultTableModel.addColumn("Senha");
			try {
				pessoaService = new PessoaService();

			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao carregar um service: " + e);
			}
			for (PessoaEntity pessoaEntity : pessoaService.listar()) {

				defaultTableModel.addRow(new Object[] { pessoaEntity.getId(), pessoaEntity.isAdm(),

						pessoaEntity.getCpf(), dateFormatter.formatarDataBR(pessoaEntity.getDataNascimento()),
						pessoaEntity.getEmail(), pessoaEntity.getNomeCompleto(), pessoaEntity.getTelefone(),
						pessoaEntity.getSenha() });

			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			relatorioAtual = 0;
			break;

		case "Moedas":// Preenche o Jtable com Moedas do banco de dados

			defaultTableModel = new DefaultTableModel();

			defaultTableModel.addColumn("ID_Moeda");
			defaultTableModel.addColumn("Ano de face");
			defaultTableModel.addColumn("Código do catálogo:");
			defaultTableModel.addColumn("Diâmetro");
			defaultTableModel.addColumn("Espessura");
			defaultTableModel.addColumn("Peso");
			defaultTableModel.addColumn("Título");
			defaultTableModel.addColumn("Valor da face");
			defaultTableModel.addColumn("País de origem");
			defaultTableModel.addColumn("Bordas");
			defaultTableModel.addColumn("Materiais");
			try {
				moedaService = new MoedaService();

			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao carregar um service: " + e);
			}

			for (MoedaEntity moedaEntity : moedaService.listar()) {

				defaultTableModel.addRow(new Object[] { moedaEntity.getId(), moedaEntity.getAnoFace(),
						moedaEntity.getCodCatalogo(), moedaEntity.getDiametro(), moedaEntity.getEspessura(),
						moedaEntity.getPeso(), moedaEntity.getTitulo(), moedaEntity.getValorFace(),
						moedaEntity.getPaisOrigem(), moedaEntity.getBordas(), moedaEntity.getMateriais() });

			}

			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			relatorioAtual = 1;
			break;

		case "Bordas":// Preenche o Jtable com Bordas do banco de dados

			defaultTableModel = new DefaultTableModel();

			defaultTableModel.addColumn("ID_Borda");
			defaultTableModel.addColumn("Nome");
			try {

				bordaService = new BordaService();

			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao carregar um service: " + e);
			}
			for (BordaEntity bordaEntity : bordaService.listar()) {

				defaultTableModel.addRow(new Object[] { bordaEntity.getId(), bordaEntity.getTipoBorda() });

			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			relatorioAtual = 2;
			break;

		case "Materiais":// Preenche o Jtable com Materiais do banco de dados

			defaultTableModel = new DefaultTableModel();

			defaultTableModel.addColumn("ID_Material");
			defaultTableModel.addColumn("Nome");
			try {

				materialService = new MaterialService();

			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao carregar um service: " + e);
			}
			for (MaterialEntity materialEntity : materialService.listar()) {

				defaultTableModel.addRow(new Object[] { materialEntity.getId(), materialEntity.getNomeMaterial() });

			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			relatorioAtual = 3;
			break;

		case "Países":// Preenche o Jtable com paises do Banco de dados

			defaultTableModel = new DefaultTableModel();

			defaultTableModel.addColumn("ID_Pais");
			defaultTableModel.addColumn("Nome");

			try {

				paisService = new PaisService();

			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao carregar um service: " + e);
			}
			for (PaisEntity paisEntity : paisService.listar()) {

				defaultTableModel.addRow(new Object[] { paisEntity.getId(), paisEntity.getNomePais() });

			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			relatorioAtual = 4;
			// editarCadastro(table);
			scrollPane.setViewportView(table);

			break;

		default:

			break;
		}

	}

	public void editarCadastro(JTable table) {

		switch (relatorioAtual) {
		case 0:

			// retorna o numero da linha selecionada
			int i = table.getSelectedRow();

			// Pega os dados da linha selecionada
			int id = (int) table.getValueAt(i, 0);
			boolean adm = (boolean) table.getValueAt(i, 1);
			String cpf = (String) table.getValueAt(i, 2);
			Date dataNascimento = dateFormatter.converterParaData((String) table.getValueAt(i, 3));
			String email = (String) table.getValueAt(i, 4);
			String nomeCompleto = (String) table.getValueAt(i, 5);
			String telefone = (String) table.getValueAt(i, 6);
			String senha = (String) table.getValueAt(i, 7);

			PessoaEntity pessoa = new PessoaEntity();

			pessoa.setId(id);
			pessoa.setAdm(adm);
			pessoa.setCpf(cpf);
			pessoa.setDataNascimento(dataNascimento);
			pessoa.setEmail(email);
			pessoa.setNomeCompleto(nomeCompleto);
			pessoa.setTelefone(telefone);
			pessoa.setSenha(senha);

			// Criando tela de edição de pessoa
			TelaCadastro cadastro = null;
			try {
				cadastro = new TelaCadastro(pessoa);
			} catch (ParseException e1) {

				e1.printStackTrace();
			}
			cadastro.setVisible(true);
			cadastro.setLocationRelativeTo(null);
			setVisible(false);

			break;

		case 1:

			// retorna o numero da linha selecionada
			int i1 = table.getSelectedRow();

			// Pega os dados da linha selecionada
			long id1 = (long) table.getValueAt(i1, 0);
			int anoFace = (int) table.getValueAt(i1, 1);
			String codCatalogo = (String) table.getValueAt(i1, 2);
			double diametro = (double) table.getValueAt(i1, 3);
			double espessura = (double) table.getValueAt(i1, 4);
			double peso = (double) table.getValueAt(i1, 5);
			String titulo = (String) table.getValueAt(i1, 6);
			double valorFace = (double) table.getValueAt(i1, 7);
			PaisEntity paisEntity = (PaisEntity) table.getValueAt(i1, 8);
			List<BordaEntity> bordasEntity = (List<BordaEntity>) table.getValueAt(i1, 9);
			List<MaterialEntity> materiaisEntity = (List<MaterialEntity>) table.getValueAt(i1, 10);

			// Cria a moeda com os atributos;
			MoedaEntity moedaEntity = new MoedaEntity(id1, titulo, anoFace, valorFace, bordasEntity, materiaisEntity,
					codCatalogo, paisEntity, peso, espessura, diametro, null, null);

			// Cria tela de edição de moedas
			try {
				TelaMoeda telaMoeda = new TelaMoeda(moedaEntity);
				telaMoeda.setVisible(true);
				telaMoeda.setLocationRelativeTo(null);
				setVisible(false);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case 2, 3, 4:

			// retorna o numero da linha selecionada
			int i2 = table.getSelectedRow();

			// Pega os dados da linha selecionada
			int id2 = (int) table.getValueAt(i2, 0);
			String nome = (String) table.getValueAt(i2, 1);

			int tipo = 0;
			// altera o valor tipo conforme o item selecionado no cbTipoRelatorio
			if (relatorioAtual == 2) {
				tipo = 0;
			}
			if (relatorioAtual == 3) {
				tipo = 1;
			}
			if (relatorioAtual == 4) {
				tipo = 2;
			}

			// gera a tela de cadastros gerais com os dados recolhidos e o tipo do cadastro
			TelaCadastrosGerais cadastrosGerais = new TelaCadastrosGerais(id2, nome, tipo);
			cadastrosGerais.setVisible(true);
			cadastrosGerais.setLocationRelativeTo(null);
			setVisible(false);

			break;

		default:
			break;
		}

	}

	public void inserirCadastro() {
		switch (relatorioAtual) {
		case 0:

			// Criando tela de edição de pessoa
			TelaCadastro cadastro = null;
			try {
				Date date = new Date();
				PessoaEntity pessoa = new PessoaEntity(0, null, null, date, null, null, null, false, null, null, null);
				cadastro = new TelaCadastro(pessoa);

			} catch (ParseException e1) {

				e1.printStackTrace();
			}
			cadastro.setVisible(true);
			cadastro.setLocationRelativeTo(null);
			setVisible(false);

			break;

		case 1:

			// Cria tela de edição de moedas
			try {
				MoedaEntity moedaEntity = new MoedaEntity(0, null, 0, 0, null, null, null, null, 0, 0, 0, null, null);
				TelaMoeda telaMoeda = new TelaMoeda(moedaEntity);
				telaMoeda.setVisible(true);
				telaMoeda.setLocationRelativeTo(null);
				setVisible(false);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case 2, 3, 4:

			int tipo = 0;
			// altera o valor tipo conforme o item selecionado no cbTipoRelatorio
			if (relatorioAtual == 2) {
				tipo = 0;
			}
			if (relatorioAtual == 3) {
				tipo = 1;
			}
			if (relatorioAtual == 4) {
				tipo = 2;
			}

			// gera a tela de cadastros gerais com os dados recolhidos e o tipo do cadastro
			TelaCadastrosGerais cadastrosGerais = new TelaCadastrosGerais(0, "", tipo);
			cadastrosGerais.setVisible(true);
			cadastrosGerais.setLocationRelativeTo(null);
			setVisible(false);

			break;

		default:
			break;
		}
	}

	public void removerCadastro(JTable table) {
		if (CaixaConfirmacaoUtil
				.chamarCaixaConfirmacao("Deseja mesmo excluir o cadastro selecionado?") == JOptionPane.YES_OPTION) {
			switch (relatorioAtual) {

			case 0:

				int i = table.getSelectedRow();
				int id = (int) table.getValueAt(i, 0);
				PessoaEntity pessoaEntity = new PessoaEntity();
				pessoaEntity.setId(id);
				pessoaService.deletar(pessoaEntity);
				gerarRelatorio();

				break;

			case 1:

				int i1 = table.getSelectedRow();
				long id1 = (long) table.getValueAt(i1, 0);
				MoedaEntity moedaEntity = new MoedaEntity();
				moedaEntity.setId(id1);
				moedaService.deletar(moedaEntity);
				gerarRelatorio();

				break;

			case 2:

				int i2 = table.getSelectedRow();
				int id2 = (int) table.getValueAt(i2, 0);
				BordaEntity bordaEntity = new BordaEntity();
				bordaEntity.setId(id2);
				bordaService.deletar(bordaEntity);
				gerarRelatorio();

				break;

			case 3:

				int i3 = table.getSelectedRow();
				int id3 = (int) table.getValueAt(i3, 0);
				MaterialEntity materialEntity = new MaterialEntity();
				materialEntity.setId(id3);
				materialService.deletar(materialEntity);
				gerarRelatorio();

				break;

			case 4:

				int i4 = table.getSelectedRow();
				int id4 = (int) table.getValueAt(i4, 0);
				PaisEntity paisEntity = new PaisEntity();
				paisEntity.setId(id4);
				paisService.deletar(paisEntity);
				gerarRelatorio();

				break;

			default:

				break;
			}
		}
	}
}
