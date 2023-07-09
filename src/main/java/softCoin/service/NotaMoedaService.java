package softCoin.service;

import java.util.List;

import softCoin.entity.NotaMoedaEntity;
import softCoin.entity.NotaMoedaID;
import softCoin.entity.ColecaoMoedaIdEntity;
import softCoin.repository.NotaMoedaRepository;

public class NotaMoedaService {

	private NotaMoedaRepository notaMoedaRepository;

	public NotaMoedaService() {

		notaMoedaRepository = new NotaMoedaRepository();

	}

	public NotaMoedaEntity salvar(NotaMoedaEntity notaMoeda) {

		if (notaMoeda.getIdNotaMoeda() == null) {

			NotaMoedaEntity entity = null;

			if (entity == null) {

				notaMoedaRepository.inserir(notaMoeda);

			} else {
				System.out.println("NotaMoeda já cadastrada!");
			}
		} else {
			notaMoedaRepository.atualizar(notaMoeda);
		}
		return notaMoeda;
	}

	public List<NotaMoedaEntity> listar() {

		return notaMoedaRepository.listar();

	}

	public void deletar(NotaMoedaEntity notaMoeda) {
		if (notaMoeda.getIdNotaMoeda() == null) {
			notaMoedaRepository.deletar(notaMoeda);
		} else {
			notaMoedaRepository.deletar(notaMoeda.getIdNotaMoeda());

		}
	}

	public NotaMoedaEntity pesquisar(NotaMoedaID id) {
		return notaMoedaRepository.pesquisarPorId(id);
	}
}
