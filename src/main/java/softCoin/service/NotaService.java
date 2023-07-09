package softCoin.service;

import java.util.List;

import softCoin.entity.NotaEntity;
import softCoin.repository.NotaRepository;

public class NotaService {

	private NotaRepository notaRepository;

	public NotaService() {

		notaRepository = new NotaRepository();

	}

	public NotaEntity salvar(NotaEntity nota) {

		if (nota.getId() == 0) {

			NotaEntity entity = null;//notaRepository.pesquisarPeloNome(nota.getNomePais());

			if (entity == null) {

				notaRepository.inserir(nota);

			} else {
				System.out.println("nota já cadastrado!");
			}
		} else {
			notaRepository.atualizar(nota);
		}
		return nota;
	}

	public List<NotaEntity> listar() {

		return notaRepository.listar();

	}

	public void deletar(NotaEntity nota) {
		if (nota.getId() == 0) {
			notaRepository.deletar(nota);
		} else {
			notaRepository.deletar(nota.getId());
		}
	}
}
