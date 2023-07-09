package softCoin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import softCoin.entity.ColecaoEntity;
import softCoin.entity.ColecaoMoedaEntity;
import softCoin.entity.ColecaoMoedaIdEntity;
import softCoin.entity.NotaEntity;
import softCoin.entity.NotaMoedaEntity;
import softCoin.entity.NotaMoedaID;
import softCoin.entity.PessoaEntity;
import softCoin.service.ColecaoMoedaService;
import softCoin.service.ColecaoService;
import softCoin.service.MoedaService;
import softCoin.service.NotaService;
import softCoin.service.PessoaService;
import softCoin.util.CaixaConfirmacaoUtil;
import softCoin.util.DateFormatter;

public class TelaVenda extends JFrame {
	private JPanel contentPane;
	private JTable tbColecao;
	private JScrollPane scrollPane;
	private JTextField textPesquisa = new JTextField();
	private final JSeparator separator = new JSeparator();
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private PessoaService pessoaService;
	private ColecaoEntity colecaoAtual;
	private JScrollPane scrollPane_1;
	private MoedaService moedaService;
	private ColecaoMoedaService colecaoMoedaService;
	private JButton btnAdicionarCarrinho = new JButton("Adicionar Ao Carrinho");
	private JButton btnEditarMoeda = new JButton("Editar Moeda");
	private JButton btnRemoverMoeda = new JButton("Remover Moeda");
	private JButton btnVoltar;
	private JComboBox comboFiltro = new JComboBox();
	private List<ColecaoMoedaEntity> colecaoMoedaEntities = new ArrayList<>();
	private JButton btnPesquisar = new JButton("Pesquisar");
	private JLabel lblMoedasDisponiveis = new JLabel("Moedas Disponíveis");
	private ColecaoService colecaoService = new ColecaoService();
	private List<ColecaoMoedaEntity> carrinho = new ArrayList<>();
	private JTextField textValor = new JTextField();
	private JTextField textMoedaSelecionada = new JTextField();
	private JLabel lblMoedaSelecionada = new JLabel("Moeda Selecionada");
	private JLabel lblValor = new JLabel("Valor:");
	private JLabel lblQuantidade = new JLabel("Quantidade:");
	private JSpinner spnQuantidade = new JSpinner();
	private final JLabel lblMinhasCompras = new JLabel("Minhas Compras");
	private JTextField textQtdTotalCarrinho = new JTextField();
	private JTextField textValorTotalCarrinho = new JTextField();
	private JLabel lblValorTotalCarrinho = new JLabel("Valor Total:");
	JLabel lblQtdTotalCarrinho = new JLabel("Total de Itens:");
	private NotaService notaService = new NotaService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaColecao frame = new TelaColecao(null);
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

