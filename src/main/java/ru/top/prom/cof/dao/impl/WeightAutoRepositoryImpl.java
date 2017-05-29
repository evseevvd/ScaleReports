package ru.top.prom.cof.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import ru.top.prom.cof.dao.WeightRepository;
import ru.top.prom.cof.model.WeightAuto;
import ru.top.prom.cof.search.SearchCriteria;
import ru.top.prom.cof.service.dto.SearchDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 30.06.2016.
 *
 */
@Repository
public class WeightAutoRepositoryImpl implements WeightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(WeightAuto weightAuto) {
        entityManager.getTransaction().begin();
        entityManager.persist(weightAuto);
        entityManager.getTransaction().commit();
        entityManager.flush();
        entityManager.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeightAuto read(Serializable id) {
        return entityManager.find(WeightAuto.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WeightAuto> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeightAuto> weightAutoCriteriaQuery = criteriaBuilder.createQuery(WeightAuto.class);
        Root<WeightAuto> root = weightAutoCriteriaQuery.from(WeightAuto.class);
        weightAutoCriteriaQuery.select(root);
        return entityManager.createQuery(weightAutoCriteriaQuery).getResultList();
    }

    @Override
    public SearchDto findAllByCriteria(SearchCriteria criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeightAuto> weightAutoCriteriaQuery = criteriaBuilder.createQuery(WeightAuto.class);
        Root<WeightAuto> root = weightAutoCriteriaQuery.from(WeightAuto.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(criteria.getSmena())) {
            predicates.add(criteriaBuilder.equal(root.get("timeFrame"), criteria.getSmena()));
        }
        if (criteria.getStartDate() != null && criteria.getEndDate() == null) {
            predicates.add(criteriaBuilder.equal(root.<Date>get("dateGross"), criteria.getStartDate()));
        }
        if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
            predicates.add(criteriaBuilder.between(root.<Date>get("dateGross"), criteria.getStartDate(), criteria.getEndDate()));
        }
        if (!StringUtils.isEmpty(criteria.getUnloading())) {
            predicates.add(criteriaBuilder.equal(root.get("unloading"), criteria.getUnloading()));
        }
        if (!StringUtils.isEmpty(criteria.getLoading())) {
            predicates.add(criteriaBuilder.equal(root.get("loading"), criteria.getLoading()));
        }
        if (criteria.getCarNom() != null && criteria.getCarNom().size() > 0) {
            predicates.add(root.get("carNom").in(criteria.getCarNom()));
        }
        if (!StringUtils.isEmpty(criteria.getCargoCarrier())) {
            predicates.add(criteriaBuilder.equal(root.get("driver"), criteria.getCargoCarrier()));
        }
        if (!StringUtils.isEmpty(criteria.getAddressee())) {
            predicates.add(criteriaBuilder.equal(root.get("addressee"), criteria.getAddressee()));
        }
        if (!StringUtils.isEmpty(criteria.getSender())) {
            predicates.add(criteriaBuilder.equal(root.get("sender"), criteria.getSender()));
        }
        if (!StringUtils.isEmpty(criteria.getRoute())) {
            predicates.add(criteriaBuilder.equal(root.get("route"), criteria.getRoute()));
        }
        if (criteria.getCargo() != null && criteria.getCargo().size() > 0) {
            predicates.add(root.get("cargo").in(criteria.getCargo()));
        }

        weightAutoCriteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(criteriaBuilder.asc(root.get("dateGross")));

        Query resultQuery = entityManager.createQuery(weightAutoCriteriaQuery);

        SearchDto response = new SearchDto();
        response.setWeightAutos(resultQuery.getResultList());


        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchDto findByCriteria(SearchCriteria criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeightAuto> weightAutoCriteriaQuery = criteriaBuilder.createQuery(WeightAuto.class);
        Root<WeightAuto> root = weightAutoCriteriaQuery.from(WeightAuto.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(criteria.getSmena())) {
            predicates.add(criteriaBuilder.equal(root.get("timeFrame"), criteria.getSmena()));
        }
        if (criteria.getStartDate() != null && criteria.getEndDate() == null) {
            predicates.add(criteriaBuilder.equal(root.<Date>get("dateGross"), criteria.getStartDate()));
        }
        if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
            predicates.add(criteriaBuilder.between(root.<Date>get("dateGross"), criteria.getStartDate(), criteria.getEndDate()));
        }
        if (!StringUtils.isEmpty(criteria.getUnloading())) {
            predicates.add(criteriaBuilder.equal(root.get("unloading"), criteria.getUnloading()));
        }
        if (!StringUtils.isEmpty(criteria.getLoading())) {
            predicates.add(criteriaBuilder.equal(root.get("loading"), criteria.getLoading()));
        }
        if (criteria.getCarNom() != null && criteria.getCarNom().size() > 0) {
            predicates.add(root.get("carNom").in(criteria.getCarNom()));
        }
        if (!StringUtils.isEmpty(criteria.getCargoCarrier())) {
            predicates.add(criteriaBuilder.equal(root.get("driver"), criteria.getCargoCarrier()));
        }
        if (!StringUtils.isEmpty(criteria.getAddressee())) {
            predicates.add(criteriaBuilder.equal(root.get("addressee"), criteria.getAddressee()));
        }
        if (!StringUtils.isEmpty(criteria.getSender())) {
            predicates.add(criteriaBuilder.equal(root.get("sender"), criteria.getSender()));
        }
        if (!StringUtils.isEmpty(criteria.getRoute())) {
            predicates.add(criteriaBuilder.equal(root.get("route"), criteria.getRoute()));
        }
        if (criteria.getCargo() != null && criteria.getCargo().size() > 0) {
            predicates.add(root.get("cargo").in(criteria.getCargo()));
        }

        weightAutoCriteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(criteriaBuilder.asc(root.get("dateGross")));

        Query resultQuery = entityManager.createQuery(weightAutoCriteriaQuery);

        resultQuery.setFirstResult(criteria.getPosition());
        resultQuery.setMaxResults(criteria.getItemPerPage());

        float gross = 0;
        float tare = 0;
        float netto = 0;
        for (WeightAuto weightAuto :entityManager.createQuery(weightAutoCriteriaQuery).getResultList()){
            gross += weightAuto.getGross();
            tare += weightAuto.getTare();
            netto += weightAuto.getNetto();
        };
        SearchDto response = new SearchDto();

        response.setItemPerPage(criteria.getItemPerPage());
        response.setTotalResult(entityManager.createQuery(weightAutoCriteriaQuery).getResultList().size());
        response.setPosition(criteria.getPosition());
        response.setWeightAutos(resultQuery.getResultList());
        response.setTotalGross(gross);
        response.setTotalTare(tare);
        response.setTotalNetto(netto);
        return response;
    }
}
