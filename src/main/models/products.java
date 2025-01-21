
package com.Mytools.JStore.models
import java.util.Date

 

import jakarta.persistence.*;


@Entity
@Table(name = "products")


public class products {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

private string name;
private string brand;
private strinf categiry;
private double price;

@column(columnDefinition = "TEXT")
private sring description;
private date createdAt;
private string imageFileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public strinf getCategiry() {
        return categiry;
    }

    public void setCategiry(strinf categiry) {
        this.categiry = categiry;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public sring getDescription() {
        return description;
    }

    public void setDescription(sring description) {
        this.description = description;
    }

    public date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(date createdAt) {
        this.createdAt = createdAt;
    }

    public string getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(string imageFileName) {
        this.imageFileName = imageFileName;
    }


}
