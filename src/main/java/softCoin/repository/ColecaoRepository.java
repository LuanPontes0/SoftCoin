package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.ColecaoEntity;

public class ColecaoRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir Colecao
	public void inserir(ColecaoEntity colecao) {
		try {

			em.getTransaction().begin();
			em.persist(colecao);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir colecao: " + e.getMessage());
		} 
	}

	// Atualizar Colecao
	public void atualizar(ColecaoEntity colecao) {
		try {

			em.getTransaction().begin();
			em.merge(colecao);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar colecao: " + e.getMessage());

		} 
	}

	// Pesquisa colecao pelo ID
	public ColecaoEntity pesquisarPorId(long id) {

		ColecaoEntity colecao = null;

		try {

			colecao = em.find(ColecaoEntity.class, id);

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

	// Deletar colecao por objeto
	public void deletar(ColecaoEntity colecao) {

		try {

			em.getTransaction().begin();
			em.remove(colecao);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Colecao: " + e.getMessage());
		} 
	}

	// Deletar Colecao por ID
	public void deletar(long id) {
		try {
			ColecaoEntity colecao = pesquisarPorId(id);
			deletar(colecao);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar colecao pelo id: " + e.getMessage());
		} 
	}

	// Listar coleção
	public List<ColecaoEntity> listar() {

		List<ColecaoEntity> colecao = null;
		Query query = em.createQuery("Select c from ColecaoEntity c");
		try {

			colecao = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar colecoes: " + e.getMessage());
		} 
		return colecao;
	}
}
