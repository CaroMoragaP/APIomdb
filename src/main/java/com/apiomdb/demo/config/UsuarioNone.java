package com.apiomdb.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.apiomdb.demo.models.entity.Usuario;
import com.apiomdb.demo.service.IUsuarioService;

import jakarta.annotation.PostConstruct;

@Configuration
public class UsuarioNone {
public static final String RECEPTOR_VACIO_MAIL = "none@system.com";
    
    @Autowired
    private IUsuarioService serviceUsuario;
    
    @PostConstruct
    public void init() {
        // Crear el usuario receptor vacío al iniciar la aplicación
        Usuario receptorVacio = serviceUsuario.findOne(RECEPTOR_VACIO_MAIL);
        
        if (receptorVacio == null) {
            receptorVacio = new Usuario(
                "NONE",
                "NONE", 
                "NONE",
                RECEPTOR_VACIO_MAIL,
                1900
            );
            receptorVacio.setPass("NONE");
            serviceUsuario.save(receptorVacio);
        }
    }

}
