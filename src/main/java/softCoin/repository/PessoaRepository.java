package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.PessoaEntity;

public class PessoaRepository {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir Pessoa
	public void inserir(PessoaEntity pessoa) {
		try {

			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir cadastro Pessoa: " + e.getMessage());
		}
	}

	// Atualizar Pessoa
	public void atualizar(PessoaEntity pessoa) {
		try {

			em.getTransaction().begin();
			em.merge(pessoa);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar Pessoa: " + e.getMessage());

		}
	}

	// Pesquisa Pessoa pelo ID
	public PessoaEntity pesquisarPorId(int id) {

		PessoaEntity pessoa = null;

		try {

			pessoa = em.find(PessoaEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar Pessoa pelo Id: " + e.getMessage());
		}
		return pessoa;
	}

	// Pesquisa Pessoa pelo Nome
	public PessoaEntity pesquisarPeloNome(String nome) {

		PessoaEntity pessoa = null;

		try {
			String jpql = "select p from PessoaEntity p where p.nomeCompleto = :nome";

			TypedQuery<PessoaEntity> tq = em.createQuery(jpql, PessoaEntity.class).setParameter("nome", nome);

			List<PessoaEntity> pessoas = tq.getResultList();

			for (PessoaEntity pessoa1 : pessoas) {

				return pessoa1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar Pessoa pelo nome");
		}
		return pessoa;
	}

	// Pesquisa pelo CPF
	public PessoaEntity pesquisarPeloCpf(String cpf) {

		PessoaEntity pessoa = null;

		try {
			String jpql = "select p from PessoaEntity p where p.cpf = :cpf";

			TypedQuery<PessoaEntity> tq = em.createQuery(jpql, PessoaEntity.class).setParameter("cpf", cpf);

			List<PessoaEntity> pessoas = tq.getResultList();

			for (PessoaEntity pessoa1 : pessoas) {

				return pessoa1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar Pessoa pelo cpf");
		}
		return pessoa;
	}

	// Pesquisa pelo E-MAIL
	public PessoaEntity pesquisarPeloEmail(String email) {

		PessoaEntity pessoa = null;

		try {
			String jpql = "select p from PessoaEntity p where p.email = :email";

			TypedQuery<PessoaEntity> tq = em.createQuery(jpql, PessoaEntity.class).setParameter("email", email);

			List<PessoaEntity> pessoas = tq.getResultList();

			for (PessoaEntity pessoa1 : pessoas) {

				return pessoa1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar Pessoa pelo email");
		}
		return pessoa;
	}

	// Deletar Pessoa por objeto
	public void deletar(PessoaEntity pessoa) {

		try {

			em.getTransaction().begin();
			em.remove(pessoa);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Pessoa pelo objeto: " + e.getMessage());
		}
	}

	// Deletar País por ID
	public void deletar(int id) {
		try {
			PessoaEntity pessoa = pesquisarPorId(id);
			deletar(pessoa);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Pessoa pelo id: " + e.getMessage());
		}
	}

	// Listar Pessoas
	public List<PessoaEntity> listar() {

		List<PessoaEntity> pessoas = null;
		Query query = em.createQuery("Select p from PessoaEntity p");
		try {

			pessoas = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar Pessoas: " + e.getMessage());
		}
		return pessoas;
	}
}
