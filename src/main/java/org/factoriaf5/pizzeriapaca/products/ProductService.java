package org.factoriaf5.pizzeriapaca.products;

import java.util.List;
import java.util.Optional;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

   public Product updateProduct(Long id, Product product) {
    Optional<Product> existingProduct = repository.findById(id);
    
    if (existingProduct.isPresent()) {
        Product productToUpdate = existingProduct.get();
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setProductType(product.getProductType());
        productToUpdate.setImage(product.getImage());
        productToUpdate.setAvailable(product.getAvailable());
        return repository.save(productToUpdate);
    } else {
        throw new EntityNotFoundException("Producto no encontrado");
    }
}

    public void deleteProductById(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    public List<Product> getProductsByType(ProductType productType) { 
        return repository.findByProductType(productType); 
    }

    public List<Product> getAvailableProducts() {
        return repository.findByAvailableTrue();
    }
    
}