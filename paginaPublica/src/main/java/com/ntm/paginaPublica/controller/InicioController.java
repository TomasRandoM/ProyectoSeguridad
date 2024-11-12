package com.ntm.paginaPublica.controller;

import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.services.AuthService;
import com.ntm.paginaPublica.services.ServicioDTOService;
import com.ntm.paginaPublica.services.UnidadDeNegocioDTOService;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private UnidadDeNegocioDTOService unidadDeNegocioDTOService;

    @Autowired
    private ServicioDTOService servicioDTOService;

    @Autowired
    private AuthService authService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {

            authService.authenticateWithApi();

            List<ServicioDTO> lista1 = servicioDTOService.listar();
            List<UnidadDeNegocioDTO> lista2 = unidadDeNegocioDTOService.listar();
            HashMap<String, Integer> listaIndices = servicioDTOService.getMapIndices(lista1);
            HashMap<String, String> listaImagenes = servicioDTOService.obtenerImagenEnBase64(lista1);
            model.addAttribute("lista1", lista1);
            model.addAttribute("lista2", lista2);
            model.addAttribute("indices", listaIndices);
            model.addAttribute("imagenes", listaImagenes);
            return "index";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "index";
        }
    }
}