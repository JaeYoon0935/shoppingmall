package com.shoppingmall.entity
import jakarta.persistence.*
import java.time.LocalDateTime

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

    @Column(name="reg_dts")
    var regDts: LocalDateTime? = null,

    @Column(name="reg_user")
    var regUser: String? = null,

    @Column(name="mod_dts")
    var modDts: LocalDateTime? = null,

    @Column(name="mod_user")
    var modUser: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    open var category: Category
) {
    override fun toString(): String {
        return "Product(id=$id, name=$name, price=$price, stock=$stock, imagePath=$imagePath, delYn=$delYn)"
    }
}