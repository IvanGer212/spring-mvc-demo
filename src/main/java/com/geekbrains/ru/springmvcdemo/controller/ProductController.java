package com.geekbrains.ru.springmvcdemo.controller;


import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.exception.ErrorResponse;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<ErrorResponse> validationError = productValidation(newProduct);
        if(validationError.isPresent()){
            model.addAttribute("error", validationError.get());
            return "exception-page";
        }
            productService.create(newProduct);
        return "redirect:/product";
    }

    private Optional<ErrorResponse> productValidation(Product product) {
        List<String> details = new ArrayList<>();

        if (product.getTitle().isEmpty()) {
            details.add("Product name cannot be empty.");
        }
        if (product.getCost() <= 0) {
            details.add("Price cannot be less then 0.");
        }
        if(!details.isEmpty()){
            return Optional.of(new ErrorResponse("Validation failed!", details));
        }

        return Optional.empty();
    }
}
