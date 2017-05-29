package ru.top.prom.cof.dao.impl;

import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.AddresseeRepository;
import ru.top.prom.cof.model.Addressees;

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
public class AddresseesRepositoryImpl implements AddresseeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Addressees> getAllAddresses() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Addressees> addresseesCriteriaQuery = criteriaBuilder.createQuery(Addressees.class);
        Root<Addressees> root = addresseesCriteriaQuery.from(Addressees.class);
        addresseesCriteriaQuery.select(root);
        return entityManager.createQuery(addresseesCriteriaQuery).getResultList();
    }
}
