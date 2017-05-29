package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.CargoRepository;
import ru.top.prom.cof.model.Cargos;

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
public class CargoRepositoryImpl implements CargoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cargos> getAllCargos() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cargos> criteriaQuery = criteriaBuilder.createQuery(Cargos.class);
        Root<Cargos> root = criteriaQuery.from(Cargos.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
