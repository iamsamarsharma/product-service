package com.ecom.productservice.controller;



import com.ecom.productservice.dtos.requsetdtos.ProductRequestDto;
import com.ecom.productservice.dtos.responsedtos.ProductResponseDto;
import com.ecom.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){

        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){

        ProductResponseDto productResponseDto = productService.getProductById(id);
        if(productResponseDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto productRequestDto){
        productService.createProduct(productRequestDto);
        return ResponseEntity.ok("Product created successfully");
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequestDto requestDto,
    @PathVariable Long id){
        boolean updated = productService.updateProduct(id, requestDto);
        if(updated){
            return ResponseEntity.ok("product updated successfully");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        boolean delete = productService.deleteProduct(id);
        if(delete){
            return ResponseEntity.ok("product deleted successfully");
        }
        return  ResponseEntity.badRequest().body("product not present") ;
    }
}
