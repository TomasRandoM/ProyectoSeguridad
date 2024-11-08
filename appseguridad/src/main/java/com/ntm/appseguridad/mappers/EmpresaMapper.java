package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.EmpresaDTO;
import com.ntm.appseguridad.entities.Direccion;
import com.ntm.appseguridad.entities.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ContactoMapper.class, DireccionMapper.class})
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    @Mapping(source = "contacto", target = "contacto")
    @Mapping(source = "direccion", target = "direccion")
    EmpresaDTO toDTO(Empresa empresa);

    @Mapping(source = "contacto", target = "contacto")
    @Mapping(source = "direccion", target = "direccion")
    Empresa toEntity(EmpresaDTO empresaDTO);

    List<EmpresaDTO> toDtoList(List<Empresa> entities);
}
