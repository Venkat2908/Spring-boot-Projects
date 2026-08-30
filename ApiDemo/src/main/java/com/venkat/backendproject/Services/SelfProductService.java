package com.venkat.backendproject.Services;

import com.venkat.backendproject.Models.Category;
import com.venkat.backendproject.Models.Product;
import com.venkat.backendproject.repository.CategoryRepository;
import com.venkat.backendproject.repository.ProductRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements Productservice {

    private final CategoryRepository categoryRepository;
    private final ProductRespository productRespository;
    public SelfProductService(CategoryRepository categoryRepository, ProductRespository productRespository) {
        this.categoryRepository = categoryRepository;
        this.productRespository = productRespository;
    }

    @Override
    public Product getproductbyid(Long id) {

        return productRespository.findByIdIs(id);
    }

    @Override
    public  Product createproduct(String title, String description, String image, String categoryTitle, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);

        Category categoryObj = categoryRepository.findByTitle(categoryTitle);
        if (categoryObj == null) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryObj = newCategory;

        }
        product.setCategory(categoryObj);

        List<Product> productsTemp = categoryObj.getProducts();

        return productRespository.save(product);
    }

    @Override
    public List<Product> getAllproduct() {
        return productRespository.findAll();

    }
}
