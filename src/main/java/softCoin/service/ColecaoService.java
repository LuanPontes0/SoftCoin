package softCoin.service;

import java.util.List;

import softCoin.entity.ColecaoEntity;
import softCoin.repository.ColecaoRepository;

public class ColecaoService {
	
	private ColecaoRepository colecaoRepository;

	public ColecaoService() {

		colecaoRepository = new ColecaoRepository();

	}

	public ColecaoEntity salvar(ColecaoEntity colecao) {

		if (colecao.getId() == 0) {

			ColecaoEntity entity = null ;

			if (entity == null) {

				colecaoRepository.inserir(colecao);

			} else {
				System.out.println("Colecao já cadastrado!");
			}
		} else {
			colecaoRepository.atualizar(colecao);
		}
		return colecao;
	}

	public List<ColecaoEntity> listar() {

		return colecaoRepository.listar();

	}

	public void deletar(ColecaoEntity colecao) {
		if (colecao.getId() == 0) {
			colecaoRepository.deletar(colecao);
		} else {
			colecaoRepository.deletar(colecao.getId());
		}
	}
}
