package com.bsuir.counter.domain;

import java.util.List;

public class Response {
    private final List<Long> results;
    private final Long max;
    private final Long min;
    private final Double avg;

    public Response(List<Long> results, Long max, Long min, Double avg) {
        this.results = results;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public List<Long> getResults() {
        return results;
    }

    public Long getMax() {
        return max;
    }

    public Long getMin() {
        return min;
    }

    public Double getAvg() {
        return avg;
    }
}
