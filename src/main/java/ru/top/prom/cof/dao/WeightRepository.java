package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.WeightAuto;
import ru.top.prom.cof.search.SearchCriteria;
import ru.top.prom.cof.service.dto.SearchDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Владимир on 30.06.2016.
 *<br>
 * Репозиторий для Провесок грузовиков
 */
public interface WeightRepository {

    /**
     * Создание сущности
     * @param weightAuto
     * @return
     */
    void create(WeightAuto weightAuto);

    /**
     * Зачитать из БД сущность
     * @param id
     * @return
     */
    WeightAuto read(Serializable id);

    /**
     * Поиск всех сущностей
     * @return
     */
    List<WeightAuto> findAll();

    /**
     * Поиск сущности по критерию
     * @return
     */
    SearchDto findByCriteria(SearchCriteria criteria);

    SearchDto findAllByCriteria(SearchCriteria criteria);
}
