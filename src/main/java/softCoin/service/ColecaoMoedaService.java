package softCoin.service;

import java.util.List;

import softCoin.entity.ColecaoMoedaEntity;
import softCoin.entity.ColecaoMoedaIdEntity;
import softCoin.repository.ColecaoMoedaRepository;

public class ColecaoMoedaService {
	
	private ColecaoMoedaRepository colecaoMoedaRepository;

	public ColecaoMoedaService() {

		colecaoMoedaRepository = new ColecaoMoedaRepository();

	}

	public ColecaoMoedaEntity salvar(ColecaoMoedaEntity colecao) {

		if (colecao.getIdColecaoMoeda() == null) {

			ColecaoMoedaEntity entity = null;

			if (entity == null) {

				colecaoMoedaRepository.inserir(colecao);

			} else {
				System.out.println("Colecao já cadastrado!");
			}
		} else {
			colecaoMoedaRepository.atualizar(colecao);
		}
		return colecao;
	}

	public List<ColecaoMoedaEntity> listar() {

		return colecaoMoedaRepository.listar();

	}

	public void deletar(ColecaoMoedaEntity colecao) {
		if (colecao.getIdColecaoMoeda() == null) {
			colecaoMoedaRepository.deletar(colecao);
		} else {
			colecaoMoedaRepository.deletar(colecao.getIdColecaoMoeda());

		}
	}

	public ColecaoMoedaEntity pesquisar(ColecaoMoedaIdEntity id) {
		return colecaoMoedaRepository.pesquisarPorId(id);
	}
}