	public TelaVenda(PessoaEntity pessoa) {
		setTitle("Loja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 576);
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

		scrollPane_1 = new JScrollPane();

		scrollPane_1.setBounds(10, 11, 798, 367);
		panel.add(scrollPane_1);
		// populaCbTitulo(pessoa);

		controleTelas(pessoa, 1);

		textPesquisa.setBounds(178, 23, 436, 35);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);

		comboFiltro.setModel(new DefaultComboBoxModel(new String[] { "Ano fabricação" }));
		comboFiltro.setBackground(new Color(192, 192, 192));
		comboFiltro.setBounds(80, 23, 88, 35);
		contentPane.add(comboFiltro);

		btnPesquisar.setBounds(624, 22, 112, 37);
		contentPane.add(btnPesquisar);

		btnAdicionarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textValorTotalCarrinho.isVisible() == false) {
					adicionarMoedaCarrinho(table, pessoa);
				} else {
					efetuarCompra(table, pessoa);
				} /*
					 * if (cbTitulo.getItemAt(0) == null) { JOptionPane.showMessageDialog(null,
					 * "Você precisa criar uma coleção antes de adicionar uma nova moeda"); return;
					 * } if (btnEditarMoeda.isEnabled() == false) { adicionarMoedaCarrinho(table,
					 * pessoa); } else { controleTelas(pessoa, 1); }
					 */
			}
		});
		btnAdicionarCarrinho.setBounds(687, 500, 167, 33);
		contentPane.add(btnAdicionarCarrinho);

		btnEditarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					editarMoedaColecao(table, pessoa);
				} else {
					JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma moeda!");
				}
			}
		});
		btnEditarMoeda.setBounds(24, 500, 144, 33);
		contentPane.add(btnEditarMoeda);

		btnRemoverMoeda.setBounds(168, 500, 144, 33);
		btnRemoverMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					removerMoedaColecao(table, pessoa);
				} else {
					JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma moeda!");
				}
			}
		});
		contentPane.add(btnRemoverMoeda);

		lblMoedasDisponiveis.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblMoedasDisponiveis.setBounds(322, 79, 170, 14);
		contentPane.add(lblMoedasDisponiveis);

		btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEditarMoeda.isEnabled() != true) {
					TelaMenu telaMenu = new TelaMenu(pessoa);
					telaMenu.setVisible(true);
					telaMenu.setLocationRelativeTo(null);
					setVisible(false);

				} else {
					controleTelas(pessoa, 1);
				}
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-back-arrow-50.png"));
		btnVoltar.setBounds(10, 11, 41, 35);
		contentPane.add(btnVoltar);

		spnQuantidade
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

		spnQuantidade.setBounds(474, 506, 58, 20);
		contentPane.add(spnQuantidade);

		lblQuantidade.setBounds(400, 509, 86, 14);
		contentPane.add(lblQuantidade);

		textValor = new JTextField();
		textValor.setEditable(false);
		textValor.setBounds(583, 506, 101, 20);
		contentPane.add(textValor);
		textValor.setColumns(10);
		if (table.getSelectedRow() != -1) {
			populaCampos(table);
		}

		lblValor.setBounds(542, 509, 46, 14);
		contentPane.add(lblValor);

		lblMoedaSelecionada.setBounds(34, 509, 112, 14);
		contentPane.add(lblMoedaSelecionada);

		textMoedaSelecionada = new JTextField();
		textMoedaSelecionada.setEditable(false);
		textMoedaSelecionada.setBounds(158, 506, 232, 20);
		contentPane.add(textMoedaSelecionada);
		textMoedaSelecionada.setColumns(10);
		lblMinhasCompras.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblMinhasCompras.setBounds(316, 78, 170, 14);

		contentPane.add(lblMinhasCompras);

		lblQtdTotalCarrinho.setBounds(322, 509, 88, 14);
		contentPane.add(lblQtdTotalCarrinho);

		textQtdTotalCarrinho = new JTextField();
		textQtdTotalCarrinho.setEditable(false);
		textQtdTotalCarrinho.setBounds(410, 506, 65, 20);
		contentPane.add(textQtdTotalCarrinho);
		textQtdTotalCarrinho.setColumns(10);

		lblValorTotalCarrinho.setBounds(496, 509, 73, 14);
		contentPane.add(lblValorTotalCarrinho);

		textValorTotalCarrinho = new JTextField();
		textValorTotalCarrinho.setEditable(false);
		textValorTotalCarrinho.setBounds(571, 506, 106, 20);
		contentPane.add(textValorTotalCarrinho);
		textValorTotalCarrinho.setColumns(10);

		JButton btnCarrinho = new JButton("");
		btnCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEditarMoeda.isVisible() == false) {
					controleTelas(pessoa, 0);
				} else {
					JOptionPane.showMessageDialog(null, "Você já está em seu carrinho!");
				}
			}
		});
		btnCarrinho.setContentAreaFilled(false);
		btnCarrinho.setBorderPainted(false);
		btnCarrinho.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-carrinho-50.png"));
		btnCarrinho.setBounds(746, 11, 108, 55);
		contentPane.add(btnCarrinho);

		lblQtdTotalCarrinho.setVisible(false);
		lblValorTotalCarrinho.setVisible(false);
		textQtdTotalCarrinho.setVisible(false);
		textValorTotalCarrinho.setVisible(false);

		spnQuantidade.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (textMoedaSelecionada.getText().isBlank() != true) {
					calcula(table);
				}
			}
		});
	}

	/*
	 * public TelaColecao() { setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * setBounds(100, 100, 880, 576); contentPane = new JPanel();
	 * contentPane.setBackground(new Color(218, 218, 218));
	 * contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	 * 
	 * setContentPane(contentPane); contentPane.setLayout(null);
	 * separator.setBounds(0, 69, 864, 31); contentPane.add(separator);
	 * 
	 * JPanel panel = new JPanel(); panel.setBorder(new
	 * BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	 * panel.setBackground(new Color(255, 255, 255)); panel.setBounds(24, 100, 818,
	 * 389); contentPane.add(panel); panel.setLayout(null);
	 * 
	 * JScrollPane scrollPane_1 = new JScrollPane(); scrollPane_1.setBounds(10, 11,
	 * 798, 367); panel.add(scrollPane_1);
	 * 
	 * DefaultTableModel defaultTableModel = new DefaultTableModel();
	 * defaultTableModel.addColumn("Título");
	 * defaultTableModel.addColumn("Ano de face");
	 * defaultTableModel.addColumn("Valor de face");
	 * defaultTableModel.addColumn("Bordas");
	 * defaultTableModel.addColumn("Materiais");
	 * defaultTableModel.addColumn("Codigo de catalogo");
	 * defaultTableModel.addColumn("País de origem");
	 * defaultTableModel.addColumn("Peso");
	 * defaultTableModel.addColumn("Espessura");
	 * defaultTableModel.addColumn("Diametro");
	 * defaultTableModel.addColumn("Quantidade");
	 * defaultTableModel.addColumn("Valor unitario");
	 * defaultTableModel.addColumn("Disponivel venda");
	 * defaultTableModel.addColumn("Dono");
	 * 
	 * ColecaoMoedaService colecaoMoedaService = new ColecaoMoedaService(); for
	 * (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaService.listar()) {
	 * defaultTableModel.addRow(new Object[] {
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getAnoFace(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getValorFace(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getBordas(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getMateriais(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getCodCatalogo(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getPaisOrigem(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getPeso(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getEspessura(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getDiametro(),
	 * colecaoMoedaEntity.getQuantidade(), colecaoMoedaEntity.getValorUnitario(),
	 * colecaoMoedaEntity.isDisponivelVenda(),
	 * colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getDono().
	 * getNomeCompleto() }); } table = new JTable(defaultTableModel);
	 * scrollPane_1.setViewportView(table);
	 * 
	 * JLabel lblLogo = new JLabel("SoftCoin"); lblLogo.setFont(new Font("Tahoma",
	 * Font.BOLD | Font.ITALIC, 24)); lblLogo.setBounds(24, 23, 112, 31);
	 * contentPane.add(lblLogo);
	 * 
	 * JSeparator separator_1 = new JSeparator();
	 * separator_1.setOrientation(SwingConstants.VERTICAL);
	 * separator_1.setBounds(146, 0, 2, 70); contentPane.add(separator_1);
	 * 
	 * textPesquisa = new JTextField(); textPesquisa.setBounds(256, 23, 436, 35);
	 * contentPane.add(textPesquisa); textPesquisa.setColumns(10);
	 * 
	 * JComboBox comboFiltro = new JComboBox(); comboFiltro.setModel(new
	 * DefaultComboBoxModel(new String[] { "Ano fabricação" }));
	 * comboFiltro.setBackground(new Color(192, 192, 192));
	 * comboFiltro.setBounds(158, 23, 88, 35); contentPane.add(comboFiltro);
	 * 
	 * JButton btnPesquisar = new JButton("Pesquisar"); btnPesquisar.setBounds(702,
	 * 23, 89, 37); contentPane.add(btnPesquisar);
	 * 
	 * cbTitulo = new JComboBox();
	 * 
	 * cbTitulo.setFont(new Font("Tahoma", Font.BOLD, 14)); cbTitulo.setModel(new
	 * DefaultComboBoxModel(new String[] { "Moedas brasileiras da epoca colonial"
	 * })); cbTitulo.setBounds(24, 69, 729, 31); contentPane.add(cbTitulo);
	 * 
	 * JButton btnNewButton = new JButton("New button"); btnNewButton.setFont(new
	 * Font("Tahoma", Font.PLAIN, 8)); btnNewButton.setBounds(763, 69, 89, 31);
	 * contentPane.add(btnNewButton);
	 * 
	 * JButton btnNewButton_1 = new JButton("New button");
	 * btnNewButton_1.setBounds(32, 505, 89, 23); contentPane.add(btnNewButton_1);
	 * 
	 * JButton btnNewButton_2 = new JButton("New button");
	 * btnNewButton_2.setBounds(142, 505, 89, 23); contentPane.add(btnNewButton_2);
	 * 
	 * JButton btnNewButton_3 = new JButton("New button");
	 * btnNewButton_3.setBounds(246, 505, 89, 23); contentPane.add(btnNewButton_3);
	 * }
	 */

	/*
	 * public void populaCbTitulo(PessoaEntity pessoa) { pessoaService = new
	 * PessoaService(); pessoa = pessoaService.pesquisar(pessoa); // Popula o combo
	 * box titulo, resgatando esses valores da coleção do usuario if
	 * (pessoa.getColecoes() != null) { for (ColecaoEntity colecaoEntity :
	 * pessoa.getColecoes()) { // for (int i = 0; i < cbTitulo.getItemCount(); i++)
	 * { // && cbTitulo.getItemAt(i).equals(colecaoEntity.getTituloColecao()) ==
	 * false && // i == cbTitulo.getItemCount() if
	 * (colecaoEntity.getTituloColecao().isBlank() != true) {
	 * 
	 * cbTitulo.addItem(colecaoEntity.getTituloColecao());
	 * 
	 * } else { inserirNovaColecao(pessoa); }
	 * 
	 * } } }
	 */

	public void populaJtableColecao(PessoaEntity pessoa, DefaultTableModel defaultTableModel) {
		/*
		 * Popula o jtable com as moedas da coleção do usuario, separando por titulo da
		 * coleção, sendo comparadas com o titulo atual do combo box titulo
		 * 
		 */

		pessoaService = new PessoaService();
		pessoa = pessoaService.pesquisar(pessoa);

		defaultTableModel.setRowCount(0);

		for (ColecaoMoedaEntity colecaoMoedaEntity : carrinho) {

			/*
			 * if (colecaoMoedaEntity.getIdColecaoMoeda() != null) {
			 * colecaoMoedaEntities.add(colecaoMoedaEntity); }
			 */

			defaultTableModel.addRow(new Object[] { colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo(),
					colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getAnoFace(),
					colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getValorFace(),
					colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getCodCatalogo(),
					colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getPaisOrigem(),
					colecaoMoedaEntity.getQuantidade(), colecaoMoedaEntity.getValorUnitario(),
					colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto() });

		}

	}

	public void inserirNovaColecao(PessoaEntity pessoa) {

		String titulo = JOptionPane.showInputDialog("Digite o título da nova coleção");

		// TODO Auto-generated catch block

		try {
			if (titulo.isBlank() == true || titulo.length() > 255) {
				JOptionPane.showMessageDialog(null, "título dado a coleção nulo ou acima de 255 caracteres");
				inserirNovaColecao(pessoa);

			} else {
				ColecaoEntity colecaoEntity = new ColecaoEntity();
				colecaoEntity.setDono(pessoa);
				colecaoEntity.setTituloColecao(titulo);
				colecaoService.salvar(colecaoEntity);

				pessoaService = new PessoaService();
				pessoa = pessoaService.pesquisar(pessoa);

				// cbTitulo.addItem(titulo);
				// populaCbTitulo(pessoa);
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Deu erro no nome da coleção ");
		}
	}

	public void controleTelas(PessoaEntity pessoa, int i) {

		switch (i) {
		case 0:
			colecaoMoedaService = new ColecaoMoedaService();
			// cbTitulo.setVisible(true);
			lblMinhasCompras.setVisible(true);
			btnEditarMoeda.setEnabled(true);
			btnRemoverMoeda.setEnabled(true);
			btnEditarMoeda.setVisible(true);
			btnRemoverMoeda.setVisible(true);
			// lblMinhasColecoes.setVisible(true);
			comboFiltro.setVisible(true);
			textPesquisa.setVisible(true);
			btnPesquisar.setVisible(true);
			lblMoedasDisponiveis.setVisible(false);
			spnQuantidade.setVisible(false);
			textMoedaSelecionada.setVisible(false);
			textValor.setVisible(false);
			btnAdicionarCarrinho.setText("Finalizar Compra");
			lblMoedaSelecionada.setVisible(false);
			lblQuantidade.setVisible(false);
			lblValor.setVisible(false);
			lblQtdTotalCarrinho.setVisible(true);
			lblValorTotalCarrinho.setVisible(true);
			textQtdTotalCarrinho.setVisible(true);
			textValorTotalCarrinho.setVisible(true);

			defaultTableModel = new DefaultTableModel() {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return String.class;
				}

				public boolean isCellEditable(int row, int column) {
					return false; // Torna todas as células não editáveis
				}
			};

			defaultTableModel.addColumn("Título");
			defaultTableModel.addColumn("Ano de face");
			defaultTableModel.addColumn("Valor de face");
			defaultTableModel.addColumn("Código de catálogo");
			defaultTableModel.addColumn("País de origem");
			defaultTableModel.addColumn("Quantidade");
			defaultTableModel.addColumn("Valor total");
			defaultTableModel.addColumn("Vendedor");
			defaultTableModel.setRowCount(0);
			populaJtableColecao(pessoa, defaultTableModel);

			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(table);
			calculaQtdeValorTotal(table);
			break;
		case 1:
			colecaoMoedaService = new ColecaoMoedaService();
			// cbTitulo.setVisible(false);
			lblMinhasCompras.setVisible(false);
			spnQuantidade.setVisible(true);
			textMoedaSelecionada.setVisible(true);
			textValor.setVisible(true);
			btnEditarMoeda.setEnabled(false);
			btnRemoverMoeda.setEnabled(false);
			btnEditarMoeda.setVisible(false);
			btnRemoverMoeda.setVisible(false);
			// lblMinhasColecoes.setVisible(false);
			comboFiltro.setVisible(true);
			textPesquisa.setVisible(true);
			btnPesquisar.setVisible(true);
			lblMoedasDisponiveis.setVisible(true);
			spnQuantidade
					.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			textMoedaSelecionada.setText(null);
			textValor.setText(null);
			btnAdicionarCarrinho.setText("Adicionar Ao Carrinho");
			lblMoedaSelecionada.setVisible(true);
			lblQuantidade.setVisible(true);
			lblValor.setVisible(true);
			lblQtdTotalCarrinho.setVisible(false);
			lblValorTotalCarrinho.setVisible(false);
			textQtdTotalCarrinho.setVisible(false);
			textValorTotalCarrinho.setVisible(false);

			defaultTableModel = new DefaultTableModel() {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return String.class;
				}

				public boolean isCellEditable(int row, int column) {
					return false; // Torna todas as células não editáveis
				}
			};

			defaultTableModel.addColumn("Título");
			defaultTableModel.addColumn("Ano de face");
			defaultTableModel.addColumn("Valor de face");
			defaultTableModel.addColumn("Código de catálogo");
			defaultTableModel.addColumn("País de origem");
			defaultTableModel.addColumn("Quantidade");
			defaultTableModel.addColumn("Valor unitário");
			defaultTableModel.addColumn("Vendedor");
			defaultTableModel.setRowCount(0);

			try {
				moedaService = new MoedaService();

			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao carregar um service: " + e);
			}
			/*
			 * O for abaixo serve para popular o jTable de moedas disponiveis, caso a lista
			 * de colecoesMoedaEntities esteja vazia ele executa o primeiro if, assim
			 * populando com todas as moedas, caso esteja com itens dentro, ele compara os
			 * itens das listas para que não haja duplicidade
			 */

			for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaService.listar()) {

				int contador = 0;
				// Popula com todas as moedas

				if (colecaoMoedaEntity.isDisponivelVenda() == true) {
					colecaoMoedaEntities.add(colecaoMoedaEntity);
					defaultTableModel.addRow(new Object[] {
							colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo(),
							colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getAnoFace(),
							colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getValorFace(),
							colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getCodCatalogo(),
							colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getPaisOrigem(),
							colecaoMoedaEntity.getQuantidade(), colecaoMoedaEntity.getValorUnitario(),
							colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto() });

				} else {
					// adiciona apenas as moedas que o usuario não tem, verificando pelo id
					for (ColecaoMoedaEntity colecaoMoedaEntity2 : colecaoMoedaService.listar()) {

						contador += 1;

						/*
						 * if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getId() ==
						 * moedaEntity.getId()) {
						 * 
						 * break;
						 * 
						 * }
						 */if (colecaoMoedaEntity.isDisponivelVenda() == true) {
							defaultTableModel.addRow(
									new Object[] { colecaoMoedaEntity2.getIdColecaoMoeda().getIdMoeda().getTitulo(),
											colecaoMoedaEntity2.getIdColecaoMoeda().getIdMoeda().getAnoFace(),
											colecaoMoedaEntity2.getIdColecaoMoeda().getIdMoeda().getValorFace(),
											colecaoMoedaEntity2.getIdColecaoMoeda().getIdMoeda().getCodCatalogo(),
											colecaoMoedaEntity2.getIdColecaoMoeda().getIdMoeda().getPaisOrigem(),
											colecaoMoedaEntity2.getQuantidade(), colecaoMoedaEntity2.getValorUnitario(),
											colecaoMoedaEntity2.isDisponivelVenda() });
						}
					}
				}
			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(table);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					populaCampos(table);
				}
			});

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					populaCampos(table);
				}
			});
			break;

		default:
			break;

		}

	}

	public void adicionarMoedaCarrinho(JTable table, PessoaEntity pessoa) {

		int i = table.getSelectedRow();

		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Você não selecionou uma moeda!");
			return;
		}
		String vendedor = (String) table.getValueAt(i, 7);
		int quantidade = (int) table.getValueAt(i, 5);
		String titulo = (String) table.getValueAt(i, 0);
		ColecaoMoedaEntity colecaoMoeda = new ColecaoMoedaEntity();
		for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaEntities) {
			if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo().equals(titulo) && colecaoMoedaEntity
					.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto().equals(vendedor)
					&& colecaoMoedaEntity.getQuantidade() == quantidade)
				colecaoMoeda = colecaoMoedaEntity;
		}

		if (colecaoMoeda.getQuantidade() < (int) spnQuantidade.getValue()) {

			JOptionPane.showMessageDialog(null, "Quantidade desejada maior que a disponível para venda!");
			return;

		}

		if (colecaoMoeda.getIdColecaoMoeda().getIdColecao().getDono().getCpf().equals(pessoa.getCpf())) {
			JOptionPane.showMessageDialog(null, "Você não pode comprar sua própria moeda ");
			return;
		}

		colecaoMoeda.setQuantidade((int) spnQuantidade.getValue());
		colecaoMoeda.setValorUnitario(Float.parseFloat(textValor.getText()));
		if (carrinho.isEmpty() == false) {
			int contador = 1;
			for (ColecaoMoedaEntity colecaoMoedaEntity : carrinho) {

				if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo()
						.equals(colecaoMoeda.getIdColecaoMoeda().getIdMoeda().getTitulo())
						&& colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto()
								.equals(vendedor)) {
					if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
							"Você já tem uma moeda deste mesmo vendedor no carrinho, deseja substituir?") == JOptionPane.OK_OPTION) {
						carrinho.set(carrinho.indexOf(colecaoMoedaEntity), colecaoMoeda);
						break;
					} else {
						return;
					}
				}

				else if (contador >= carrinho.size()) {
					carrinho.add(colecaoMoeda);
					break;
				}
				contador += 1;
			}
		} else {
			carrinho.add(colecaoMoeda);
		}

		controleTelas(pessoa, 0);
		controleTelas(pessoa, 1);
		controleTelas(pessoa, 0);
	}

	public void editarMoedaColecao(JTable jTable, PessoaEntity pessoa) {

		int i = table.getSelectedRow();

		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Você não selecionou uma moeda!");
			return;
		}
		String vendedor = (String) table.getValueAt(i, 7);
		float valor = (float) table.getValueAt(i, 6);
		String titulo = (String) table.getValueAt(i, 0);
		ColecaoMoedaEntity colecaoMoeda = null;
		int quantidadeDisp = 0;
		float valorUnitario = 0;
		/*
		 * for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaEntities) { if
		 * (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo().equals(
		 * titulo) == true &&
		 * colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getDono().
		 * getNomeCompleto() .equals(vendedor) == true) { for (ColecaoMoedaEntity
		 * colecaoMoedaEntity2 : carrinho) { if
		 * (colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getId() ==
		 * colecaoMoedaEntity2 .getIdColecaoMoeda().getIdColecao().getId() &&
		 * colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getId() ==
		 * colecaoMoedaEntity2 .getIdColecaoMoeda().getIdMoeda().getId()) {
		 * 
		 * quantidadeDisp = colecaoMoedaEntity.getQuantidade(); valorUnitario =
		 * colecaoMoedaEntity.getValorUnitario(); colecaoMoeda = colecaoMoedaEntity; } }
		 * } }
		 */
		for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaEntities) {

			for (ColecaoMoedaEntity colecaoMoedaEntity2 : carrinho) {
				if (colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getId() == colecaoMoedaEntity2
						.getIdColecaoMoeda().getIdColecao().getId()
						&& colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getId() == colecaoMoedaEntity2
								.getIdColecaoMoeda().getIdMoeda().getId()
						&& colecaoMoedaEntity2.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto()
								.equals(vendedor)) {
					quantidadeDisp = colecaoMoedaEntity.getQuantidade();
					valorUnitario = colecaoMoedaEntity.getValorUnitario();
					colecaoMoeda = colecaoMoedaEntity;
				}
			}
		}

		if (quantidadeDisp == 1) {
			JOptionPane.showMessageDialog(null,
					"Não é possível aumentar a quantidade do produto selecionado! pois, o vendedor só possui uma unidade para venda");
			return;
		}
		int quantidade = Integer.parseInt(
				JOptionPane.showInputDialog(null, "Digite a nova quantidade| Valor máximo:" + quantidadeDisp));

		if (quantidade <= 0) {
			JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que zero !");
			return;
		}

		if (quantidadeDisp < (int) quantidade) {

			JOptionPane.showMessageDialog(null, "Quantidade desejada maior que a disponível para venda!");
			return;

		}

		colecaoMoeda.setQuantidade(quantidade);
		colecaoMoeda.setValorUnitario(valorUnitario * quantidade);
		if (carrinho.isEmpty() == false) {
			int contador = 1;
			for (ColecaoMoedaEntity colecaoMoedaEntity : carrinho) {

				if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo()
						.equals(colecaoMoeda.getIdColecaoMoeda().getIdMoeda().getTitulo())
						&& colecaoMoedaEntity.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto()
								.equals(vendedor)) {
					carrinho.set(carrinho.indexOf(colecaoMoedaEntity), colecaoMoeda);
					break;
				}

				else if (contador >= carrinho.size()) {
					carrinho.add(colecaoMoeda);
					break;
				}
				contador += 1;
			}
		} else {
			carrinho.add(colecaoMoeda);
		}

		controleTelas(pessoa, 1);
		controleTelas(pessoa, 0);
		calculaQtdeValorTotal(jTable);

	}

	public void removerMoedaColecao(JTable jTable, PessoaEntity pessoa) {

		colecaoMoedaService = new ColecaoMoedaService();
		int i = jTable.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Você não selecionou uma moeda!");
			return;
		}
		String titulo = (String) jTable.getValueAt(i, 0);
		String vendedor = (String) jTable.getValueAt(i, 7);
		ColecaoMoedaEntity colecaoMoeda = null;

		for (ColecaoMoedaEntity colecaoMoedaEntity : carrinho) {
			if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo().equals(titulo) && colecaoMoedaEntity
					.getIdColecaoMoeda().getIdColecao().getDono().getNomeCompleto().equals(vendedor))
				colecaoMoeda = colecaoMoedaEntity;
		}
		if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Deseja mesmo remover a meoda: "
				+ colecaoMoeda.getIdColecaoMoeda().getIdMoeda().getTitulo() + " ?") == JOptionPane.YES_OPTION) {
			carrinho.remove(colecaoMoeda);
			controleTelas(pessoa, 0);
		}
	}

	public void populaCampos(JTable Table) {

		int quantidade = (int) spnQuantidade.getValue();
		float valorUnitario = (float) table.getValueAt(table.getSelectedRow(), 6);
		textValor.setText("" + (valorUnitario * quantidade));

		textMoedaSelecionada.setText((String) table.getValueAt(table.getSelectedRow(), 0));

	}

	public void calcula(JTable Table) {
		int quantidade = (int) spnQuantidade.getValue();
		float valorUnitario = (float) table.getValueAt(table.getSelectedRow(), 6);
		textValor.setText("" + (valorUnitario * quantidade));
	}

	public void calculaQtdeValorTotal(JTable Table) {
		int indiceColunaQuantidade = 5;

		// Obtém a soma dos valores da coluna "Quantidade"
		int somaQuantidade = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			int quantidade2 = (int) table.getValueAt(i, indiceColunaQuantidade);
			somaQuantidade += quantidade2;
		}
		textQtdTotalCarrinho.setText("" + somaQuantidade);

		indiceColunaQuantidade = 6;
		float somaValor = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			float quantidade2 = (float) table.getValueAt(i, indiceColunaQuantidade);
			somaValor += quantidade2;
		}
		textValorTotalCarrinho.setText("" + somaValor);
	}

	public void efetuarCompra(JTable Table, PessoaEntity pessoa) {

		pessoa = pessoaService.pesquisar(pessoa);

		if (Table.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "O carrinho esta vazio!");
			return;
		}

		if (pessoa.getColecoes().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Você não possui uma coleção, por favor, crie uma antes!");
			inserirNovaColecao(pessoa);
			return;
		}

		ColecaoEntity colecaoSelecionada = CaixaConfirmacaoUtil.chamarComboBox("Escolha a coleção: ", pessoa);

		if (colecaoSelecionada == null) {
			JOptionPane.showMessageDialog(null, "Você precisa selecionar uma coleção antes!");
			return;
		}

		// Obtém o índice da coluna "Vendedor"
		String texto = "-----------------------Nota Fiscal------------------------------\n";
		int indiceColunaVendedor = 7;
		int indiceColunaProduto = 0;
		int indiceColunaQuantidade = 5;
		// Cria uma lista para armazenar os nomes dos vendedores
		List<String> listaVendedores = new ArrayList<>();
		Date dataAtual = new Date();
		DateFormatter dateFormatter = new DateFormatter();
		int quantidadeColecao = colecaoSelecionada.getColecaoMoeda().size();
		// Extrai os nomes dos vendedores da coluna "Vendedor"
		for (int i = 0; i < table.getRowCount(); i++) {
			String vendedor = (String) table.getValueAt(i, indiceColunaVendedor);
			if (!listaVendedores.contains(vendedor)) {
				listaVendedores.add(vendedor);
			}
		}

		// Loop pelos vendedores
		for (String vendedor : listaVendedores) {
			// Cria uma lista para armazenar os produtos do vendedor atual
			List<ColecaoMoedaEntity> listaProdutos = new ArrayList<>();

			// Extrai os produtos do vendedor atual
			for (int i = 0; i < table.getRowCount(); i++) {
				String produto = (String) table.getValueAt(i, indiceColunaProduto);
				String vendedorProduto = (String) table.getValueAt(i, indiceColunaVendedor);
				int quantidadeProduto = (int) table.getValueAt(i, indiceColunaQuantidade);
				for (ColecaoMoedaEntity colecaoMoeda : carrinho) {
					if (vendedor.equals(vendedorProduto) && colecaoMoeda.getIdColecaoMoeda().getIdColecao().getDono()
							.getNomeCompleto().equals(vendedor)) {
						if (colecaoMoeda.getIdColecaoMoeda().getIdMoeda().getTitulo().equals(produto)
								&& colecaoMoeda.getQuantidade() == quantidadeProduto) {
							listaProdutos.add(colecaoMoeda);
						}
					}

				}
			}

			Iterator<ColecaoMoedaEntity> iterator2 = listaProdutos.iterator();

			while (iterator2.hasNext()) {
				Iterator<ColecaoMoedaEntity> iterator = colecaoSelecionada.getColecaoMoeda().iterator();
				ColecaoMoedaEntity colecaoMoedaEntity = iterator2.next();
				int contador = 0;
				boolean achou = false;
				if (iterator.hasNext()) {

					while (iterator.hasNext()) {

						ColecaoMoedaEntity colecaoMoedaU = iterator.next();
						contador += 1;

						if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo()
								.equals(colecaoMoedaU.getIdColecaoMoeda().getIdMoeda().getTitulo())) {
							achou = true;
							colecaoMoedaU
									.setQuantidade(colecaoMoedaU.getQuantidade() + colecaoMoedaEntity.getQuantidade());
							try {

								colecaoMoedaService.salvar(colecaoMoedaU);
								ColecaoMoedaEntity colecaoVendedor = colecaoMoedaService
										.pesquisar(colecaoMoedaEntity.getIdColecaoMoeda());
								colecaoVendedor.setQuantidade(
										colecaoVendedor.getQuantidade() - colecaoMoedaEntity.getQuantidade());

								if (colecaoVendedor.getQuantidade() <= 0) {

									colecaoVendedor.setDisponivelVenda(false);

								}

								colecaoMoedaService.salvar(colecaoVendedor);
								// colecaoSelecionada.getColecaoMoeda().remove(colecaoMoedaU);

								break;
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null,
										"ocorreu um erro ao atualizar a quantidade da moeda: "
												+ colecaoMoedaU.getIdColecaoMoeda().getIdMoeda().getTitulo());
							}

						} else if (iterator.hasNext() == false && achou == false) {

							ColecaoMoedaEntity colecaoMoedaNova = new ColecaoMoedaEntity();
							ColecaoMoedaIdEntity colecaoMoedaIdEntity = new ColecaoMoedaIdEntity();
							colecaoMoedaIdEntity.setIdColecao(colecaoSelecionada);
							colecaoMoedaIdEntity.setIdMoeda(colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda());

							colecaoMoedaNova.setIdColecaoMoeda(colecaoMoedaIdEntity);
							colecaoMoedaNova.setQuantidade(colecaoMoedaEntity.getQuantidade());
							colecaoMoedaNova.setValorUnitario(colecaoMoedaEntity.getValorUnitario());
							colecaoMoedaNova.setDisponivelVenda(false);

							colecaoSelecionada.getColecaoMoeda().add(colecaoMoedaNova);
							colecaoService.salvar(colecaoSelecionada);

							ColecaoMoedaEntity colecaoVendedor = colecaoMoedaService
									.pesquisar(colecaoMoedaEntity.getIdColecaoMoeda());
							colecaoVendedor.setQuantidade(
									colecaoVendedor.getQuantidade() - colecaoMoedaEntity.getQuantidade());

							if (colecaoVendedor.getQuantidade() <= 0) {
								colecaoVendedor.setDisponivelVenda(false);
							}

							colecaoMoedaService.salvar(colecaoVendedor);

							// colecaoSelecionada.getColecaoMoeda().remove(colecaoMoedaU);

							break;
						}

					}
				} else {
					ColecaoMoedaEntity colecaoMoedaNova = new ColecaoMoedaEntity();
					ColecaoMoedaIdEntity colecaoMoedaIdEntity = new ColecaoMoedaIdEntity();
					colecaoMoedaIdEntity.setIdColecao(colecaoSelecionada);
					colecaoMoedaIdEntity.setIdMoeda(colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda());

					colecaoMoedaNova.setIdColecaoMoeda(colecaoMoedaIdEntity);
					colecaoMoedaNova.setQuantidade(colecaoMoedaEntity.getQuantidade());
					colecaoMoedaNova.setValorUnitario(colecaoMoedaEntity.getValorUnitario());
					colecaoMoedaNova.setDisponivelVenda(false);

					colecaoSelecionada.getColecaoMoeda().add(colecaoMoedaNova);
					colecaoService.salvar(colecaoSelecionada);

					ColecaoMoedaEntity colecaoVendedor = colecaoMoedaService
							.pesquisar(colecaoMoedaEntity.getIdColecaoMoeda());
					colecaoVendedor.setQuantidade(colecaoVendedor.getQuantidade() - colecaoMoedaEntity.getQuantidade());

					if (colecaoVendedor.getQuantidade() <= 0) {
						colecaoVendedor.setDisponivelVenda(false);
					}

					colecaoMoedaService.salvar(colecaoVendedor);

				}

			}

			// Exibe os produtos do vendedor atual
			int quantidadeTotal = 0;
			float valorTotal = 0;
			PessoaEntity vendedorAtual = null;
			int contadorNota = 0;
			NotaEntity notaEntity = new NotaEntity();
			List<NotaMoedaEntity> notasMoeda = new ArrayList<>();
			StringBuilder mensagem = new StringBuilder("Produtos do vendedor " + vendedor + ":\n");
			for (ColecaoMoedaEntity produto : listaProdutos) {
				contadorNota += 1;
				mensagem.append(produto.getIdColecaoMoeda().getIdMoeda().getTitulo()).append(" Quantidade: ")
						.append(produto.getQuantidade()).append(" Valor unitário : ").append(produto.getValorUnitario())
						.append("\n");
				//
				// quantidadeTotal += produto.getQuantidade();
				valorTotal += produto.getValorUnitario();
				vendedorAtual = produto.getIdColecaoMoeda().getIdColecao().getDono();
				NotaMoedaID notaMoedaID = new NotaMoedaID();
				notaMoedaID.setIdMoeda(produto.getIdColecaoMoeda().getIdMoeda());
				notaMoedaID.setIdNota(notaEntity);
				NotaMoedaEntity notaMoedaEntity = new NotaMoedaEntity();
				notaMoedaEntity.setIdNotaMoeda(notaMoedaID);
				notaMoedaEntity.setQuantidade(produto.getQuantidade());
				notaMoedaEntity.setValorUnitario(produto.getValorUnitario());
				notasMoeda.add(notaMoedaEntity);

			}

			notaEntity.setComprador(pessoa);
			notaEntity.setVendedor(vendedorAtual);
			notaEntity.setData(dataAtual);
			notaEntity.setValorTotal(valorTotal);
			notaEntity.setNotaMoeda(notasMoeda);

			notaService.salvar(notaEntity);

			texto += mensagem.toString() + "\n----------------------------------------------------------------\n";
		}
		texto += " Total de itens: " + textQtdTotalCarrinho.getText() + " || Valor Total: R$"
				+ textValorTotalCarrinho.getText() + " || Data da Compra : " + dateFormatter.formatarDataBR(dataAtual);
		JOptionPane.showMessageDialog(null, texto.toString(), "NOTA", JOptionPane.INFORMATION_MESSAGE);

		controleTelas(pessoa, 1);
	}
}
