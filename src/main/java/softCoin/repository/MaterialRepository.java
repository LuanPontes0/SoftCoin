package softCoin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import softCoin.entity.MaterialEntity;

public class MaterialRepository {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("coin-db");
	EntityManager em = emf.createEntityManager();

	// Inserir Material
	public void inserir(MaterialEntity material) {
		try {

			em.getTransaction().begin();
			em.persist(material);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao inserir cadastro Material: " + e.getMessage());
		}
	}

	// Atualizar Material
	public boolean atualizar(MaterialEntity material) {
		boolean erro = true;
		try {
			
			em.getTransaction().begin();
			em.merge(material);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao atualizar material: " + e.getMessage());
			erro = false;
		}
		return erro;
	}

	// Pesquisa Material pelo ID
	public MaterialEntity pesquisarPorId(int id) {

		MaterialEntity material = null;

		try {

			material = em.find(MaterialEntity.class, id);

		} catch (Exception e) {

			System.out.println("Erro ao pesquisar Material pelo Id: " + e.getMessage());
		}
		return material;
	}

	// Pesquisa Material pelo Nome
	public MaterialEntity pesquisarPeloNome(String nome) {

		MaterialEntity material = null;

		try {
			String jpql = "select m from MaterialEntity m where m.nomeMaterial = :nome";

			TypedQuery<MaterialEntity> tq = em.createQuery(jpql, MaterialEntity.class).setParameter("nome", nome);

			List<MaterialEntity> materiais = tq.getResultList();

			for (MaterialEntity material1 : materiais) {

				return material1;

			}
		} catch (Exception e) {

			System.out.println(e + "Erro ao buscar Material pelo nome");
		}
		return material;
	}

	// Deletar Material por objeto
	public void deletar(MaterialEntity material) {

		try {

			em.getTransaction().begin();
			em.remove(material);
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Material: " + e.getMessage());
		}
	}

	// Deletar Material por ID
	public void deletar(int id) {
		try {
			MaterialEntity material = pesquisarPorId(id);
			deletar(material);

		} catch (Exception e) {

			em.getTransaction().rollback();
			System.out.println("Erro ao deletar Material: " + e.getMessage());
		}
	}

	// Listar Material
	public List<MaterialEntity> listar() {

		List<MaterialEntity> materiais = null;
		Query query = em.createQuery("Select m from MaterialEntity m order by m.id");
		try {

			materiais = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao listar Materiais: " + e.getMessage());
		}
		return materiais;
	}
}
