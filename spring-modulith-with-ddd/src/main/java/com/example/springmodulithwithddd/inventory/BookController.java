package com.example.springmodulithwithddd.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookManagement books;

    @PostMapping("/books")
    ResponseEntity<BookDto> addBookToInventory(@RequestBody AddBookRequest request) {
        var book = books.addToInventory(request.title, new Book.Barcode(request.inventoryNumber()), request.isbn(), request.author);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/books/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        books.removeFromInventory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/{id}")
    ResponseEntity<BookDto> viewSingle(@PathVariable("id") Long id) {
        return books.locate(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/books")
    ResponseEntity<List<BookDto>> viewIssued() {
        return ResponseEntity.ok(books.issuedBooks());
    }

    public record AddBookRequest(String title, String inventoryNumber, String isbn, String author) {
    }

}
