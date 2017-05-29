package ru.top.prom.cof.dao;

import ru.top.prom.cof.model.Senders;

import java.util.List;

/**
 * Created by Владимир on 18.07.2016.
 * <p/>
 * Репозиторий для грузоотправителя
 */
public interface SenderRepository {

    /**
     * Вернет всех грузоотправителей
     */
    List<Senders> getAllSenders();
}
