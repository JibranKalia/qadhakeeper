package com.jibrankalia.qadhakeeper.model.user

import jakarta.persistence.*
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
data class User (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(nullable = false, unique = true)
  val username: String,

  @Column(nullable = false)
  val password: String,

  @Column(nullable = false)
  val enabled: Boolean,

  @CreatedDate
  @Column(nullable = false)
  val createdAt : LocalDateTime? = null,

  @LastModifiedDate
  @Column(nullable = false)
  val updatedAt : LocalDateTime? = null,
)
