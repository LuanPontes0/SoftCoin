package softCoin.util;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import softCoin.entity.ColecaoEntity;
import softCoin.entity.PessoaEntity;

public class CaixaConfirmacaoUtil {

	private String mensagem;

	public static int chamarCaixaConfirmacao(String textoMensagem) {
		int respostaUser = 0;

		Object[] options = { "Sim", "Não" };
		int selection = JOptionPane.showOptionDialog(null, textoMensagem, "Confirmação", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		return respostaUser = selection;

	}

	public static int chamarTermosCondicoes() {

		JTextArea textArea = new JTextArea(20, 50);
		textArea.setLineWrap(true); // Quebra de linha automática
		textArea.setWrapStyleWord(true); // Quebra de linha por palavras
		textArea.setText("Política de Privacidade e Proteção de Dados\r\n" + "\r\n"
				+ "Esta Política de Privacidade descreve como são coletadas, usadas, armazenadas e protegidas as informações pessoais dos usuários ao utilizar este aplicativo. É importante que você leia com atenção as seguintes informações antes de fornecer qualquer dado pessoal.\r\n"
				+ "\r\n" + "Informações Coletadas\r\n"
				+ "Podemos coletar as seguintes informações pessoais dos usuários:\r\n" + "\r\n"
				+ "1.1. Informações de identificação: nome, endereço de e-mail, número de telefone e outros dados de contato fornecidos voluntariamente pelo usuário.\r\n"
				+ "\r\n"
				+ "1.2. Informações de uso: dados sobre a forma como o usuário utiliza o aplicativo, incluindo informações de acesso, tempo de utilização, páginas visitadas e recursos acessados.\r\n"
				+ "\r\n"
				+ "1.3. Informações do dispositivo: informações técnicas sobre o dispositivo utilizado para acessar o aplicativo, como endereço IP, tipo de navegador, sistema operacional, provedor de serviços de internet e outros dados similares.\r\n"
				+ "\r\n" + "Uso das Informações\r\n"
				+ "As informações coletadas são utilizadas para os seguintes fins:\r\n" + "\r\n"
				+ "2.1. Fornecer e melhorar os serviços do aplicativo.\r\n" + "\r\n"
				+ "2.2. Personalizar a experiência do usuário, adaptando o conteúdo e os recursos de acordo com suas preferências.\r\n"
				+ "\r\n"
				+ "2.3. Enviar comunicações relevantes, como atualizações, notificações e informações sobre os serviços prestados.\r\n"
				+ "\r\n"
				+ "2.4. Realizar pesquisas e análises para aprimorar a qualidade e a segurança do aplicativo.\r\n"
				+ "\r\n" + "Compartilhamento de Informações\r\n"
				+ "Nós não compartilhamos informações pessoais dos usuários com terceiros, exceto nas seguintes circunstâncias:\r\n"
				+ "\r\n"
				+ "3.1. Com o consentimento do usuário: podemos compartilhar informações pessoais com terceiros caso o usuário tenha dado consentimento expresso para essa finalidade.\r\n"
				+ "\r\n"
				+ "3.2. Parceiros de serviço: podemos compartilhar informações pessoais com parceiros de serviço que nos auxiliam na operação do aplicativo, desde que eles também garantam a proteção dessas informações.\r\n"
				+ "\r\n"
				+ "3.3. Cumprimento legal: podemos compartilhar informações pessoais caso seja necessário cumprir com obrigações legais, regulatórias ou responder a solicitações de autoridades competentes.\r\n"
				+ "\r\n" + "Armazenamento e Segurança\r\n"
				+ "As informações pessoais são armazenadas de forma segura em servidores protegidos e acessíveis apenas por pessoas autorizadas. Utilizamos medidas técnicas e organizacionais adequadas para proteger as informações contra acesso não autorizado, uso indevido, divulgação, alteração ou destruição.\r\n"
				+ "\r\n" + "Direitos dos Usuários\r\n"
				+ "Os usuários têm direito a solicitar acesso, correção, exclusão e portabilidade de suas informações pessoais, bem como a limitação do seu uso. Caso deseje exercer esses direitos ou tenha qualquer dúvida sobre esta Política de Privacidade, entre em contato conosco por meio dos canais disponibilizados no aplicativo.\r\n"
				+ "\r\n" + "Atualizações da Política de Privacidade\r\n"
				+ "Esta Política de Privacidade pode ser atualizada periodicamente para refletir mudanças nas práticas de coleta e uso de informações. Recomendamos que os usuários revisem regularmente esta página para estar cientes de quaisquer alterações. A continuação do uso do aplicativo após a publicação de alterações constitui aceitação das mesmas.\r\n"
				+ "\r\n" + "Consentimento\r\n"
				+ "Ao utilizar este aplicativo, o usuário concorda com a coleta, uso, armazenamento e compartilhamento de suas informações pessoais de acordo com esta Política de Privacidade.\r\n"
				+ "\r\n" + "Última atualização: [19 de Junho de 2023]");

		// Crie um painel de rolagem e adicione a área de texto a ele
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Exiba o diálogo de mensagem com o painel de rolagem
		JOptionPane.showConfirmDialog(null, scrollPane, "Política de Privacidade e Proteção de Dados",
				JOptionPane.YES_NO_OPTION);

		return 0;

	}

	public static ColecaoEntity chamarComboBox(String textoMensagem, PessoaEntity pessoa) {
		// Cria um array de opções para o JComboBox
		List<ColecaoEntity> colecoesUser = pessoa.getColecoes();

		// Cria um JComboBox com as opções
		DefaultComboBoxModel<ColecaoEntity> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (ColecaoEntity colecaoEntity : colecoesUser) {
			defaultComboBoxModel.addElement(colecaoEntity);
		}

		JComboBox<ColecaoEntity> comboBox = new JComboBox<>(defaultComboBoxModel);

		// Exibe um JOptionPane.showInputDialog com o JComboBox
		int resultado = JOptionPane.showOptionDialog(null, comboBox, textoMensagem, JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		// Verifica se o usuário selecionou uma opção e exibe a opção selecionada
		if (resultado == JOptionPane.OK_OPTION) {
			ColecaoEntity opcaoSelecionada = (ColecaoEntity) comboBox.getSelectedItem();
			return opcaoSelecionada;
		}
		return null;
	}

	public static int chamarTelaAjuda(String mensagem) {

		JTextArea textArea = new JTextArea(20, 50);
		textArea.setLineWrap(true); // Quebra de linha automática
		textArea.setWrapStyleWord(true); // Quebra de linha por palavras
		textArea.setText(mensagem);

		// Crie um painel de rolagem e adicione a área de texto a ele
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Exiba o diálogo de mensagem com o painel de rolagem
		JOptionPane.showMessageDialog(null, scrollPane, "Ajuda", JOptionPane.INFORMATION_MESSAGE);

		return 0;

	}
}
