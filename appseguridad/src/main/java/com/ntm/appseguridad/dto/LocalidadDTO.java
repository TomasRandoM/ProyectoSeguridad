package com.ntm.appseguridad.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LocalidadDTO extends BaseDTO {
    private String nombre;
    private DepartamentoDTO departamento;
}
