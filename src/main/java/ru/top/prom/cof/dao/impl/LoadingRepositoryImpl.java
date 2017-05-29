package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.LoadingRepository;
import ru.top.prom.cof.model.Loadings;

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
public class LoadingRepositoryImpl implements LoadingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Loadings> getAllLoadings() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loadings> criteriaQuery = criteriaBuilder.createQuery(Loadings.class);
        Root<Loadings> root = criteriaQuery.from(Loadings.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
