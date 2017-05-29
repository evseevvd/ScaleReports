package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Timeframes;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 */
public interface TimeframesRepository {

    List<Timeframes> getAllTimeframes();
}
