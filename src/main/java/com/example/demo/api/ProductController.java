package com.example.demo.api;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RequestMapping("api/v1/product")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
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

    @ExceptionHandler(EntityExistsException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleEntityExistsException(EntityExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }
}
