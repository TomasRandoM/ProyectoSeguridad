package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Localidad;

public interface LocalidadService extends BaseService<Localidad, String> {
    Localidad save(Localidad localidad) throws Exception;
}
