package com.geekbrains.ru.springmvcdemo.controller;


import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model){
        List<Product> products = productService.getAll();

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/create-product")
    public String createAddProductPage(Model model){
        model.addAttribute("newProduct", new Product());

        return "create-product";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping
    public String addProduct(@ModelAttribute("newProduct") Product newProduct, Model model) {
            productService.create(newProduct);
        return "redirect:/product";
    }

}
