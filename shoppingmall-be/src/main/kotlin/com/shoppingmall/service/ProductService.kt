package com.shoppingmall.service

import com.shoppingmall.dto.CategoryDto
import com.shoppingmall.dto.ProductDto
import com.shoppingmall.dto.ProductRequestDto
import com.shoppingmall.entity.Product
import com.shoppingmall.extension.toDto
import com.shoppingmall.repository.CategoryRepository
import com.shoppingmall.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService (
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val fileService: FileService
){

    fun getAllProducts(pageable: Pageable): Page<ProductDto> {
        return productRepository.findNotDeletedWithCategory(pageable).map{it.toDto()}
    }

    fun getProduct(id: Long): ProductDto{
        val product = productRepository.findById(id).orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다.") }
        return product.toDto()
    }

    @Transactional
    fun regProduct(param: ProductRequestDto): ProductDto{

        val savedImagePath = param.uploadImage?.let { fileService.saveFile(it)}

        val category = categoryRepository.findById(param.categoryId).orElseThrow { IllegalArgumentException("카테고리를 찾을 수 없습니다.") }

        val product = Product(
            name = param.name,
            description = param.description,
            price = param.price,
            stock = param.stock,
            imagePath = savedImagePath,
            category = category
        )

        return productRepository.save(product).toDto()

    }

    @Transactional
    fun modProduct(id: Long, param: ProductRequestDto): ProductDto {

        val product = productRepository.findById(id).orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다.") }
        val category = categoryRepository.findById(param.categoryId).orElseThrow { IllegalArgumentException("카테고리를 찾을 수 없습니다.") }

        val savedImagePath = param.uploadImage?.let { fileService.saveFile(it) }

        product.name = param.name
        product.description = param.description
        product.price = param.price
        product.stock = param.stock
        product.category = category

        if (param.uploadImage != null) {
            val savedImagePath = fileService.saveFile(param.uploadImage)
            product.imagePath = savedImagePath
        } else {
            product.imagePath = null
        }

        return product.toDto()
    }

    @Transactional
    fun delProduct(id: Long): ProductDto{
        val product = productRepository.findById(id).orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다.") }
        product.delYn = "Y"
        return product.toDto()
    }

    fun getAllCategories(): List<CategoryDto>{
        return categoryRepository.findAll().map{it.toDto()}
    }

}