package com.example.shop.product.controller;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import com.example.shop.product.entity.Product;
import com.example.shop.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "상품 관리", description = "상품 CRUD API")
public class ProductController {

    final private ProductService productService;

    @PostMapping
    @Operation(summary = "상품 생성", description = "새로운 상품을 등록합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패 또는 중복된 상품 아이디)")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        Long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + productId)).build();
    }

    @GetMapping
    @Operation(summary = "모든 상품 조회", description = "모든 상품을 조회합니다.")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 조회", description = "상품을 조회합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{productId}")
    @Operation(summary = "상품 업데이트", description = "상품 정보를 수정합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid ProductUpdateRequest request) {
        productService.updateProduct(productId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
