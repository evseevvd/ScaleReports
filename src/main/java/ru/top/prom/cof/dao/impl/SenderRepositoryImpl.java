package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.SenderRepository;
import ru.top.prom.cof.model.Senders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 */
@Repository
public class SenderRepositoryImpl implements SenderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Senders> getAllSenders() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Senders> criteriaQuery = criteriaBuilder.createQuery(Senders.class);
        Root<Senders> root = criteriaQuery.from(Senders.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
