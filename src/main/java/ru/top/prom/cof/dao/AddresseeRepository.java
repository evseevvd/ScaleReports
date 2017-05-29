package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Addressees;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий для грузополучателя
 */
public interface AddresseeRepository {

    /**
     * @return вернет список всех грузополучателей
     */
    List<Addressees> getAllAddresses();
}
