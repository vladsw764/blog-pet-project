package com.isarev.blog.blogpetproject.controllers;

import com.isarev.blog.blogpetproject.models.Product;
import com.isarev.blog.blogpetproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ProductRepository productRepository;
    @GetMapping("")
    public String shopMain(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "shop/shop-main";
    }

    @GetMapping("/add_products")
    public String shopAdd(){
        return "shop/shop-add";
    }

    @PostMapping("/add_products")
    public String productAdd(@RequestParam String product_name, @RequestParam String supplier,
                             @RequestParam BigDecimal unit_price, @RequestParam String description){
        Product product = new Product(product_name, supplier, description, unit_price);
        productRepository.save(product);
        return "redirect:/shop";
    }

    @GetMapping("/{id}")
    public String moreInfo(@PathVariable(name = "id") long id, Model model){
        if (!productRepository.existsById(id)) return "redirect:/shop";

        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);

        model.addAttribute("products", res);
        return "shop/product-details";
    }

    @DeleteMapping("/{id}/delete")
    public String productRemove(@PathVariable(name = "id") long id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/shop";
    }

}
