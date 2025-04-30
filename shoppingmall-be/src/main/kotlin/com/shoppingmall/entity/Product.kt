package com.shoppingmall.entity
import jakarta.persistence.*

@Entity
@Table(name= "products")
class Product (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    var description: String? = null,

    @Column(nullable = false)
    var price: Int = 0,

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(name="image_path")
    var imagePath: String? = null,

    @Column(name="del_yn", nullable = false)
    var delYn: String = "N",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category
) {
    override fun toString(): String {
        return "Product(id=$id, name=$name, price=$price, stock=$stock, imagePath=$imagePath, delYn=$delYn)"
    }
}