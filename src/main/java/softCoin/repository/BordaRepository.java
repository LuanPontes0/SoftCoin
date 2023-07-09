package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.BordaEntity;

public class BordaRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir Borda
	public void inserir(BordaEntity borda) {
		try {

			em.getTransaction().begin();
			em.persist(borda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir cadastro Borda: " + e.getMessage());
		}
	}

	// Atualizar Borda
	public boolean atualizar(BordaEntity borda) {
		boolean erro = true;
		try {
			
			em.getTransaction().begin();
			em.merge(borda);
			em.getTransaction().commit();

		} catch (Exception e) {
			
			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar Borda: " + e.getMessage());
			erro = false;
			
		}
		return erro;
	}

	// Pesquisa Borda pelo ID
	public BordaEntity pesquisarPorId(int id) {

		BordaEntity borda = null;

		try {

			borda = em.find(BordaEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar Borda pelo Id: " + e.getMessage());
		}
		return borda;
	}

	// Pesquisa Borda pelo Nome
	public BordaEntity pesquisarPeloNome(String nome) {

		BordaEntity borda = null;

		try {
			String jpql = "select b from BordaEntity b where b.tipoBorda = :nome";

			TypedQuery<BordaEntity> tq = em.createQuery(jpql, BordaEntity.class).setParameter("nome", nome);

			List<BordaEntity> bordas = tq.getResultList();

			for (BordaEntity borda1 : bordas) {

				return borda1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar borda pelo nome");
		}
		return borda;
	}

	// Deletar Borda por objeto
	public void deletar(BordaEntity borda) {

		try {

			em.getTransaction().begin();
			em.remove(borda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Borda: " + e.getMessage());
		}
	}

	// Deletar Borda por ID
	public void deletar(int id) {
		try {
			BordaEntity borda = pesquisarPorId(id);
			deletar(borda);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar borda: " + e.getMessage());
		}
	}

	// Listar bordas
	public List<BordaEntity> listar() {

		List<BordaEntity> bordas = null;
		Query query = em.createQuery("Select b from BordaEntity b order by b.id");
		try {

			bordas = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar bordas: " + e.getMessage());
		}
		return bordas;
	}
}
