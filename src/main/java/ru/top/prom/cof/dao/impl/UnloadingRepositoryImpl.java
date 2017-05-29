package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.UnloadingRepository;
import ru.top.prom.cof.model.Unloadings;

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
public class UnloadingRepositoryImpl implements UnloadingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Unloadings> getAllUnloadings() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unloadings> criteriaQuery = criteriaBuilder.createQuery(Unloadings.class);
        Root<Unloadings> root = criteriaQuery.from(Unloadings.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
