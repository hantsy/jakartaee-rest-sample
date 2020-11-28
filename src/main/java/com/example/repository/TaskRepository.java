package com.example.repository;

import com.example.common.persistence.AbstractRepository;
import com.example.domain.Task;
import com.example.domain.Task_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hantsy
 */
@Stateless
public class TaskRepository extends AbstractRepository<Task, Long> {

    @PersistenceContext
    EntityManager em;

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

    public List<Task> findByCreatedBy(String name) {
        Objects.requireNonNull(name, "username can not be null");
        return this.stream().filter(t -> name.equals(t.getCreatedBy().getUsername())).collect(Collectors.toList());
    }

    @Override
    protected EntityManager entityManager() {
        return this.em;
    }

}
