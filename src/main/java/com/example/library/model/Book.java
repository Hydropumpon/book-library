package com.example.library.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", unique = true)
    private String title;

    @Size(max = 4096)
    @Column(name = "description")
    private String description;

    @NotNull
    @Min(0)
    private Integer quantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns =
    @JoinColumn(name = "author_id"))
    @Builder.Default
    @Fetch(FetchMode.SUBSELECT)
    private Set<Author> authors = new HashSet<>();

    @Builder.Default
    @OneToMany(targetEntity = Borrowed.class, mappedBy = "book", cascade = CascadeType.REMOVE)
    private Set<Borrowed> borrowedSet = new HashSet<>();

    public Book(Long id) {
        this.id = id;
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + ", description=" + description + ", authors=" + authors
                .stream().map(Author::getName).collect(Collectors.toList()) + '}';
    }
}
