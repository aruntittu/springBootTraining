package com.example.demo.model.projections;

import com.example.demo.model.Product;

public interface OrderDetailsView {

    Product getProduct();
    long getQuantity();
}
