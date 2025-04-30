package com.shoppingmall.entity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    var address: String? = null,
    var phone: String? = null,

    @Column(name = "del_yn", nullable = false)
    var delYn: String = "N"

)