package ru.top.prom.cof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.top.prom.cof.dao.*;
import ru.top.prom.cof.model.*;
import ru.top.prom.cof.search.SearchCriteria;
import ru.top.prom.cof.service.Service;
import ru.top.prom.cof.service.dto.SearchDto;

import java.util.List;

/**
 * Created by Владимир on 01.07.2016.
 */
@org.springframework.stereotype.Service
public class WeightService implements Service {

    @Autowired
    private WeightRepository weightAutoWeightRepository;

    @Autowired
    private AddresseeRepository addresseeRepository;

    @Autowired
    private CargoCarriersRepository cargoCarriersRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private LoadingRepository loadingRepository;

    @Autowired
    private SenderRepository senderRepository;

    @Autowired
    private TimeframesRepository timeframesRepository;

    @Autowired
    private UnloadingRepository unloadingRepository;

    @Override
    public SearchDto findWeightAuto(SearchCriteria criteria) {
        return weightAutoWeightRepository.findByCriteria(criteria);
    }

    @Override
    public SearchDto findWeightAutoAll(SearchCriteria criteria) {
        return weightAutoWeightRepository.findAllByCriteria(criteria);
    }

    @Override
    public List<WeightAuto> findAllWeightAuto() {
        return weightAutoWeightRepository.findAll();
    }

    @Override
    public List<Cars> getCars() {
       return carsRepository.getAllCars();
    }

    @Override
    public List<Cars> findCars(String carNum) {
        return carsRepository.findCars(carNum);
    }

    @Override
    public List<Cargos> getCargos() {
        return cargoRepository.getAllCargos();
    }

    @Override
    public List<Loadings> getLoadings() {
        return loadingRepository.getAllLoadings();
    }

    @Override
    public List<Unloadings> getUnloadings() {
        return unloadingRepository.getAllUnloadings();
    }

    @Override
    public List<CargoCarriers> getCargoCarriers() {
        return cargoCarriersRepository.getAllCargoCarriers();
    }

    @Override
    public List<Addressees> getAddressees() {
        return addresseeRepository.getAllAddresses();
    }

    @Override
    public List<Senders> getSenders() {
        return senderRepository.getAllSenders();
    }

    @Override
    public List<Timeframes> getTimeFrames() {
        return timeframesRepository.getAllTimeframes();
    }
}
