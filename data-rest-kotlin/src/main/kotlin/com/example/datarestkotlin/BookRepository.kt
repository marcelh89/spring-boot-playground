package com.example.datarestkotlin

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : PagingAndSortingRepository<Book, Long> {
    fun findByTitleContaining(@Param("query") query: String, page: Pageable): Page<Book>
    fun findByAuthorContaining(@Param("query") query: String, page: Pageable): Page<Book>
}