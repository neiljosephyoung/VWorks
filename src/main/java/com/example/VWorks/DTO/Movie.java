package com.example.VWorks.DTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Data annotation uses lombok to generate the necessary getters and setters
@Entity(name = "Movies")
@Data
public class Movie {
    @Id
    private String name;

    private int releaseYear;
    private int runtime;
    private String category;
    private Double rating;
    private Double boxOffice;


}


