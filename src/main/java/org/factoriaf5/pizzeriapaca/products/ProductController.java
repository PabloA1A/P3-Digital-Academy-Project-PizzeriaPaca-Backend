package org.factoriaf5.pizzeriapaca.products;

import java.util.Optional;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.factoriaf5.pizzeriapaca.uploadimage.firebase.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



import java.util.List;




@RestController
@RequestMapping(path = "api/v1/products") 
public class ProductController {

   @Autowired
    private  ProductService productService;

    @Autowired
    private UploadService uploadService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all") 
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}") 
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create") 
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = Optional.ofNullable(productService.getProductById(id));
        
        if (existingProduct.isPresent()) {
            Product updateProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updateProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}") 
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/type/{productType}") 
    public ResponseEntity<List<Product>> getProductByType(@PathVariable ProductType productType) {
        List<Product> products = productService.getProductsByType(productType);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/available") 
    public ResponseEntity<List<Product>> getAvailableProduct() {
        List<Product> availableProducts = productService.getAvailableProducts();
        return ResponseEntity.ok(availableProducts);
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productOptional.get();

        try {
            // Subir la imagen a Firebase
            String fileUrl = uploadService.uploadFileToFirebaseAndSaveRecord(file);
            product.setImage(fileUrl); // Actualizar el campo de imagen
            productService.updateProduct(id, product); // Guarda el producto con la nueva imagen

            return ResponseEntity.ok("Imagen subida exitosamente. URL: " + fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al subir la imagen.");
        }
    }

    @GetMapping(path = "/type/{productType}/available")
    public ResponseEntity<List<Product>> getAvailableProductsByType(@PathVariable ProductType productType) {
        List<Product> availableProducts = productService.getAvailableProductsByType(productType);
        return ResponseEntity.ok(availableProducts);
    }
}
