package com.example.prajjwal.watchlistApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prajjwal.watchlistApp.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
