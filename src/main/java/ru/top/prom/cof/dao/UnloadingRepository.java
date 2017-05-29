package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Unloadings;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий место разгрузки
 */
public interface UnloadingRepository {

    List<Unloadings> getAllUnloadings();
}
