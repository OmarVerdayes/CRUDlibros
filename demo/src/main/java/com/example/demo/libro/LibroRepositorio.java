package com.example.demo.libro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro,Long> {
}
