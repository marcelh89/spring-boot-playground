package com.example.springmodulithwithddd.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //visibility of this repository interface is package-private and not public
interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByInventoryNumber(Book.Barcode inventoryNumber);

    List<Book> findByStatus(Book.BookStatus status);
}
