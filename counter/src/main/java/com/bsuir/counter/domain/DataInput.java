package com.bsuir.counter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;



@Getter
@Setter
@AllArgsConstructor
public class DataInput {
    @Length(min = 1, max = 30, message = " 1 - 30 characters")
    private String str;
    private String symbol;
}