package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.CuentaCorreo;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.CuentaCorreoRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CuentaCorreoServiceImpl extends BaseServiceImpl<CuentaCorreo,String> implements CuentaCorreoService {

    private final CuentaCorreoRepository cuentacorreoRepository;

    public CuentaCorreoServiceImpl(BaseRepository<CuentaCorreo, String> baserepository, CuentaCorreoRepository cuentacorreoRepository) {super(baserepository);
        this.cuentacorreoRepository = cuentacorreoRepository;
    }

    @Override
    public boolean validar(CuentaCorreo entity, String caso) throws ErrorServiceException {
        try {
            if (entity.getCorreo() == null) {
                throw new ErrorServiceException("Debe indicar el correo");
            }
            if (caso.equals("SAVE")) {
                if (cuentacorreoRepository.existsByCorreoAndEliminadoFalse(entity.getCorreo())) {
                    throw new ErrorServiceException("El correo ya existe en el sistema");
                }
            } else {
                CuentaCorreo cc = cuentacorreoRepository.findByCorreoAndEliminadoFalse(entity.getCorreo());
                if (cc != null) {
                    if (!cc.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El email especificado ya existe en el sistema");
                    }
                }
            }

            if (entity.getSmtp() == null || entity.getSmtp().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el smtp");
            }
            if (entity.getClave() == null || entity.getClave().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la clave");
            }
            if (entity.getPuerto() == null || entity.getPuerto().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el puerto");
            }
            if (entity.getEmpresa() != null) {
                throw new ErrorServiceException("Debe indicar la empresa");
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex ) {
            throw new ErrorServiceException("Error de sistemas");
        }

    }
}