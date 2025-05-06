package com.shoppingmall.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name ="orders")
class Order (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(nullable = false, name="order_date")
    val orderDate: LocalDateTime,

    @Column(nullable = false, name="total_price")
    val totalPrice: Int,

    val status: String,

    @OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val orderItems: MutableList<OrderItem> = ArrayList()
){
    override fun toString(): String {
        return "Order(id=$id, orderDate=$orderDate, totalPrice=$totalPrice, status=$status)"
    }
}