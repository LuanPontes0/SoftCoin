package softCoin.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import softCoin.entity.BordaEntity;
import softCoin.entity.ColecaoEntity;
import softCoin.entity.ColecaoMoedaEntity;
import softCoin.entity.MaterialEntity;
import softCoin.entity.MoedaEntity;
import softCoin.entity.PaisEntity;
import softCoin.entity.PessoaEntity;
import softCoin.service.ColecaoMoedaService;
import softCoin.service.ColecaoService;
import softCoin.service.MoedaService;
import softCoin.service.PessoaService;
import softCoin.util.CaixaConfirmacaoUtil;

public class TelaColecao extends JFrame {

	private JPanel contentPane;
	private JTable tbColecao;
	private JScrollPane scrollPane;
	private JTextField textPesquisa = new JTextField();
	private final JSeparator separator = new JSeparator();
	private JTable table;
	private JComboBox cbTitulo;
	private DefaultTableModel defaultTableModel;
	private PessoaService pessoaService;
	private ColecaoEntity colecaoAtual;
	private JScrollPane scrollPane_1;
	private MoedaService moedaService;
	private ColecaoMoedaService colecaoMoedaService;
	private JButton btnAdicionarMoeda = new JButton("Adicionar Moeda");
	private JButton btnNovaColecao;
	private JButton btnEditarMoeda = new JButton("Editar Moeda");
	private JButton btnRemoverMoeda = new JButton("Remover Moeda");
	private JLabel lblMinhasColecoes = new JLabel("Minhas coleções:");
	private JButton btnVoltar;
	private JComboBox comboFiltro = new JComboBox();
	private List<ColecaoMoedaEntity> colecaoMoedaEntities;
	private JButton btnPesquisar = new JButton("Pesquisar");
	private JLabel lblMoedasDisponiveis = new JLabel("Moedas Disponíveis");
	private ColecaoService colecaoService = new ColecaoService();
	private JButton btnEditarColecao = new JButton("Editar Coleção");
	private JButton btnRemoverColecao = new JButton("Remover Coleção");

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

