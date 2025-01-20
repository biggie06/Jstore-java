package com.Mytools.JStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.sort;
import org.springframework.stereotype.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMappinf;
import org.springframework.web.bind.annotation.RequestMapping;


import com.Mytools.JStore.models.products;
import com.Mytools.JStore.services.productsRepository;


@controller
@RequestMapping("/products")
public class productController {


    @Autowired
    private ProductsRepository repo;



    @GetMapping(("","/";))

    public string showProductList(Model model){
        List<Product> products = repo.findAll(SortableTableModel.by(SortableTableModel.Direction.DESC, "id"));
        model.addAttribute("products",products);
        return "products/index";
    }


    @GetMapping("/create")
    public string showCreatepage(Model model){
        ProductDto productDto = new productDto();
        model.addAttribute("productDto", productDto);
        return "products/createProduct";
    }





}
