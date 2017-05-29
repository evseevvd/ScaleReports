package ru.top.prom.cof.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.top.prom.cof.model.*;
import ru.top.prom.cof.search.SearchCriteria;
import ru.top.prom.cof.service.dto.SearchDto;
import ru.top.prom.cof.service.impl.WeightService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Владимир on 01.07.2016.
 */
@Controller
public class MainController {

    @Autowired
    private WeightService weightService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Locale locale, Model model) {
        return "main";
    }

    @RequestMapping(value = "/api/echo", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    String echo() {
        return "echo";
    }

    @RequestMapping(value = "/api/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<WeightAuto> getAll() {
        return weightService.findAllWeightAuto();
    }

    @RequestMapping(value = "/api/search", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    SearchDto search(@RequestBody @Valid SearchCriteria criteria) {
        return weightService.findWeightAuto(criteria);
    }

    @RequestMapping(value = "/api/search/all", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    SearchDto searchAll(@RequestBody @Valid SearchCriteria criteria) {
        return weightService.findWeightAutoAll(criteria);
    }

    @RequestMapping(value = "/api/carnom", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Cars> getAllCarNom() {
        return weightService.getCars();
    }

    @RequestMapping(value = "/api/find/car/{carNum}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Cars> findCarNom(@PathVariable String carNum) {
        return weightService.findCars(carNum);
    }

    @RequestMapping(value = "/api/loading", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Loadings> getAllLoading() {
        return weightService.getLoadings();
    }

    @RequestMapping(value = "/api/unloading", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Unloadings> getAllUnloading() {
        return weightService.getUnloadings();
    }

    @RequestMapping(value = "/api/cargo", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Cargos> getAllCargo() {
        return weightService.getCargos();
    }

    @RequestMapping(value = "/api/cargocarrier", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<CargoCarriers> getAllCargoCarrier() {
        return weightService.getCargoCarriers();
    }

    @RequestMapping(value = "/api/addressee", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Addressees> getAllAddressee() {
        return weightService.getAddressees();
    }

    @RequestMapping(value = "/api/sender", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Senders> getAllSender() {
        return weightService.getSenders();
    }

    @RequestMapping(value = "/api/smena", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Timeframes> getAllTimeFrames() {
        return weightService.getTimeFrames();
    }
}
