package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author hantsy
 */
@Stateless
public class TaskRepository {

    @PersistenceContext
    EntityManager em;

    public Optional<Task> findOptionalById(Long id) {
        Task task = em.find(Task.class, id);
        return Optional.ofNullable(task);
    }

    public long countByKeyword(String keyword, Task.Status status) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<Task> c = q.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            predicates.add(
                    cb.or(
                            cb.like(c.get(Task_.name), '%' + keyword + '%'),
                            cb.like(c.get(Task_.description), '%' + keyword + '%')
                    )
            );
        }
        if (status != null) {
            predicates.add(cb.equal(c.get(Task_.status), status));
        }

        q.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Long> query = em.createQuery(q.select(cb.count(c)));

        return query.getSingleResult();
    }

    public List<Task> searchByKeyword(String keyword,
                                      Task.Status status,
                                      int offset,
                                      int limit) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        CriteriaQuery<Task> q = cb.createQuery(Task.class);
        Root<Task> c = q.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            predicates.add(
                    cb.or(
                            cb.like(c.get(Task_.name), '%' + keyword + '%'),
                            cb.like(c.get(Task_.description), '%' + keyword + '%')
                    )
            );
        }
        if (status != null) {
            predicates.add(cb.equal(c.get(Task_.status), status));
        }

        q.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Task> query = em.createQuery(q).setFirstResult(offset).setMaxResults(limit);

        return query.getResultList();
    }

    public Task save(Task task) {
        em.persist(task);

        return task;
    }

    public Task update(Task task) {
        return em.merge(task);
    }

    public void delete(Task task) {
        task = em.merge(task);
        em.remove(task);
    }


}
