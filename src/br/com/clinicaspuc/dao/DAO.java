package br.com.clinicaspuc.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public class DAO<T, I extends Serializable> {
	
	private static final String INSTRUCAO_PADRAO = "select o from ";

	protected EntityManager em;

	@PersistenceContext(unitName = "clinicasPU")
	protected void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public T save(T entidade) {
		entidade = em.merge(entidade);
		return entidade;
	}

	public List<T> saveList(List<T> lista) {
		for (T entidade : lista) {
			em.merge(entidade);
		}
		return lista;
	}

	public void remove(T entidade) {
		if (!em.contains(entidade)) {
			entidade = em.merge(entidade);
		}
		
		em.remove(entidade);
	}

	public void removeList(List<T> lista) {
		for (T entidade : lista) {
			em.remove(entidade);
		}
	}

	public T findById(I chavePrimaria) throws NoResultException {
		try {
			return em.find(this.getClasse(), chavePrimaria);
		} catch (NoResultException e) {
			throw new NoResultException(
					"Não foi possivel localizar o objeto com identificador " + chavePrimaria);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery(INSTRUCAO_PADRAO.concat(this.getClasse().getSimpleName().concat(" o"))).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllOrderByParameter(String fieldName) {
		return em
				.createQuery(INSTRUCAO_PADRAO
						.concat(this.getClasse().getSimpleName().concat(" o order by o.".concat(fieldName))))
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllDescOrderByParameter(String fieldName) {
		return em
				.createQuery(INSTRUCAO_PADRAO.concat(
						this.getClasse().getSimpleName().concat(" o order by o.".concat(fieldName.concat(" desc")))))
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findOneByNamedQuery(String namedQuery) {
		Query query = em.createNamedQuery(namedQuery);
		List<T> lista = query.getResultList();
		if (lista != null && !lista.isEmpty())
			return lista.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public T findOneByNamedQueryAndParams(String namedQuery, Map<String, Object> mapaDeParametros) {
		Query query = em.createNamedQuery(namedQuery);
		for (Map.Entry<String, Object> param : mapaDeParametros.entrySet()) {
			query.setParameter((String) param.getKey(), param.getValue());
		}
		List<T> lista = query.getResultList();
		if (lista != null && !lista.isEmpty())
			return lista.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndParams(String namedQuery, Map<String, Object> mapaDeParametros) {
		Query query = em.createNamedQuery(namedQuery);
		for (Map.Entry<String, Object> param : mapaDeParametros.entrySet()) {
			query.setParameter((String) param.getKey(), param.getValue());
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndParamsLazyModel(String namedQuery, Map<String, Object> mapaDeParametros,
			int primeiro, int tamanhoPagina) {
		Query query = em.createNamedQuery(namedQuery);
		for (Map.Entry<String, Object> param : mapaDeParametros.entrySet()) {
			query.setParameter((String) param.getKey(), param.getValue());
		}
		return query.setFirstResult(primeiro).setMaxResults(tamanhoPagina).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String namedQuery) {
		Query query = em.createNamedQuery(namedQuery);
		return query.getResultList();
	}

	public Integer executeUpdate(String namedQuery, Map<String, Object> mapaDeParametros) {
		Query query = em.createNamedQuery(namedQuery);
		for (Map.Entry<String, Object> param : mapaDeParametros.entrySet()) {
			query.setParameter((String) param.getKey(), param.getValue());
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public Class<T> getClasse() {
		return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T fetchLazyList(String namedGraph, Serializable codigo) {
		EntityGraph<?> grafo = this.em.getEntityGraph(namedGraph);

		Map mapa = new HashMap();
		mapa.put("javax.persistence.fetchgraph", grafo);

		return (T) this.em.find(this.getClasse(), codigo, mapa);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndParamsLazy(String namedQuery, Map<String, Object> mapaDeParametros,
			String namedGraph) {
		Query query = em.createNamedQuery(namedQuery).setHint("javax.persistence.fetchgraph",
				em.getEntityGraph(namedGraph));

		for (Map.Entry<String, Object> param : mapaDeParametros.entrySet()) {
			query.setParameter((String) param.getKey(), param.getValue());
		}

		return query.getResultList();
	}

}
