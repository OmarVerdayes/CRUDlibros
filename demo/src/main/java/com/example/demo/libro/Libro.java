package com.example.demo.libro;

import com.example.demo.genero.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="libro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( columnDefinition = "varchar(100)",nullable = false)
    private String nombre;
    @Column( columnDefinition = "varchar(100)",nullable = false)
    private String autor;
    @Column( columnDefinition = "int",nullable = false)
    private int anioPublicacion;
    @Column( columnDefinition = "varchar(255)",nullable = false)
    private String imagen;
    @ManyToOne
    @JoinColumn(name="id_genero",nullable = false)
    private Genero genero;
}
