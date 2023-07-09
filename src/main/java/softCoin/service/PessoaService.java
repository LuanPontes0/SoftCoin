package softCoin.service;

import java.util.List;

import softCoin.entity.PessoaEntity;
import softCoin.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pessoaRepository;

	public PessoaService() {

		pessoaRepository = new PessoaRepository();

	}

	public boolean salvar(PessoaEntity pessoa) {
		boolean achou = true;

		if (pessoa.getId() == 0) {
			PessoaEntity entity = null;
			int i = 1;
			switch (i) {
			case 0:
				/*
				 * entity = pessoaRepository.pesquisarPeloNome(pessoa.getNomeCompleto()); if
				 * (entity != null) { return achou = true;
				 * 
				 * } else i = 1;
				 */
			case 1:
				entity = pessoaRepository.pesquisarPeloCpf(pessoa.getCpf());
				if (entity != null) {
					return achou = true;

				} else {
					i = 2;
				}

			case 2:
				entity = pessoaRepository.pesquisarPeloEmail(pessoa.getEmail());
				if (entity != null) {
					return achou = true;

				} else {
					i = 1;
				}
			default:

				break;
			}

			if (entity == null) {

				pessoaRepository.inserir(pessoa);
				return achou = false;

			} else {
				System.out.println("Pessoa já cadastrada!");
				return achou = true;
			}
		} else {
			pessoaRepository.atualizar(pessoa);
			achou = false;
		}
		return achou;
	}

	public List<PessoaEntity> listar() {

		return pessoaRepository.listar();

	}

	public void deletar(PessoaEntity pessoa) {

		if (pessoa.getId() == 0) {
			pessoaRepository.deletar(pessoa);
		} else {
			pessoaRepository.deletar(pessoa.getId());
		}
	}

	public PessoaEntity pesquisar(PessoaEntity pessoa) {
		PessoaEntity pessoaPesquisada = null;

		// Pesquisa pelo id da pessoa
		if (pessoa.getId() != 0) {

			pessoaPesquisada = pessoaRepository.pesquisarPorId(pessoa.getId());

		} // Pesquisa pelo E-mail da pessoa
		else if (pessoa.getEmail() != null) {

			pessoaPesquisada = pessoaRepository.pesquisarPeloEmail(pessoa.getEmail());

		} // Pesquisa pelo CPF da pessoa
		else if (pessoa.getCpf() != null) {

			pessoaPesquisada = pessoaRepository.pesquisarPeloCpf(pessoa.getCpf());

		}
		return pessoaPesquisada;
	}

}
