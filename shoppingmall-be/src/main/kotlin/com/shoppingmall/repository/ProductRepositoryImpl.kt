package com.shoppingmall.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.shoppingmall.entity.Product
import com.shoppingmall.entity.QProduct
import com.shoppingmall.entity.QCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : ProductRepositoryCustom {

    override fun findNotDeletedWithCategory(pageable: Pageable): Page<Product> {
        val product: QProduct = QProduct.product
        val category: QCategory = QCategory.category

        val results: List<Product> = queryFactory
            .selectFrom(product)
            .join(product.category(), category).fetchJoin()
            .where(product.delYn.eq("N"))
            .orderBy(product.id.asc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val count: Long = queryFactory
            .select(product.count())
            .from(product)
            .where(product.delYn.eq("N"))
            .fetchOne() ?: 0L

        return PageImpl(results, pageable, count)
    }
}