package com.shoppingmall.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.shoppingmall.dto.ProductCardDto
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

    override fun findNotDeletedWithCategory(pageable: Pageable): Page<ProductCardDto> {
        val product: QProduct = QProduct.product
        val category: QCategory = QCategory.category

        val orders = pageable.sort.mapNotNull {
            when (it.property) {
                "id" -> if (it.isAscending) product.id.asc() else product.id.desc()
                else -> null
            }
        }.toTypedArray()

        val results: List<ProductCardDto> = queryFactory
            .select(
                com.querydsl.core.types.Projections.constructor(
                    ProductCardDto::class.java,
                    product.id,
                    product.name,
                    product.price,
                    product.imagePath,
                    product.stock,
                    product.category().name,
                    product.delYn,
                    product.category().id,
                    product.regDts
                )
            ).from(product)
            .join(product.category(), category)
            .where(product.delYn.eq("N"))
            .orderBy(*orders)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val count: Long = queryFactory
            .select(product.id.countDistinct())
            .from(product)
            .where(product.delYn.eq("N"))
            .fetchOne() ?: 0L

        return PageImpl(results, pageable, count)
    }

    //메인화면 상품목록 (등록일 기준, 카테고리별 분류)
    override fun findRecentTopProductsByCategory(limit: Int): List<ProductCardDto> {
        val product = QProduct.product

        val products: List<ProductCardDto> = queryFactory
            .select(
                com.querydsl.core.types.Projections.constructor(
                    ProductCardDto::class.java,
                    product.id,
                    product.name,
                    product.price,
                    product.imagePath,
                    product.stock,
                    product.category().name,
                    product.delYn,
                    product.category().id,
                    product.regDts
                )
            )
            .from(product)
            .where(product.delYn.eq("N"))
            .orderBy(product.regDts.desc())
            .fetch()

        return products
            .groupBy { it.categoryId }
            .values
            .flatMap { it.take(limit) }
    }

    override fun findProductsByCategory(id: Long, pageable: Pageable): Page<ProductCardDto> {
        val product = QProduct.product

        val orders = pageable.sort.mapNotNull {
            when (it.property) {
                "regDts" -> if (it.isAscending) product.regDts.asc() else product.regDts.desc()
                else -> null
            }
        }.toTypedArray()

        val results: List<ProductCardDto> = queryFactory
            .select(
                com.querydsl.core.types.Projections.constructor(
                    ProductCardDto::class.java,
                    product.id,
                    product.name,
                    product.price,
                    product.imagePath,
                    product.stock,
                    product.category().name,
                    product.delYn,
                    product.category().id,
                    product.regDts
                )
            )
            .from(product)
            .where(product.delYn.eq("N"),
                         product.category().id.eq(id))
            .orderBy(*orders)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val count: Long = queryFactory
            .select(product.id.countDistinct())
            .from(product)
            .where(product.delYn.eq("N"),
                    product.category().id.eq(id))
            .fetchOne() ?:0L

        return PageImpl(results, pageable, count)
    }

    override fun findByQuery(query: String, pageable: Pageable): Page<ProductCardDto> {
        val product = QProduct.product

        val results: List<ProductCardDto> = queryFactory
            .select(
                com.querydsl.core.types.Projections.constructor(
                    ProductCardDto::class.java,
                    product.id,
                    product.name,
                    product.price,
                    product.imagePath,
                    product.stock,
                    product.category().name,
                    product.delYn,
                    product.category().id,
                    product.regDts
                )
            )
            .from(product)
            .where( product.delYn.eq("N").and(
                product.name.containsIgnoreCase(query)
                        .or(product.description.containsIgnoreCase(query)))
            )
            .orderBy(product.regDts.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val count: Long = queryFactory
            .select(product.id.countDistinct())
            .from(product)
            .where(product.delYn.eq("N").and(
                product.name.containsIgnoreCase(query)
                    .or(product.description.containsIgnoreCase(query))
            ))
            .fetchOne() ?: 0L

        return PageImpl(results, pageable, count)
    }

}