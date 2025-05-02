package com.shoppingmall.entity
import jakarta.persistence.*

@Entity
@Table(name = "categories")
class Category (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    open var products: MutableList<Product> = ArrayList()
){
    override fun toString(): String {
        return "Category(id=$id, name=$name)"
    }
}