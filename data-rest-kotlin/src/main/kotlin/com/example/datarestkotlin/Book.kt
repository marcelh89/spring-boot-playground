package com.example.datarestkotlin

import jakarta.persistence.*

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(columnDefinition = "VARCHAR", length = 100)
    private var title: String? = null

    @Column(columnDefinition = "VARCHAR", length = 100)
    private var author: String? = null

    @Column(columnDefinition = "VARCHAR", length = 1000)
    private var blurb: String? = null

    private val pages = 0

}