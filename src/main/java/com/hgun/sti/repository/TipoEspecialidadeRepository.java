package com.hgun.sti.repository;

import com.hgun.sti.models.TipoEspecialidade;
import com.hgun.sti.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoEspecialidadeRepository extends JpaRepository<TipoEspecialidade, Long> {
    @Query("SELECT t FROM TipoEspecialidade t WHERE t.ativa = true")
    List<TipoEspecialidade> listarEspecialidadesAtivas();
}