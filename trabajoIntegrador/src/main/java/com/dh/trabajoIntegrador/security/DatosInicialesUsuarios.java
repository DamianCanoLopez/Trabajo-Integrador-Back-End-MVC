package com.dh.trabajoIntegrador.security;

import com.dh.trabajoIntegrador.entity.Usuario;
import com.dh.trabajoIntegrador.entity.UsuarioRole;
import com.dh.trabajoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUsuarios implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario como si fuera real
        //guardarlo en la base
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passSinCifrar="digital";
        String passCifrada=cifrador.encode(passSinCifrar);
        Usuario usuarioAInsertar=new Usuario("Carlos",
                "Cano",
                "DD@gmail.com",passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
        //CREAR USUARIO TIPO ADMIN
        String passCifrada2=cifrador.encode("house");
        Usuario usuarioAInsertar2=new Usuario("damian","cano",
                "damiancano1811@hotmail.com",passCifrada2,UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);

    }
}
