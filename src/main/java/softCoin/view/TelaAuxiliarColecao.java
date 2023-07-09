package softCoin.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import softCoin.entity.ColecaoEntity;
import softCoin.entity.ColecaoMoedaEntity;
import softCoin.entity.ColecaoMoedaIdEntity;
import softCoin.entity.MoedaEntity;
import softCoin.service.ColecaoMoedaService;
import softCoin.service.ColecaoService;
import softCoin.service.MoedaService;
import softCoin.util.CaixaConfirmacaoUtil;

public class TelaAuxiliarColecao extends JFrame {

	private JTable table;
	private MoedaService moedaService;
	private ColecaoMoedaService colecaoMoedaService;
	private JTextField txtMoedaSelecionada;
	private JSpinner spnQuantidade;
	private JRadioButton rdbtnDisponivelVenda;
	private JLabel lblValorUnitario;
	private JLabel lblQuantidade;
	private JLabel lblDisponivelVenda;
	private JButton btnSalvar;
	private JLabel lblMoedaSelecionada;
	private JSpinner spnValorUnitario;
	private ColecaoService colecaoService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAuxiliarColecao frame = new TelaAuxiliarColecao(null);
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
	 * @wbp.parser.constructor
	 */
	public TelaAuxiliarColecao(ColecaoMoedaEntity colecaoMoedaEntity) {
		setTitle("Auxiliar de coleção");

		setBounds(100, 100, 422, 313);
		getContentPane().setLayout(null);

		spnQuantidade = new JSpinner();
		spnQuantidade
				.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spnQuantidade.setValue(colecaoMoedaEntity.getQuantidade());
		spnQuantidade.setBounds(149, 110, 95, 20);
		getContentPane().add(spnQuantidade);

		rdbtnDisponivelVenda = new JRadioButton("");
		rdbtnDisponivelVenda.setBounds(158, 148, 109, 23);
		rdbtnDisponivelVenda.setSelected(colecaoMoedaEntity.isDisponivelVenda());
		getContentPane().add(rdbtnDisponivelVenda);

		lblValorUnitario = new JLabel("Valor unitário:");
		lblValorUnitario.setBounds(27, 82, 95, 14);
		getContentPane().add(lblValorUnitario);

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(27, 113, 77, 14);
		getContentPane().add(lblQuantidade);

		lblDisponivelVenda = new JLabel("Disponível para venda:");
		lblDisponivelVenda.setBounds(14, 152, 140, 14);
		getContentPane().add(lblDisponivelVenda);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				atualizarMoedaColecao(colecaoMoedaEntity);

			}
		});

		btnSalvar.setBounds(104, 226, 194, 37);
		getContentPane().add(btnSalvar);

		txtMoedaSelecionada = new JTextField();
		txtMoedaSelecionada.setEditable(false);
		txtMoedaSelecionada.setBounds(149, 44, 231, 20);
		txtMoedaSelecionada.setText(colecaoMoedaEntity.getIdColecaoMoeda().getIdMoeda().getTitulo());
		getContentPane().add(txtMoedaSelecionada);
		txtMoedaSelecionada.setColumns(10);

		lblMoedaSelecionada = new JLabel("Moeda selecionada:");
		lblMoedaSelecionada.setBounds(10, 47, 129, 14);
		getContentPane().add(lblMoedaSelecionada);

		spnValorUnitario = new JSpinner();
		spnValorUnitario.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		spnValorUnitario.setBounds(149, 79, 118, 20);
		getContentPane().add(spnValorUnitario);
		spnValorUnitario.setValue(colecaoMoedaEntity.getValorUnitario());

	}

	public TelaAuxiliarColecao(MoedaEntity moeda, ColecaoEntity colecaoAtual) {
		setTitle("Auxiliar de coleção");
		setBounds(100, 100, 422, 313);
		getContentPane().setLayout(null);

		spnQuantidade = new JSpinner();
		spnQuantidade
				.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spnQuantidade.setBounds(149, 110, 95, 20);
		getContentPane().add(spnQuantidade);

		spnValorUnitario = new JSpinner();
		spnValorUnitario.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		spnValorUnitario.setBounds(149, 79, 118, 20);
		getContentPane().add(spnValorUnitario);

		rdbtnDisponivelVenda = new JRadioButton("");
		rdbtnDisponivelVenda.setBounds(158, 148, 109, 23);
		getContentPane().add(rdbtnDisponivelVenda);

		lblValorUnitario = new JLabel("Valor unitário:");
		lblValorUnitario.setBounds(27, 82, 95, 14);
		getContentPane().add(lblValorUnitario);

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(27, 113, 77, 14);
		getContentPane().add(lblQuantidade);

		lblDisponivelVenda = new JLabel("Disponível para venda:");
		lblDisponivelVenda.setBounds(14, 152, 140, 14);
		getContentPane().add(lblDisponivelVenda);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(104, 226, 194, 37);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					inserirMoedaColecao(moeda, colecaoAtual);
					setVisible(false);

				} catch (Exception e1) {
					setVisible(true);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnSalvar);

		txtMoedaSelecionada = new JTextField();
		txtMoedaSelecionada.setEditable(false);
		txtMoedaSelecionada.setBounds(149, 44, 231, 20);
		getContentPane().add(txtMoedaSelecionada);
		txtMoedaSelecionada.setColumns(10);
		txtMoedaSelecionada.setText(moeda.getTitulo());

		lblMoedaSelecionada = new JLabel("Moeda selecionada:");
		lblMoedaSelecionada.setBounds(10, 47, 129, 14);
		getContentPane().add(lblMoedaSelecionada);

	}

	public int inserirMoedaColecao(MoedaEntity moeda, ColecaoEntity colecao) {
		int erro = 0;
		colecaoService = new ColecaoService();

		ColecaoMoedaEntity colecaoMoedaEntity = new ColecaoMoedaEntity();
		ColecaoMoedaIdEntity colecaoMoedaIdEntity = new ColecaoMoedaIdEntity();
		colecaoMoedaIdEntity.setIdColecao(colecao);
		colecaoMoedaIdEntity.setIdMoeda(moeda);

		colecaoMoedaEntity.setIdColecaoMoeda(colecaoMoedaIdEntity);
		colecaoMoedaEntity.setQuantidade((int) spnQuantidade.getValue());
		colecaoMoedaEntity.setValorUnitario((float) spnValorUnitario.getValue());
		colecaoMoedaEntity.setDisponivelVenda(rdbtnDisponivelVenda.isSelected());

		if ((int) spnQuantidade.getValue() > 1000000 || (int) spnQuantidade.getValue() <= 0) {
			JOptionPane.showMessageDialog(null, "quantidade menor ou igual a zero || quantidade maior que um milhão ");
			return erro = 1;
		}

		if (Float.parseFloat(spnValorUnitario.getValue().toString()) > 10000000
				|| Float.parseFloat(spnValorUnitario.getValue().toString()) <= 0) {
			JOptionPane.showMessageDialog(null,
					"valor unitário menor ou igual a zero || valor unitário maior que dez milhões ");
			return erro = 1;
		}

		if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao(
				"Adicionar moeda a coleção: " + colecao.getTituloColecao() + " ?") == JOptionPane.YES_OPTION) {
			colecao.getColecaoMoeda().add(colecaoMoedaEntity);
			try {
				colecaoService.salvar(colecao);
				this.dispose();
			} catch (Exception e) {
				erro = 1;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return erro;
	}

	public int atualizarMoedaColecao(ColecaoMoedaEntity colecaoMoedaEntity) {
		int erro = 0;
		colecaoMoedaService = new ColecaoMoedaService();
		colecaoMoedaEntity.setQuantidade((int) spnQuantidade.getValue());
		colecaoMoedaEntity.setValorUnitario((float) spnValorUnitario.getValue());
		colecaoMoedaEntity.setDisponivelVenda(rdbtnDisponivelVenda.isSelected());

		if ((int) spnQuantidade.getValue() > 1000000 || (int) spnQuantidade.getValue() <= 0) {
			JOptionPane.showMessageDialog(null, "quantidade menor ou igual a zero || quantidade maior que um milhão ");
			return erro = 1;
		}

		if (Float.parseFloat(spnValorUnitario.getValue().toString()) > 10000000
				|| Float.parseFloat(spnValorUnitario.getValue().toString()) <= 0) {
			JOptionPane.showMessageDialog(null,
					"valor unitário menor ou igual a zero || valor unitário maior que dez milhões ");
			return erro = 1;
		}

		if (CaixaConfirmacaoUtil.chamarCaixaConfirmacao("Deseja salvar as alterações ?") == JOptionPane.YES_OPTION) {
			try {
				colecaoMoedaService.salvar(colecaoMoedaEntity);
				this.dispose();
			} catch (Exception e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
				erro = 1;
			}
		}
		return erro;
	}
}
