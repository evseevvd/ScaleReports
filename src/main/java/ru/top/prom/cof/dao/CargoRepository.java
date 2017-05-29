package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Cargos;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий для вида угля
 */
public interface CargoRepository {

    /**
     * вернет все виды угля
     */
    List<Cargos> getAllCargos();
}
