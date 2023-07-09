package softCoin.service;

import java.util.List;

import softCoin.entity.MoedaEntity;
import softCoin.repository.MoedaRepository;

public class MoedaService {
	
	private MoedaRepository moedaRepository;

	public MoedaService() {

		moedaRepository = new MoedaRepository();

	}

	public boolean salvar(MoedaEntity moeda) {
		boolean salvou = true;
		if (moeda.getId() == 0) {

			MoedaEntity entity = moedaRepository.pesquisarPeloTitulo(moeda.getTitulo());

			if (entity == null) {

				moedaRepository.inserir(moeda);
				salvou = true;
			} else {
				System.out.println("Moeda já cadastrada!");
				return salvou = false;
			}
		} else {
			moedaRepository.atualizar(moeda);
		}
		return salvou;
	}

	public List<MoedaEntity> listar() {

		return moedaRepository.listar();

	}

	public void deletar(MoedaEntity moeda) {
		if (moeda.getId() == 0) {
			moedaRepository.deletar(moeda);
		} else {
			moedaRepository.deletar(moeda.getId());
		}
	}

	public MoedaEntity pesquisar(MoedaEntity moeda) {

		if (moeda.getId() != 0) {

			moeda = moedaRepository.pesquisarPorId(moeda.getId());

		} else if (moeda.getTitulo() != null) {

			moeda = moedaRepository.pesquisarPeloTitulo(moeda.getTitulo());

		}

		return moeda;
	}

}
