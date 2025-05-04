package com.ecom.productservice.services;



import com.ecom.productservice.dtos.requsetdtos.ProductRequestDto;
import com.ecom.productservice.dtos.responsedtos.ProductResponseDto;
import com.ecom.productservice.entity.Product;
import com.ecom.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> findAllProduct() {
        return productRepository.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapEntityToResponse)
                .orElse(null);
    }

    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        mapRequestToEntity(productRequestDto, product);
        productRepository.save(product);

    }

    public boolean updateProduct(Long id, ProductRequestDto requestDto) {
        return productRepository.findById(id)
                .map(ep -> {
                    mapRequestToEntity(requestDto, ep);
                    productRepository.save(ep);
                    return true;
                }).orElse(false);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void mapRequestToEntity(ProductRequestDto productRequestDto, Product product) {
        product.setProductName(productRequestDto.getProductName());
        product.setPrice(productRequestDto.getPrice());
        product.setCategory(productRequestDto.getCategory());
        product.setProductDescription(productRequestDto.getProductDescription());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setQuantity(productRequestDto.getQuantity());
    }

    private ProductResponseDto mapEntityToResponse(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(product.getProductId());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setProductDescription(product.getProductDescription());
        productResponseDto.setActive(product.getActive());
        productResponseDto.setCreatedAt(product.getCreatedAt());
        productResponseDto.setUpdatedAt(product.getUpdatedAt());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setQuantity(product.getQuantity());
        return productResponseDto;
    }


}