package ru.top.prom.cof.service;

import ru.top.prom.cof.model.*;
import ru.top.prom.cof.search.SearchCriteria;
import ru.top.prom.cof.service.dto.SearchDto;

import java.util.List;

/**
 * Created by Владимир on 01.07.2016.
 */
public interface Service {

    SearchDto findWeightAuto(SearchCriteria criteria);

    SearchDto findWeightAutoAll(SearchCriteria criteria);

    List<WeightAuto> findAllWeightAuto();

    List<Cars> getCars();

    List<Cars> findCars(String carNum);

    List<Cargos> getCargos();

    List<Loadings> getLoadings();

    List<Unloadings> getUnloadings();

    List<CargoCarriers> getCargoCarriers();

    List<Addressees> getAddressees();

    List<Senders> getSenders();

    List<Timeframes> getTimeFrames();
}
