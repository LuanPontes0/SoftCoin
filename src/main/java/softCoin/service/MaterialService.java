package softCoin.service;

import java.util.List;

import softCoin.entity.MaterialEntity;
import softCoin.repository.MaterialRepository;

public class MaterialService {

	private MaterialRepository materialRepository;

	public MaterialService() {

		materialRepository = new MaterialRepository();

	}

	public boolean salvar(MaterialEntity material) {
		boolean achou = true;
		if (material.getId() == 0) {

			MaterialEntity entity = materialRepository.pesquisarPeloNome(material.getNomeMaterial());

			if (entity == null) {

				
				materialRepository.inserir(material);
				return achou = true;

			} else {
				System.out.println("Material já cadastrado!");
				return achou = false;
			}
		} else {
			achou = materialRepository.atualizar(material);
		}
		return achou;
	}

	public List<MaterialEntity> listar() {

		return materialRepository.listar();

	}

	public void deletar(MaterialEntity material) {
		if (material.getId() == 0) {
			materialRepository.deletar(material);
		} else {
			materialRepository.deletar(material.getId());
		}
	}
}
