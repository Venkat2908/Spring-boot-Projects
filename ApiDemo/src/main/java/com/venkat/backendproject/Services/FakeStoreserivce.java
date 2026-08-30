package com.venkat.backendproject.Services;

import com.venkat.backendproject.DTOS.FakeStoresproductDTO;
import com.venkat.backendproject.Models.Category;
import com.venkat.backendproject.Models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreservice")

public class    FakeStoreserivce implements Productservice {

    RestTemplate restTemplate ;
    public FakeStoreserivce(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    @Override
    public Product getproductbyid(Long id) {

        ResponseEntity<FakeStoresproductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/"
        +id,FakeStoresproductDTO.class);


        FakeStoresproductDTO fakeStoresproductDTO = response.getBody();
        Product product = new Product();
        product.setId(fakeStoresproductDTO.getId());
        product.setTitle(fakeStoresproductDTO.getTitle());
        product.setDescription(fakeStoresproductDTO.getDescription());
        product.setPrice(fakeStoresproductDTO.getPrice());

        Category category = new Category();
        category.setTitle(fakeStoresproductDTO.getCategory());
        product.setCategory(category);

        return product;

    }

    @Override
    public Product createproduct(String title, String description, String image, String category, double price) {
        FakeStoresproductDTO fakeStoresproductDTO = new FakeStoresproductDTO();
        fakeStoresproductDTO.setTitle(title);
        fakeStoresproductDTO.setDescription(description);
        fakeStoresproductDTO.setImage(image);
        fakeStoresproductDTO.setCategory(category);
        fakeStoresproductDTO.setPrice(price);

        FakeStoresproductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoresproductDTO, FakeStoresproductDTO.class);

        return response.toProduct();
    }

    @Override
    public List<Product> getAllproduct() {
    FakeStoresproductDTO[] response1 =restTemplate.getForObject("https://fakestoreapi.com/products",
            FakeStoresproductDTO[].class);

    List<Product> products = new ArrayList<Product>();

    for (FakeStoresproductDTO fakeStoresproductDTO : response1) {
        products.add(fakeStoresproductDTO.toProduct());




    }
        return products;
    }
}
