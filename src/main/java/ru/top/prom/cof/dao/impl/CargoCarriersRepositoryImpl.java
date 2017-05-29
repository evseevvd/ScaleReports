package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.CargoCarriersRepository;
import ru.top.prom.cof.model.CargoCarriers;

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
public class CargoCarriersRepositoryImpl implements CargoCarriersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CargoCarriers> getAllCargoCarriers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CargoCarriers> criteriaQuery = criteriaBuilder.createQuery(CargoCarriers.class);
        Root<CargoCarriers> root = criteriaQuery.from(CargoCarriers.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
