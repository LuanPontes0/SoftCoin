package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.PaisEntity;

public class PaisRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir País
	public void inserir(PaisEntity pais) {
		try {

			em.getTransaction().begin();
			em.persist(pais);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir cadastro Pais: " + e.getMessage());
		}
	}

	// Atualizar País
	public boolean atualizar(PaisEntity pais) {

		boolean erro = true;
		try {

			em.getTransaction().begin();
			em.merge(pais);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar Pais: " + e.getMessage());
			erro = false;
		}
		return erro;
	}

	// Pesquisa Pais pelo ID
	public PaisEntity pesquisarPorId(int id) {

		PaisEntity pais = null;

		try {

			pais = em.find(PaisEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar Pais pelo Id: " + e.getMessage());
		}
		return pais;
	}

	// Pesquisa Pais pelo Nome
	public PaisEntity pesquisarPeloNome(String nome) {

		PaisEntity pais = null;

		try {
			String jpql = "select p from PaisEntity p where p.nomePais = :nome";

			TypedQuery<PaisEntity> tq = em.createQuery(jpql, PaisEntity.class).setParameter("nome", nome);

			List<PaisEntity> paises = tq.getResultList();

			for (PaisEntity pais1 : paises) {

				return pais1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar País pelo nome");
		}
		return pais;
	}

	// Deletar País por objeto
	public void deletar(PaisEntity pais) {

		try {

			em.getTransaction().begin();
			em.remove(pais);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Pais: " + e.getMessage());
		}
	}

	// Deletar País por ID
	public void deletar(int id) {
		try {
			PaisEntity pais = pesquisarPorId(id);
			deletar(pais);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Pais: " + e.getMessage());
		}
	}

	// Listar Países
	public List<PaisEntity> listar() {

		List<PaisEntity> paises = null;
		Query query = em.createQuery("Select p from PaisEntity p order by p.id");
		try {

			paises = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar Países: " + e.getMessage());
		}
		return paises;
	}
}
