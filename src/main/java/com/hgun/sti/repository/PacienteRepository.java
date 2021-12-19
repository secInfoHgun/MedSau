package com.hgun.sti.repository;

import com.hgun.sti.models.Paciente;
import com.hgun.sti.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.prontuario = :prontuario")
    public Paciente getPacienteByProntuario(@Param("prontuario") String prontuario);
}