package com.geekbrains.ru.springmvcdemo.repository.impl;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;
    private final Random random = new Random();
    private long curIdNumber;

    @PostConstruct
    void init(){
        products = new ArrayList<>();

        for (int i = 0; i <5 ; i++) {
            curIdNumber++;
            products.add(new Product(curIdNumber, "Product" + curIdNumber, random.nextInt()*i*1000));
        }
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(long id) {
        for (Product product: products) {
            if (product.getId() == id){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
    @Override
    public Product add (Product newProduct){
        Optional<Product> mayBeExistingProd = products.stream()
                .filter(curProduct -> curProduct.getId() == newProduct.getId())
                .findFirst();

        if(mayBeExistingProd.isPresent()){
            Product existingProd = mayBeExistingProd.get();
            existingProd.setTitle(newProduct.getTitle());
            existingProd.setCost(newProduct.getCost());
        }else {
            curIdNumber++;
            newProduct.setId(curIdNumber);
            products.add(newProduct);
        }
        return newProduct;
    }

}
