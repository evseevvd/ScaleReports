package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Cars;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий грузовиков
 */
public interface CarsRepository {

    /**
     * вернет все грузовики
     */
    List<Cars> getAllCars();


    /**
     * Поиск грузовика
     * @param CarNum - гос номер
     * @return Вернет грузовики {@link Cars}, соответствующие гос номеру
     */
    List<Cars> findCars(String CarNum);
}
