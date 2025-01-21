package com.Mytools.JStore.controller;


import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Date;

import jakarta.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.sort;
import org.springframework.validation.FieldError;
import org.springframework.stereotype.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.Multipart.multipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.postMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;


import com.Mytools.JStore.models.products;
import com.Mytools.JStore.models.ProductDto;
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



    @postMapping("/create")
    public String createProduct(
        @Vaild @ModelAttribute ProductDto productDto,
        BindingResult result
    ){
        if (productDto.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto", "imageFile", "Image file required"));
        }
        if (result.hasErrors()){
            return "products/CreateProduct";
        }
        MultipartFile image = productDto.getImageFile();
        Date createAt = new Date();
        string storageFileName = createAt.getTime +"_"+ image.getoriginalFilename();
        try {
            String uploadDir = "public/image/";
            path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputstream = image.get.InputStream()){
                Files.copy(inputstream, Paths.get(uploadDir + storageFileName),
                StandardCopyOption.REPLACE_EXISTING);
            }
            
        } catch (Exception ex) {
            System.out.println("Exception:  "+ ex.getMessage());
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createAt);
        product.setImageFileName(storageFileName);

        repo.save(product);






        return "redirect:/products";
    }


    @GetMapping("/edit")
    public String showEditPage(
        Model model,
        @RequestParam int id 
    ) {

        try{
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);


            ProductDto productDto = new productDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto" , productDto);

        }
        catch(Exception ex){
            System.out.println("Exception:" + ex.getMessage());
            return "redirect:/products";
        }


        return "products/EditProduct";
    }


    @PostMapping("/edit")
    public String updateProduct(
        Model model,
        @RequestParam int id,
        @Valid @ModelAttribute ProductDto productDto,
        BindingResult result
    ){

try {
    Product product = repo.findById(id).get();
    model.addAttribute("product",product);
     
    if (result.hasErrors()){
        return "products/EditProduct";
    }

    if (!productDto.getImageFile().isEmpty()){
        string uploadDir= "public/image/";
        path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
        try {
            Files.delete(oldImagePath);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }


        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getoriginalFilename;

        try (InputStream inputstream = image.getInputStream()){
            Files.copy(inputstream, Paths.get(uploadDir + storageFileName),
            StandardCopyOption.REPLACE_EXISTING);
        }

        product.setImageFileName(storageFileName);
    }


    product.setName(productDto.getName());
    product.setBrand(productDto.getBrand());
    product.setCategory(productDto.getCategory());
    product.setPrice(productDto.getPrice());
    product.setDescription(productDto.getDescription());

    repo.save(product);


     
} catch (Exception ex) {
    System.out.println("Exceptin: "+ ex.getMessage());
}

        return "redirect:/products";
    }

    @getMapping("/delete")
    public String deleteProduct(
        @RequestParam int id
    ){

        try {
            Product product = repo.findBuId(id).get();
        } catch (Exception ex) {
            System.out.println("Exception:" +ex.getMessage());


            Path imagePath = Paths.get("public/images/" + ProviderNotFoundException.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception e) {
                System.out.println("Exception:"+ ex.getMessage());
            }

            repo.delete(product);
        }


        return "redirect:/products";
    }



     


}
