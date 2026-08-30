package com.venkat.backendproject.repository;

import com.venkat.backendproject.Models.Product;
import com.venkat.backendproject.Projections.ProductWithTitleAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRespository extends JpaRepository<Product,Long> {

    Product save(Product product);

    Product findByIdIs(Long id);

    List<Product> findAll();
    @Query("select p from Product p where p.category.title = :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") Long id);

    // Native SQL query
    @Query(value = "select * from product where id = :id and title = :title", nativeQuery = true)
    Product getProductWithSomeTitleAndId(@Param("title") String title,
                                         @Param("id") Long id);


    @Query("select p.id, p.title from Product p where p.title = :title and p.id = :id")
    ProductWithTitleAndId getProductWithASpecificTitleAndId2(@Param("title") String title, @Param("id") Long id);


}
