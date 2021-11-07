package com.bsuir.counter.service;

import com.bsuir.counter.cache.ResultCache;
import com.bsuir.counter.counter.ServiceCallCounter;
import com.bsuir.counter.domain.DataInput;
import com.bsuir.counter.domain.Response;
import com.bsuir.counter.exceptions.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MainServiceTest {
    private static MainService mainService;

    @BeforeAll
    static void beforeAll() {
        mainService = new MainService(new ResultCache());
    }

    @Test
    public void find_GivenHelloAndL_ShouldReturn2() {

        DataInput dataInput = new DataInput("Hello", "l");
        Assertions.assertEquals(mainService.countEntries(dataInput), 2);
    }

    @Test
    public void find_GivenAaaaaaAndA_ShouldReturn5() {

        DataInput dataInput = new DataInput("Aaaaaa", "a");
        Assertions.assertEquals(mainService.countEntries(dataInput), 5);
    }

    @Test
    public void find_GivenProgramAndV_ShouldReturn0() {

        DataInput dataInput = new DataInput("Program", "V");
        Assertions.assertEquals(mainService.countEntries(dataInput), 0);
    }

    @Test
    public void find_GivenCon_ShouldThrowServiceException() {
        DataInput dataInput = new DataInput("con", "c");
        Assertions.assertThrows(ServiceException.class, () -> {
            mainService.countEntries(dataInput);
        });
    }

    @Test
    public void countEntriesList() {
        DataInput dataInput1 = new DataInput("com", "c");
        DataInput dataInput2 = new DataInput("dimaa", "a");
        DataInput dataInput3 = new DataInput("diiim", "i");
        DataInput dataInput4 = new DataInput("test", "e");
        List<DataInput> givenDataInputs = List.of(dataInput1, dataInput2, dataInput3, dataInput4);
        List<Long> expectedResults = List.of(1L, 2L, 3L, 1L);

        Response response = mainService.countEntriesList(givenDataInputs);

        Assertions.assertEquals(expectedResults, response.getResults());
    }
}
