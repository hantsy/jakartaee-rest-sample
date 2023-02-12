package com.example.infrastructure.persistence.jpa;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;
import com.example.domain.task.Task_;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hantsy
 */
@Stateless
public class JpaTaskRepository extends AbstractRepository<Task, Long> implements TaskRepository {

    @PersistenceContext
    EntityManager em;

    @Override
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

    @Override
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

    @Override
    public List<Task> findByCreatedBy(String name) {
        Objects.requireNonNull(name, "username can not be null");
        return this.stream().filter(t -> name.equals(t.getCreatedBy().getUsername())).collect(Collectors.toList());
    }

    @Override
    protected EntityManager entityManager() {
        return this.em;
    }

}
