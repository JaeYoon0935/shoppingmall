package com.shoppingmall.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name="order_items")
class OrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    var order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    var product: Product? = null,

    val quantity: Int,

    @Column(name = "order_price")
    val orderPrice: Int,

){
    override fun toString(): String {
        return "OrderItem(Id:$id, quantity:$quantity, orderPrice:$orderPrice)"
    }
}