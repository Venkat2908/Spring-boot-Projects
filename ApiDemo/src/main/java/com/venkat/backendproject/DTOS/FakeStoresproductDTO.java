package com.venkat.backendproject.DTOS;
import com.venkat.backendproject.Models.Category;
import com.venkat.backendproject.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class FakeStoresproductDTO {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImage(getImage());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;

    }
}
