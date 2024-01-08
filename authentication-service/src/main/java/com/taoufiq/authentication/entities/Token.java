package com.taoufiq.authentication.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Date expiration;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
