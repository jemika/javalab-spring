package com.epam.jl.demo.ioc;
import lombok.Value;

@Value
public class SimpleContact implements Contact {
    private String type;
    private String value;
}
