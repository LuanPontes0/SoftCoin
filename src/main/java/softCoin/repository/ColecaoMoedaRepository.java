package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import softCoin.entity.ColecaoMoedaEntity;
import softCoin.entity.ColecaoMoedaIdEntity;

public class ColecaoMoedaRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir ColecaoMoeda
	public void inserir(ColecaoMoedaEntity colecao) {
		try {

			em.getTransaction().begin();
			em.persist(colecao);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir colecaoMoeda: " + e.getMessage());
		}
	}

	// Atualizar ColecaoMoeda
	public void atualizar(ColecaoMoedaEntity colecao) {
		try {

			em.getTransaction().begin();
			em.merge(colecao);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar colecaoMoeda: " + e.getMessage());

		}
	}

	// Pesquisa colecaoMoeda pelo ID
	public ColecaoMoedaEntity pesquisarPorId(ColecaoMoedaIdEntity id) {

		ColecaoMoedaEntity colecao = null;

		try {

			colecao = em.find(ColecaoMoedaEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar colecao pelo Id: " + e.getMessage());
		}
		return colecao;
	}

	/*
	 * Pesquisa colecao pelo Nome public ColecaoEntity pesquisarPeloNome(String
	 * nome) {
	 * 
	 * ColecaoEntity pais = null;
	 * 
	 * try { String jpql = "select p from PaisEntity p where p.nomePais = :nome";
	 * 
	 * TypedQuery<ColecaoEntity> tq = em.createQuery(jpql,
	 * ColecaoEntity.class).setParameter("nome", nome);
	 * 
	 * List<ColecaoEntity> paises = tq.getResultList();
	 * 
	 * for (ColecaoEntity pais1 : paises) {
	 * 
	 * return pais1;
	 * 
	 * } } catch (Exception e) {
	 * 
	 * System.out.println(e + "Erro ao buscar País pelo nome"); } return pais; }
	 */

	// Deletar colecaoMoeda por objeto
	public void deletar(ColecaoMoedaEntity colecao) {

		try {

			em.getTransaction().begin();
			em.remove(colecao);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar ColecaoMoeda: " + e.getMessage());
		}
	}

	// Deletar Colecao por ID
	public void deletar(ColecaoMoedaIdEntity id) {
		try {
			ColecaoMoedaEntity colecao = pesquisarPorId(id);
			deletar(colecao);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar colecaoMoeda pelo id: " + e.getMessage());
		}
	}

	// Listar coleçãoMoeda
	public List<ColecaoMoedaEntity> listar() {

		List<ColecaoMoedaEntity> paises = null;
		Query query = em.createQuery("Select c from ColecaoMoedaEntity c");
		try {

			paises = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar colecoes: " + e.getMessage());
		}
		return paises;
	}
}
