package softCoin.service;

import java.util.List;

import softCoin.entity.PaisEntity;
import softCoin.repository.PaisRepository;

public class PaisService {

	private PaisRepository paisRepository;

	public PaisService() {

		paisRepository = new PaisRepository();

	}

	public boolean salvar(PaisEntity pais) {
		boolean achou = true;
		if (pais.getId() == 0) {

			PaisEntity entity = paisRepository.pesquisarPeloNome(pais.getNomePais());

			if (entity == null) {

				paisRepository.inserir(pais);
				return achou = true;

			} else {
				System.out.println("País já cadastrado!");
				return achou = false;
			}
		} else {

			achou = paisRepository.atualizar(pais);
		}
		return achou;
	}

	public List<PaisEntity> listar() {

		return paisRepository.listar();

	}

	public void deletar(PaisEntity pais) {
		if (pais.getId() == 0) {
			paisRepository.deletar(pais);
		} else {
			paisRepository.deletar(pais.getId());
		}
	}
}
