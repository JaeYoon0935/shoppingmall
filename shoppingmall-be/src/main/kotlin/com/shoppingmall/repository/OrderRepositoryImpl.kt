package com.shoppingmall.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.shoppingmall.dto.OrderDetailDto
import com.shoppingmall.entity.QOrder
import com.shoppingmall.entity.QOrderItem
import com.shoppingmall.entity.QProduct
import com.shoppingmall.entity.QUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : OrderRepositoryCustom {

    override fun getUserOrders(id: Long): List<OrderDetailDto> {
        val order: QOrder = QOrder.order
        val orderItem: QOrderItem = QOrderItem.orderItem
        val product: QProduct = QProduct.product
        val user: QUser = QUser.user

        val result: List<OrderDetailDto> = queryFactory
            .select(
                Projections.constructor(
                    OrderDetailDto::class.java,
                    order.id,
                    order.orderDate,
                    order.totalPrice,
                    order.status,
                    orderItem.id,
                    orderItem.quantity,
                    orderItem.orderPrice,
                    product.id,
                    product.name,
                    product.imagePath,
                    user.email
                ))
            .from(order)
            .leftJoin(order.orderItems, orderItem)
            .join(orderItem.product(), product)
            .join(order.user(), user)
            .where(order.user().id.eq(id))
            .orderBy(order.id.desc())
            .fetch()

        return result
    }

    override fun getAllUserOrders(pageable: Pageable): Page<OrderDetailDto> {
        val order = QOrder.order
        val orderItem = QOrderItem.orderItem
        val product = QProduct.product
        val user = QUser.user

        // 1단계: Order ID만 조회 (페이지네이션 적용)
        val orderIds = queryFactory
            .select(order.id)
            .from(order)
            .orderBy(order.id.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        if (orderIds.isEmpty()) {
            return PageImpl(emptyList(), pageable, 0)
        }

        // 2단계: 상세 정보 조회 (OrderItem, Product, User join)
        val results = queryFactory
            .select(
                Projections.constructor(
                    OrderDetailDto::class.java,
                    order.id,
                    order.orderDate,
                    order.totalPrice,
                    order.status,
                    orderItem.id,
                    orderItem.quantity,
                    orderItem.orderPrice,
                    product.id,
                    product.name,
                    product.imagePath,
                    user.email
                )
            )
            .from(order)
            .leftJoin(order.orderItems, orderItem)
            .join(orderItem.product(), product)
            .join(order.user(), user)
            .where(order.id.`in`(orderIds))
            .orderBy(order.id.desc())
            .fetch()

        // 전체 주문 수 (distinct order 기준 count)
        val totalCount = queryFactory
            .select(order.id.countDistinct())
            .from(order)
            .fetchOne() ?: 0L

        return PageImpl(results, pageable, totalCount)
    }


}