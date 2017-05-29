package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.CarsRepository;
import ru.top.prom.cof.model.Cars;

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
public class CarsRepositoryImpl implements CarsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cars> getAllCars() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cars> criteriaQuery = criteriaBuilder.createQuery(Cars.class);
        Root<Cars> root = criteriaQuery.from(Cars.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Cars> findCars(String carNum) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cars> query = criteriaBuilder.createQuery(Cars.class);
        Root<Cars> root = query.from(Cars.class);
        query.select(root).where(criteriaBuilder.like(root.<String>get("name"), toRegex(carNum)));
        return entityManager.createQuery(query).getResultList();
    }

    private String toRegex(String string) {
        return "%"+string+"%";
    }
}
