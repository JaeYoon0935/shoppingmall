package com.shoppingmall.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.shoppingmall.dto.OrderDetailDto
import com.shoppingmall.entity.QOrder
import com.shoppingmall.entity.QOrderItem
import com.shoppingmall.entity.QProduct
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : OrderRepositoryCustom {

    override fun getUserOrders(id: Long): List<OrderDetailDto> {
        val order: QOrder = QOrder.order
        val orderItem: QOrderItem = QOrderItem.orderItem
        val product: QProduct = QProduct.product

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
                product.imagePath
            ))
            .from(order)
            .leftJoin(order.orderItems, orderItem)
            .join(orderItem.product(), product)
            .where(order.user().id.eq(id))
            .orderBy(order.id.desc())
            .fetch()

        return result
    }

}