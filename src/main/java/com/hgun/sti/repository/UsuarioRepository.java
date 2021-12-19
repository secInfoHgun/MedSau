package com.hgun.sti.repository;

import com.hgun.sti.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.login = :login")
    public Usuario getUsuarioByLogin(@Param("login") String username);
}
