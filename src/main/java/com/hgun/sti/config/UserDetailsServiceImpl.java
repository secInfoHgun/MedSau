package com.hgun.sti.config;

import com.hgun.sti.models.Usuario;
import com.hgun.sti.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario user = usuarioRepository.getUsuarioByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario não foi encontrado");
        }

        return new MyUserDetails(user);
    }

}