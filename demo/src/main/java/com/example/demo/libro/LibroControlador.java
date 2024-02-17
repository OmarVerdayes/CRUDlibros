package com.example.demo.libro;


import com.example.demo.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = {"*"})
public class LibroControlador {
    @Autowired
    LibroServicio servicio;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Libro>>> getAll(){
        return new ResponseEntity<>(this.servicio.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Libro>> insert(@Validated @ModelAttribute Libro libro,  MultipartFile imagenArchivo) throws IOException {
        return new ResponseEntity<>(this.servicio.insert(libro,imagenArchivo),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Libro>> update(@Validated @ModelAttribute Libro libro,  MultipartFile imagenArchivo) throws IOException {
        return new ResponseEntity<>(this.servicio.update(libro,imagenArchivo),HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public ResponseEntity<CustomResponse<Libro>> delete(@Validated @RequestBody Map<String,Long> requets){
        Long id=requets.get("id");
        return new ResponseEntity<>(this.servicio.delete(id),HttpStatus.CREATED);
    }
}
