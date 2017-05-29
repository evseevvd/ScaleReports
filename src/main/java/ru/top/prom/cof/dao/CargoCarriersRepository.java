package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.CargoCarriers;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий для грузоперевозчика
 */
public interface CargoCarriersRepository {

    /**
     * Вернет всех грузоперевозчиков
     */
    List<CargoCarriers> getAllCargoCarriers();
}
