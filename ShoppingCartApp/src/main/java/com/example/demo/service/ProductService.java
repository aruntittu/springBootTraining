package com.example.demo.service;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService implements ProductDao {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateById(int id, Product product) {
        if(productRepository.findById(id).isPresent()) {
            Product exitingProduct = productRepository.findById(id).get();
            exitingProduct.setId(id);
            exitingProduct.setName(product.getName());
            exitingProduct.setPrice(product.getPrice());
            exitingProduct.setDescription(product.getDescription());
            exitingProduct.setImageLocation(product.getImageLocation());
            productRepository.save(exitingProduct);
        }
    }

    @Override
    @Transactional
    public HttpStatus deleteProductById(int id) {
        if(productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @Override
    @Transactional
    public Product get(Integer id) {
        if(productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

}
