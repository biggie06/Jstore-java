package com.Mystore.JStore.models; 

import org.springframework.web.multipart.Multifile;

import jakarta.validation.constraints.*;





public class ProductDto {
    @NotEmpty(message = "Name Required")
    private string name;
    
    @NotEmpty(message ="Brand Required")
    private string brand;

    @NotEmpty(message = "Category Required")
    private string category;

    @Min(0)
    private double price;
    @Size(min = 10 , message = "Minimum of 10 characters")
    @Size(max = 2000 , message = "Maximum of 2000 characters")


    private MultipartFile imageFile;

    public string getName() {
        return name;
    }

    public void setName(string name) {
        this.name = name;
    }

    public string getBrand() {
        return brand;
    }

    public void setBrand(string brand) {
        this.brand = brand;
    }

    public string getCategory() {
        return category;
    }

    public void setCategory(string category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }


}
