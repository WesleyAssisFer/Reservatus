package com.grupoBL8.Reservatus.Agendamento.Repository;

import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {

     boolean existesBySalaModelIdAndHorario(Long salaId, LocalDateTime horaio);

     boolean existesByProfessorModelIdAndHorario(Long professorId, LocalDateTime horario);
}
