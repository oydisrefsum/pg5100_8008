package org.tsdes.backend.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="MOVIE")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    @Past
    @NotNull
    private LocalDate dateOfRelease;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Review review) {
        this.reviews.add(review);
    }
}

