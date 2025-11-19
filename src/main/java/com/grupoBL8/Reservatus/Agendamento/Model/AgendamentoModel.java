package com.grupoBL8.Reservatus.Agendamento.Model;


import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorModel professorModel;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private SalaModel salaModel;

    private LocalDateTime horario;


}
