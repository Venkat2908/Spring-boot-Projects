package com.venkat.backendproject.Controllers;



import com.venkat.backendproject.DTOS.CreateproductDTO;
import com.venkat.backendproject.Models.Product;
import com.venkat.backendproject.Services.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/users")
public class ProductController {

    Productservice productservice;

    public ProductController( @Qualifier("selfProductService") Productservice productservice) {
        this.productservice = productservice;

    }

    @GetMapping ("/")
    public ResponseEntity<List<Product>> getallproduct(){
        List<Product> responsedata = productservice.getAllproduct();

        ResponseEntity<List<Product>> responseentity = new
                ResponseEntity<>(responsedata, HttpStatusCode.valueOf(200));

        return responseentity;


    }
    @GetMapping("/{id}")
    public Product getsingleidproduct(@PathVariable Long id){
        return productservice.getproductbyid(id);


    }

    @PostMapping()
    public Product createproduct( @RequestBody CreateproductDTO createproductDTO){
        return productservice.createproduct(
        createproductDTO.getTitle(),
                createproductDTO.getDescription(),
                createproductDTO.getImage(),
                createproductDTO.getCategory(),
                createproductDTO.getPrice()

        );

    }
}
