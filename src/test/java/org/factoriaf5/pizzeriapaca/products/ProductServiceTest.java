package org.factoriaf5.pizzeriapaca.products;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Pizza Margherita", "Deliciosa pizza clásica", 8.99, ProductType.PIZZA, "image-url", true),
                new Product(2L, "Coca Cola", "Refreshing drink", 2.50, ProductType.BEBIDA, "image-url", true)
        );

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductByIdSuccess() {
        Product mockProduct = new Product(1L, "Pizza Pepperoni", "Deliciosa pizza con pepperoni", 9.99, ProductType.PIZZA, "image-url", true);

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Product product = productService.getProductById(1L);

        assertNotNull(product);
        assertEquals("Pizza Pepperoni", product.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product(null, "Pizza Pepperoni", "Deliciosa pizza con pepperoni", 9.99, ProductType.PIZZA, "image-url", true);
        Product savedProduct = new Product(1L, "Pizza Pepperoni", "Deliciosa pizza con pepperoni", 9.99, ProductType.PIZZA, "image-url", true);

        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        Product product = productService.createProduct(newProduct);

        assertNotNull(product.getId());
        assertEquals("Pizza Pepperoni", product.getName());
        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    public void testUpdateProductSuccess() {
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Pizza Margherita", "Deliciosa pizza clásica", 8.99, ProductType.PIZZA, "old-image-url", true);
        Product updatedData = new Product(productId, "Pizza Margherita", "Deliciosa pizza clásica (actualizada)", 9.99, ProductType.PIZZA, "new-image-url", false);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedData);

        Product updatedProduct = productService.updateProduct(productId, updatedData);

        assertEquals("Deliciosa pizza clásica (actualizada)", updatedProduct.getDescription());
        assertEquals(9.99, updatedProduct.getPrice());
        assertEquals("new-image-url", updatedProduct.getImage());
        assertFalse(updatedProduct.getAvailable());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testUpdateProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Product updatedData = new Product(1L, "Pizza Pepperoni", "Deliciosa pizza con pepperoni", 9.99, ProductType.PIZZA, "image-url", true);

        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(1L, updatedData));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteProductByIdSuccess() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(true);

        productService.deleteProductById(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteProductByIdNotFound() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(productId));
        verify(productRepository, times(1)).existsById(productId);
    }
}
