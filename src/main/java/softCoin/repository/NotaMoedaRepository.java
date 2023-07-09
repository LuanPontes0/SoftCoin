package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import softCoin.entity.NotaMoedaEntity;
import softCoin.entity.NotaMoedaID;

public class NotaMoedaRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir ColecaoMoeda
	public void inserir(NotaMoedaEntity notaMoeda) {
		try {

			em.getTransaction().begin();
			em.persist(notaMoeda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir NotaMoeda: " + e.getMessage());
		} 
	}

	// Atualizar ColecaoMoeda
	public void atualizar(NotaMoedaEntity notaMoeda) {
		try {

			em.getTransaction().begin();
			em.merge(notaMoeda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar notaMoeda: " + e.getMessage());

		} 
	}

	// Pesquisa colecaoMoeda pelo ID
	public NotaMoedaEntity pesquisarPorId(NotaMoedaID id) {

		NotaMoedaEntity notaMoeda = null;

		try {

			notaMoeda = em.find(NotaMoedaEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar notaMoeda pelo Id: " + e.getMessage());
		} 
		return notaMoeda;
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
	public void deletar(NotaMoedaEntity notaMoeda) {

		try {

			em.getTransaction().begin();
			em.remove(notaMoeda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar NotaMoeda: " + e.getMessage());
		} 
	}

	// Deletar Colecao por ID
	public void deletar(NotaMoedaID id) {
		try {
			NotaMoedaEntity notaMoeda = pesquisarPorId(id);
			deletar(notaMoeda);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar NotaMoeda pelo id: " + e.getMessage());
		} 
	}

	// Listar coleçãoMoeda
	public List<NotaMoedaEntity> listar() {

		List<NotaMoedaEntity> notasMoeda = null;
		Query query = em.createQuery("Select n from NotaMoedaEntity n");
		try {

			notasMoeda = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar notasMoeda: " + e.getMessage());
		} 
		return notasMoeda;
	}
}
