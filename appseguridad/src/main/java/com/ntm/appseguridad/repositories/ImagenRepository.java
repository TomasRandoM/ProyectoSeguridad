package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Imagen;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends BaseRepository<Imagen, String> {
    Imagen findByNombreAndEliminadoFalse(String nombre);
    boolean existsByNombreAndEliminadoFalse(String nombre);
}
