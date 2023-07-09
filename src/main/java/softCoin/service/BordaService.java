package softCoin.service;

import java.util.List;

import softCoin.entity.BordaEntity;
import softCoin.repository.BordaRepository;

public class BordaService {

	private BordaRepository bordaRepository;

	public BordaService() {

		bordaRepository = new BordaRepository();

	}

	public boolean salvar(BordaEntity borda) {
		boolean achou = true;
		if (borda.getId() == 0) {

			BordaEntity entity = bordaRepository.pesquisarPeloNome(borda.getTipoBorda());

			if (entity == null) {

				bordaRepository.inserir(borda);
				return achou = true;

			} else {
				System.out.println("Borda já cadastrada!");
				return achou = false;
			}
		} else {
			achou = bordaRepository.atualizar(borda);
		}
		return achou;
	}

	public List<BordaEntity> listar() {

		return bordaRepository.listar();

	}

	public void deletar(BordaEntity borda) {
		if (borda.getId() == 0) {
			bordaRepository.deletar(borda);
		} else {
			bordaRepository.deletar(borda.getId());
		}
	}
}
