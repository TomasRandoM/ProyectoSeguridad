package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.EmpleadoDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoDAORest  extends BaseDAORestImpl<EmpleadoDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/empleado";
    }
}
