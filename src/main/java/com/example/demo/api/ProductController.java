package com.example.demo.api;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;


@RequestMapping("api/v1/product")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public void save(@RequestBody Product product) {
        productService.save(product);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path ="{id}")
    public Product get(@PathVariable(value = "id") int id) {
        return productService.get(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable int id, @RequestBody Product product) {
        productService.updateById(id, product);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return "Invalid Request, Product ID should be a number";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return "Product with that ID doesn't exist";
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public String handleTransactionSystemException(TransactionSystemException e) {
        Throwable cause = ((TransactionSystemException) e).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            return "Validation failed for Product properties. Name and price must not be null";
        } else {
            return e.getLocalizedMessage();
        }
    }

}
