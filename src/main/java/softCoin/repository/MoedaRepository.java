package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.MoedaEntity;

public class MoedaRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir Moeda
	public void inserir(MoedaEntity moeda) {
		try {

			em.getTransaction().begin();
			em.persist(moeda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir cadastro Moeda: " + e.getMessage());
		} 
	}

	// Atualizar Moeda
	public void atualizar(MoedaEntity moeda) {
		try {

			em.getTransaction().begin();
			em.merge(moeda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar Moeda: " + e.getMessage());

		} 
	}

	// Pesquisa Moeda pelo ID
	public MoedaEntity pesquisarPorId(Long id) {

		MoedaEntity moeda = null;

		try {

			moeda = em.find(MoedaEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar Moeda pelo Id: " + e.getMessage());
		} 
		return moeda;
	}

	// Pesquisa Moeda pelo titulo
	public MoedaEntity pesquisarPeloTitulo(String titulo) {

		MoedaEntity moeda = null;

		try {
			String jpql = "select m from MoedaEntity m where m.titulo = :titulo";

			TypedQuery<MoedaEntity> tq = em.createQuery(jpql, MoedaEntity.class).setParameter("titulo", titulo);

			List<MoedaEntity> titulos = tq.getResultList();

			for (MoedaEntity titulo1 : titulos) {

				return titulo1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar Moeda pelo titulo");
		} 
		return moeda;
	}

	// Deletar Moeda por objeto
	public void deletar(MoedaEntity moeda) {

		try {

			em.getTransaction().begin();
			em.remove(moeda);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar moeda por objeto: " + e.getMessage());
		} 
	}

	// Deletar Moeda por ID
	public void deletar(long id) {
		try {
			MoedaEntity moeda = pesquisarPorId(id);
			deletar(moeda);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Moeda: " + e.getMessage());
		} 
	}

	// Listar Moedas
	public List<MoedaEntity> listar() {

		List<MoedaEntity> moedas = null;
		Query query = em.createQuery("Select m from MoedaEntity m");
		try {

			moedas = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar Moedas: " + e.getMessage());
		}
		return moedas;
	}
}
