package com.github.memoryh.nutritionfacts.api.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Products<T> {

    private List<?> products;

}