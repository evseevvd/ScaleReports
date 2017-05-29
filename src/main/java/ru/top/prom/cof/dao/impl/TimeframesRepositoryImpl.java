package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.TimeframesRepository;
import ru.top.prom.cof.model.Timeframes;

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
public class TimeframesRepositoryImpl implements TimeframesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Timeframes> getAllTimeframes() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Timeframes> criteriaQuery = criteriaBuilder.createQuery(Timeframes.class);
        Root<Timeframes> root = criteriaQuery.from(Timeframes.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
