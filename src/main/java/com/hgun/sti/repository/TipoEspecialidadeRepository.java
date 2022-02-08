package com.hgun.sti.repository;

import com.hgun.sti.models.TipoEspecialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoEspecialidadeRepository extends JpaRepository<TipoEspecialidade, Long> {
    @Query("SELECT t FROM TipoEspecialidade t WHERE t.ativa = true order by t.nome asc")
    List<TipoEspecialidade> listarEspecialidadesAtivas();

    @Query("SELECT t FROM TipoEspecialidade t order by t.nome asc")
    List<TipoEspecialidade> listarPorOrgemAlfabetica();
}