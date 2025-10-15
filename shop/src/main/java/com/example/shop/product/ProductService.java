package com.example.shop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Long createProduct(ProductCreateRequest request) {
        Product existingProduct = productRepository.findById(request.getId());
        if (existingProduct != null) {
            throw new RuntimeException("이미 존재하는 제품 아이디입니다: " + request.getId());
        }

        Product product = new Product(
                request.getId(),
                request.getName(),
                request.getPrice()
        );

        productRepository.save(product);

        return product.getId();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("제품을 찾을 수 없습니다.");
        }

        return product;
    }

    public void updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("제품을 찾을 수 없습니다.");
        }

        product.updateInfo(request.getName(), request.getPrice());
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("제품을 찾을 수 없습니다.");
        }

        productRepository.deleteById(id);
    }
}