	public TelaColecao(PessoaEntity pessoa) {
		setTitle("Coleção");
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

		cbTitulo = new JComboBox();
		cbTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		populaCbTitulo(pessoa);
		cbTitulo.setBounds(191, 36, 537, 31);
		cbTitulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleTelas(pessoa, 0);
				// populaJtableColecao(pessoa, defaultTableModel);
			}
		});
		contentPane.add(cbTitulo);

		btnNovaColecao = new JButton("Nova coleção");
		btnNovaColecao.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNovaColecao.setBounds(753, 36, 89, 31);
		btnNovaColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirNovaColecao(pessoa);
			}
		});
		contentPane.add(btnNovaColecao);

		controleTelas(pessoa, 0);

		JLabel lblLogo = new JLabel("SoftCoin");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblLogo.setBounds(702, 500, 112, 31);
		contentPane.add(lblLogo);

		textPesquisa.setBounds(256, 23, 436, 35);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);

		comboFiltro.setModel(new DefaultComboBoxModel(new String[] { "Ano fabricação" }));
		comboFiltro.setBackground(new Color(192, 192, 192));
		comboFiltro.setBounds(158, 23, 88, 35);
		contentPane.add(comboFiltro);

		btnPesquisar.setBounds(702, 23, 112, 37);
		contentPane.add(btnPesquisar);

		btnAdicionarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbTitulo.getItemAt(0) == null) {
					JOptionPane.showMessageDialog(null,
							"Você precisa criar uma coleção antes de adicionar uma nova moeda");
					return;
				}
				if (btnEditarMoeda.isEnabled() == false) {
					if (table.getSelectedRow() != -1) {
						adicionarMoedaColecao(table, colecaoAtual);
					} else {
						JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma moeda!");
					}

				} else {
					controleTelas(pessoa, 1);
				}
			}
		});
		btnAdicionarMoeda.setBounds(34, 500, 144, 33);
		contentPane.add(btnAdicionarMoeda);

		btnEditarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					editarMoedaColecao(table, pessoa);
				} else {
					JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma moeda!");
				}
			}
		});
		btnEditarMoeda.setBounds(191, 500, 144, 33);
		contentPane.add(btnEditarMoeda);

		btnRemoverMoeda.setBounds(348, 500, 144, 33);
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

		lblMinhasColecoes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblMinhasColecoes.setBounds(63, 44, 144, 14);
		contentPane.add(lblMinhasColecoes);

		btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEditarMoeda.isEnabled() == true) {
					TelaMenu telaMenu = new TelaMenu(pessoa);
					telaMenu.setVisible(true);
					telaMenu.setLocationRelativeTo(null);
					setVisible(false);

				} else {
					controleTelas(pessoa, 0);
				}
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setIcon(new ImageIcon(
				"C:\\Users\\Luan\\eclipse-workspace\\softCoinMaven\\src\\main\\resources\\images\\icons8-back-arrow-50.png"));
		btnVoltar.setBounds(10, 11, 41, 35);
		contentPane.add(btnVoltar);

		btnEditarColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbTitulo.getItemAt(0) != null) {

					editarColecao();

				} else {
					JOptionPane.showMessageDialog(null, "Você não tem nenhuma coleção ainda!");
				}
			}
		});
		btnEditarColecao.setBounds(191, 77, 235, 23);
		contentPane.add(btnEditarColecao);

		btnRemoverColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerColecao();
			}
		});
		btnRemoverColecao.setBounds(493, 77, 235, 23);
		contentPane.add(btnRemoverColecao);
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

	public void populaCbTitulo(PessoaEntity pessoa) {
		pessoaService = new PessoaService();
		pessoa = pessoaService.pesquisar(pessoa);
		// Popula o combo box titulo, resgatando esses valores da coleção do usuario
		if (pessoa.getColecoes() != null) {
			for (ColecaoEntity colecaoEntity : pessoa.getColecoes()) {
				// for (int i = 0; i < cbTitulo.getItemCount(); i++) {
				// && cbTitulo.getItemAt(i).equals(colecaoEntity.getTituloColecao()) == false &&
				// i == cbTitulo.getItemCount()
				if (colecaoEntity.getTituloColecao().isBlank() != true) {

					cbTitulo.addItem(colecaoEntity.getTituloColecao());

				} else {
					inserirNovaColecao(pessoa);
				}

			}
		}
	}

	public void populaJtableColecao(PessoaEntity pessoa, DefaultTableModel defaultTableModel) {
		/*
		 * Popula o jtable com as moedas da coleção do usuario, separando por titulo da
		 * coleção, sendo comparadas com o titulo atual do combo box titulo
		 * 
		 */

		colecaoMoedaEntities = new ArrayList<>();
		colecaoMoedaEntities.clear();
		pessoaService = new PessoaService();
		pessoa = pessoaService.pesquisar(pessoa);

		defaultTableModel.setRowCount(0);
		if (cbTitulo.getSelectedIndex() != -1) {
			if (pessoa.getColecoes() != null) {
				for (ColecaoEntity colecaoEntity : pessoa.getColecoes()) {
					if (colecaoEntity.getTituloColecao().equals(cbTitulo.getSelectedItem().toString()) == true) {
						colecaoAtual = colecaoEntity;
						for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoEntity.getColecaoMoeda()) {

							if (colecaoMoedaEntity.getIdColecaoMoeda() != null) {
								colecaoMoedaEntities.add(colecaoMoedaEntity);
							}

							defaultTableModel.addRow(
									new Object[] { colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo(),
											colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getAnoFace(),
											colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getValorFace(),
											colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getCodCatalogo(),
											colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getPaisOrigem(),
											colecaoMoedaEntity.getQuantidade(), colecaoMoedaEntity.getValorUnitario(),
											colecaoMoedaEntity.isDisponivelVenda() });
						}
					}
				}
			}
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

				cbTitulo.addItem(titulo);
				cbTitulo.setSelectedItem(titulo);
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

			cbTitulo.setVisible(true);
			btnNovaColecao.setVisible(true);

			btnEditarMoeda.setEnabled(true);
			btnRemoverMoeda.setEnabled(true);
			lblMinhasColecoes.setVisible(true);
			comboFiltro.setVisible(false);
			textPesquisa.setVisible(false);
			btnPesquisar.setVisible(false);
			lblMoedasDisponiveis.setVisible(false);
			btnEditarColecao.setVisible(true);
			btnRemoverColecao.setVisible(true);

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
			defaultTableModel.addColumn("Disponível venda");

			if (pessoa.getColecoes() != null) {
				populaJtableColecao(pessoa, defaultTableModel);
			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(table);
			break;
		case 1:

			cbTitulo.setVisible(false);
			btnNovaColecao.setVisible(false);
			btnEditarMoeda.setEnabled(false);
			btnRemoverMoeda.setEnabled(false);
			lblMinhasColecoes.setVisible(false);
			comboFiltro.setVisible(true);
			textPesquisa.setVisible(true);
			btnPesquisar.setVisible(true);
			lblMoedasDisponiveis.setVisible(true);
			btnEditarColecao.setVisible(false);
			btnRemoverColecao.setVisible(false);

			defaultTableModel = new DefaultTableModel() {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return String.class;
				}

				public boolean isCellEditable(int row, int column) {
					return false; // Torna todas as células não editáveis
				}
			};

			defaultTableModel.addColumn("ID_Moeda");
			defaultTableModel.addColumn("Ano de face");
			defaultTableModel.addColumn("Código de catálogo");
			defaultTableModel.addColumn("diâmetro ");
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
			/*
			 * O for abaixo serve para popular o jTable de moedas disponiveis, caso a lista
			 * de colecoesMoedaEntities esteja vazia ele executa o primeiro if, assim
			 * populando com todas as moedas, caso esteja com itens dentro, ele compara os
			 * itens das listas para que não haja duplicidade
			 */
			for (MoedaEntity moedaEntity : moedaService.listar()) {

				int contador = 0;
				// Popula com todas as moedas
				if (colecaoMoedaEntities.isEmpty() == true) {
					defaultTableModel.addRow(new Object[] { moedaEntity.getId(), moedaEntity.getAnoFace(),
							moedaEntity.getCodCatalogo(), moedaEntity.getDiametro(), moedaEntity.getEspessura(),
							moedaEntity.getPeso(), moedaEntity.getTitulo(), moedaEntity.getValorFace(),
							moedaEntity.getPaisOrigem(), moedaEntity.getBordas(), moedaEntity.getMateriais() });
				} else {
					// adiciona apenas as moedas que o usuario não tem, verificando pelo id
					for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaEntities) {

						contador += 1;

						if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getId() == moedaEntity.getId()) {

							break;

						} else if (contador == colecaoMoedaEntities.size()) {
							defaultTableModel.addRow(new Object[] { moedaEntity.getId(), moedaEntity.getAnoFace(),
									moedaEntity.getCodCatalogo(), moedaEntity.getDiametro(), moedaEntity.getEspessura(),
									moedaEntity.getPeso(), moedaEntity.getTitulo(), moedaEntity.getValorFace(),
									moedaEntity.getPaisOrigem(), moedaEntity.getBordas(), moedaEntity.getMateriais() });
						}
					}
				}
			}
			table = new JTable(defaultTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(table);
			break;

		default:
			break;

		}

	}

	public void adicionarMoedaColecao(JTable table, ColecaoEntity colecaoEntity) {

		// retorna o numero da linha selecionada
		int i = table.getSelectedRow();

		// Pega os dados da linha selecionada
		long id = (long) table.getValueAt(i, 0);
		int anoFace = (int) table.getValueAt(i, 1);
		String codCatalogo = (String) table.getValueAt(i, 2);
		double diametro = (double) table.getValueAt(i, 3);
		double espessura = (double) table.getValueAt(i, 4);
		double peso = (double) table.getValueAt(i, 5);
		String titulo = (String) table.getValueAt(i, 6);
		double valorFace = (double) table.getValueAt(i, 7);
		PaisEntity paisEntity = (PaisEntity) table.getValueAt(i, 8);
		List<BordaEntity> bordasEntity = (List<BordaEntity>) table.getValueAt(i, 9);
		List<MaterialEntity> materiaisEntity = (List<MaterialEntity>) table.getValueAt(i, 10);

		// Cria a moeda com os atributos;
		MoedaEntity moedaEntity = new MoedaEntity(id, titulo, anoFace, valorFace, bordasEntity, materiaisEntity,
				codCatalogo, paisEntity, peso, espessura, diametro, null, null);

		TelaAuxiliarColecao telaAuxiliarColecao = new TelaAuxiliarColecao(moedaEntity, colecaoEntity);
		telaAuxiliarColecao.setVisible(true);
		telaAuxiliarColecao.setLocationRelativeTo(null);
		telaAuxiliarColecao.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				controleTelas(colecaoEntity.getDono(), 0);
			}

		});

	}

	public void editarMoedaColecao(JTable jTable, PessoaEntity pessoa) {
		colecaoMoedaService = new ColecaoMoedaService();
		int i = jTable.getSelectedRow();

		// ColecaoMoedaIdEntity id = (ColecaoMoedaIdEntity) jTable.getValueAt(i, 13);
		// ColecaoMoedaEntity colecaoMoedaEntity = colecaoMoedaService.pesquisar(id);
		String titulo = (String) jTable.getValueAt(i, 0);
		ColecaoMoedaEntity colecaoMoeda = null;

		for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaEntities) {
			if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo().equals(titulo))
				colecaoMoeda = colecaoMoedaEntity;
		}

		TelaAuxiliarColecao telaAuxiliarColecao = new TelaAuxiliarColecao(colecaoMoeda);
		telaAuxiliarColecao.setVisible(true);
		telaAuxiliarColecao.setLocationRelativeTo(null);
		telaAuxiliarColecao.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				controleTelas(pessoa, 0);
			}

		});
	}

	public void removerMoedaColecao(JTable jTable, PessoaEntity pessoa) {

		colecaoMoedaService = new ColecaoMoedaService();
		int i = jTable.getSelectedRow();

		String titulo = (String) jTable.getValueAt(i, 0);
		ColecaoMoedaEntity colecaoMoeda = null;

		for (ColecaoMoedaEntity colecaoMoedaEntity : colecaoMoedaEntities) {
			if (colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo().equals(titulo))
				colecaoMoeda = colecaoMoedaEntity;
		}
		if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Deseja mesmo remover a meoda: "
				+ colecaoMoeda.getIdColecaoMoeda().getIdMoeda().getTitulo() + " ?") == JOptionPane.YES_OPTION) {
			colecaoMoedaService.deletar(colecaoMoeda);
			controleTelas(pessoa, 0);
		}
	}

	public void editarColecao() {

		String titulo = colecaoAtual.getTituloColecao();
		String tituloNovo = JOptionPane.showInputDialog(null, "Digite o novo título para a sua coleção", titulo);
		if (tituloNovo.isBlank() == false && tituloNovo.length() < 256) {
			colecaoAtual.setTituloColecao(tituloNovo);
			colecaoService.salvar(colecaoAtual);
			int index = cbTitulo.getSelectedIndex();

			if (cbTitulo.getItemAt(index) != null) {

				cbTitulo.removeItemAt(index);

			}
			cbTitulo.insertItemAt(tituloNovo, index);
			cbTitulo.setSelectedIndex(index);
			controleTelas(colecaoAtual.getDono(), 0);

		} else {

			JOptionPane.showMessageDialog(null,
					"O título informado está em branco ou passou do limite de 255 caracteres");
		}
	}

	public void removerColecao() {
		if (cbTitulo.getItemAt(0) != null) {
			ColecaoEntity colecao = colecaoAtual;
			if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
					"Deseja remover a coleção: " + colecao.getTituloColecao()) == JOptionPane.OK_OPTION) {
				cbTitulo.removeItem(colecao.getTituloColecao());
				colecaoService.deletar(colecao);
				PessoaEntity pessoa = pessoaService.pesquisar(colecao.getDono());
				controleTelas(pessoa, 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Você não possui nenhuma coleção!");
		}
	}

}
