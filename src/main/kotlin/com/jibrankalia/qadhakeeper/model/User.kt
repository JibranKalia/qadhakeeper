package com.jibrankalia.qadhakeeper.model.user

import jakarta.persistence.*
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false, unique = true) val username: String,
    @Column(nullable = false) val password: String,
    @Column(nullable = false) val enabled: Boolean,
    @CreatedDate @Column(nullable = false) val createdAt: LocalDateTime? = null,
    @LastModifiedDate @Column(nullable = false) val updatedAt: LocalDateTime? = null,
)
// : UserDetails {
//   override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()
//   override fun getPassword(): String = password
//   override fun getUsername(): String = email
//   override fun isAccountNonExpired(): Boolean = true
//   override fun isAccountNonLocked(): Boolean = true
//   override fun isCredentialsNonExpired(): Boolean = true
//   override fun isEnabled(): Boolean = true
// }
