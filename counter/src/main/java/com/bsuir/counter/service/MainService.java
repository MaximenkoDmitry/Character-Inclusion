package com.bsuir.counter.service;

import com.bsuir.counter.cache.ResultCache;
import com.bsuir.counter.domain.DataInput;
import com.bsuir.counter.domain.Response;
import com.bsuir.counter.exceptions.ServiceException;
import com.bsuir.counter.exceptions.ValidateException;
import com.bsuir.counter.models.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MainService {
    private final ResultCache resultCache;

    public MainService(@Autowired ResultCache resultCache) {
        this.resultCache = resultCache;
    }

    private void validateDataInput(DataInput dataInput) throws ValidateException {
        if (dataInput.getStr() == null || dataInput.getStr().equals("")) {
            log.error("Line is empty!");
            throw new ValidateException("Line is empty!");
        }

        if (dataInput.getSymbol() == null || dataInput.getSymbol().equals("")) {
            log.error("Symbol is empty!");
            throw new ValidateException("Symbol is empty!");
        }
    }

    private void validateLine(String line) throws ServiceException {
        if (line.equals("con")) {
            log.error("Line is 'con'!");
            throw new ServiceException("Error input!");
        }
    }

    public long countEntries(DataInput dataInput) {
        validateDataInput(dataInput);
        validateLine(dataInput.getStr());
        log.debug("Input data is validate.");
        long count;
        Optional<Long> resultOptional = resultCache.getResult(dataInput);
        if (resultOptional.isPresent()) {
            log.debug("Result from cache.");
            count = resultOptional.get();
        } else {
            count = org.springframework.util.StringUtils.countOccurrencesOf(dataInput.getStr(), dataInput.getSymbol());
            Post post = new Post(dataInput.getStr(),dataInput.getSymbol(),count);
            resultCache.getPostRepository().save(post);
            resultCache.putResult(dataInput, count);
            log.debug("Result push cache");
        }

        return count;
    }

    public Response countEntriesList(List<DataInput> dataInputs) {
        dataInputs.forEach(this::validateDataInput);
        dataInputs.forEach(dataInput -> validateLine(dataInput.getStr()));
        List<Long> resultList = dataInputs.stream().map(dataInput -> {
                    final Optional<Long> resultOptional = resultCache.getResult(dataInput);
                    return resultOptional.orElseGet(() -> {
                        long result = StringUtils.countOccurrencesOf(dataInput.getStr(), dataInput.getSymbol());
                        resultCache.putResult(dataInput, result);
                        Post post = new Post(dataInput.getStr(),dataInput.getSymbol(),result);
                        resultCache.getPostRepository().save(post);
                        log.debug("Result push cache");
                        return result;
                    });
                }
        ).collect(Collectors.toList());
        LongSummaryStatistics stats = resultList.stream().mapToLong(Long::longValue).summaryStatistics();
        return new Response(resultList, stats.getMax(), stats.getMin(), stats.getAverage());
    }

}
