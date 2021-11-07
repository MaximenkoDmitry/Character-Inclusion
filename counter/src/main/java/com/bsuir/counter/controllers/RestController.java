package com.bsuir.counter.controllers;

import com.bsuir.counter.counter.ServiceCallCounter;
import com.bsuir.counter.domain.DataInput;
import com.bsuir.counter.domain.Request;
import com.bsuir.counter.domain.Response;
import com.bsuir.counter.service.MainService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

@Log4j2
@org.springframework.web.bind.annotation.RestController
@Validated
public class RestController {
    private MainService mainService;
    private ServiceCallCounter serviceCallCounter;


    public RestController(@Autowired MainService mainService, @Autowired ServiceCallCounter serviceCallCounter) {

        this.mainService = mainService;
        this.serviceCallCounter = serviceCallCounter;
    }

    @GetMapping("/rest")
    public Map<String, Object> calculate(@Valid final DataInput dataInput) {
        serviceCallCounter.increment();
        Object result;
        result = mainService.countEntries(dataInput);
        log.debug("Controller get count.");
        return Map.of("result", result);
    }

    @PostMapping("/rest")
    public Map<String, Object> calculateBulk(@Valid @RequestBody final Request request) {
        serviceCallCounter.increment();
        Response result;
        result = mainService.countEntriesList(request.getDataInputs());
        log.debug("Controller get count.");
        return Map.of("result", result);
    }

    @GetMapping("/counter")
    public Map<String, Object> count() {
        Object result;
        result = serviceCallCounter.getCount();
        return Map.of("result", result);
    }
}
