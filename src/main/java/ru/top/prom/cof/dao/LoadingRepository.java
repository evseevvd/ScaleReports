package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Loadings;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий место погрузки
 */
public interface LoadingRepository {

    List<Loadings> getAllLoadings();
}
