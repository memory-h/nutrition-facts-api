package com.github.memoryh.nutritionfacts.api.service;

import com.github.memoryh.nutritionfacts.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeCheckImpl implements ProductTypeCheck {

    private final ProductRepository repository;

    @Override
    public boolean checkProductType(String productName) {
        return repository.isProductWater(productName);
    }

}