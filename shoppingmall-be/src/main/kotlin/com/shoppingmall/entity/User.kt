package com.shoppingmall.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    var address: String? = null,
    var phone: String? = null,

    var birth: String? = null,

    @Column(name = "del_yn", nullable = false)
    var delYn: String = "N",

    @Column(name="reg_dts")
    var regDts: LocalDateTime = LocalDateTime.now(),

    @Column(name="reg_user")
    var regUser: String? = null,

    @Column(name="mod_dts")
    var modDts: LocalDateTime? = null,

    @Column(name="mod_user")
    var modUser: String? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role> = mutableSetOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val orders: MutableList<Order> = mutableListOf()

    )