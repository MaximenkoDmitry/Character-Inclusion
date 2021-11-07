package com.bsuir.counter.domain;

import javax.validation.Valid;
import java.util.List;

public class Request {

    @Valid
    private List<DataInput> dataInputs;

    public List<DataInput> getDataInputs() {
        return dataInputs;
    }
}
