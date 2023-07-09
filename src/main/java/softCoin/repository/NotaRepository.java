package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.NotaEntity;
import softCoin.entity.PaisEntity;

public class NotaRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir nota
	public void inserir(NotaEntity nota) {
		try {

			em.getTransaction().begin();
			em.persist(nota);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir nota: " + e.getMessage());
		}
	}

	// Atualizar nota
	public void atualizar(NotaEntity nota) {
		try {

			em.getTransaction().begin();
			em.merge(nota);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar nota: " + e.getMessage());

		}
	}

	// Pesquisa nota pelo ID
	public NotaEntity pesquisarPorId(long id) {

		NotaEntity nota = null;

		try {

			nota = em.find(NotaEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar nota pelo Id: " + e.getMessage());
		}
		return nota;
	}

	// Pesquisa nota pelo Nome
	/*
	 * public NotaEntity pesquisarPeloNome(String nome) {
	 * 
	 * NotaEntity pais = null;
	 * 
	 * try { String jpql = "select p from PaisEntity p where p.nomePais = :nome";
	 * 
	 * TypedQuery<NotaEntity> tq = em.createQuery(jpql,
	 * NotaEntity.class).setParameter("nome", nome);
	 * 
	 * List<NotaEntity> paises = tq.getResultList();
	 * 
	 * for (NotaEntity pais1 : paises) {
	 * 
	 * return pais1;
	 * 
	 * } } catch (Exception e) {
	 * 
	 * System.out.println(e + "Erro ao buscar País pelo nome"); } return pais; }
	 */
	// Deletar nota por objeto
	public void deletar(NotaEntity nota) {

		try {

			em.getTransaction().begin();
			em.remove(nota);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar nota: " + e.getMessage());
		}
	}

	// Deletar nota por ID
	public void deletar(long id) {
		try {
			NotaEntity nota = pesquisarPorId(id);
			deletar(nota);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar nota: " + e.getMessage());
		}
	}

	// Listar notas
	public List<NotaEntity> listar() {

		List<NotaEntity> notas = null;
		Query query = em.createQuery("Select n from NotaEntity n");
		try {

			notas = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar notas: " + e.getMessage());
		}
		return notas;
	}
}
