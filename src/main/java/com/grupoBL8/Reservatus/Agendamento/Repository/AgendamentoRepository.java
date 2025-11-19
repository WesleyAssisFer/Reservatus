package com.grupoBL8.Reservatus.Agendamento.Repository;

import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {

}
