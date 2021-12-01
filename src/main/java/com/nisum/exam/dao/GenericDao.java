package com.nisum.exam.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

public abstract class GenericDao<Entity> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Session getCurrentSession() { 
		return entityManager.unwrap(Session.class);
	}
	
	public Entity saveEntity(Entity entity) {
		getCurrentSession().save(entity);
		getCurrentSession().flush();
		getCurrentSession().refresh(entity);
		return entity;
	}
	
	public Optional<Entity> findOneEntity(String attributeId, String value, Class<Entity> clazz) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Entity> cq = cb.createQuery(clazz);
        Root<Entity> entityRoot = cq.from(clazz);
        cq.where(cb.equal(entityRoot.get(attributeId), value));
        return Optional.ofNullable(getCurrentSession().createQuery(cq).uniqueResult());
    }
	
	public List<Entity> findAllEntites(Class<Entity> clazz) {
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Entity> cq = cb.createQuery(clazz);
        cq.from(clazz);
        List<Entity> resultList = getCurrentSession().createQuery(cq).getResultList();
        return resultList;
	}    
}
