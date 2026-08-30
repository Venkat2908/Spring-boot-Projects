package com.venkat.backendproject.Services;

import com.venkat.backendproject.Models.Product;

import java.util.List;

public interface Productservice {

    public Product getproductbyid(Long id);
    public Product createproduct(String title,String description,String image,String category,double price);

    public List<Product> getAllproduct();


}
