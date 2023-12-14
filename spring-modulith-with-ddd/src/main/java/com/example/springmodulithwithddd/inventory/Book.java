package com.example.springmodulithwithddd.inventory;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Book", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"barcode"})
})
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "author"))
    private Author author;

    private String title;

    @Embedded
    private Barcode inventoryNumber;

    private String isbn;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Version
    private Long version;

    public Book(String title, Barcode inventoryNumber, String isbn, Author author) {
        this.author = author;
        this.title = title;
        this.inventoryNumber = inventoryNumber;
        this.isbn = isbn;
        this.status = BookStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return BookStatus.AVAILABLE.equals(this.status);
    }

    public boolean issued() {
        return BookStatus.ISSUED.equals(this.status);
    }

    public Book markIssued() {
        if (this.status.equals(BookStatus.ISSUED)) {
            throw new IllegalStateException("Book is already issued");
        }
        this.status = BookStatus.ISSUED;
        return this;
    }

    public Book markAvailable() {
        this.status = BookStatus.AVAILABLE;
        return this;
    }



    public record Barcode(String barcode) {
    }

    public record Author(String name) {
    }


    public enum BookStatus {
        AVAILABLE, ISSUED
    }
}
