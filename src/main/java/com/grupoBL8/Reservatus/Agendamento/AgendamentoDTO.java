package com.grupoBL8.Reservatus.Agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {

    private Long id;
    private Long id_Professor;
    private Long id_Sala;
    private LocalDateTime horario;
}
