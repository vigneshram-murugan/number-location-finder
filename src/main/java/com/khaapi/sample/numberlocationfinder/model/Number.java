package com.khaapi.sample.numberlocationfinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@NoArgsConstructor
public class Number {
    private String phoneNumber;
    private String locality;
    private String postalCode;
}
