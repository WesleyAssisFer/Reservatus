package com.grupoBL8.Reservatus.Agendamento;

import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;

@Component
public class AgendamentoMapper {

    public AgendamentoModel map(AgendamentoDTO dto, ProfessorModel professorModel, SalaModel salaModel){
        AgendamentoModel model = new AgendamentoModel();
        model.setId(dto.getId());
        model.setProfessorModel(professorModel);
        model.setSalaModel(salaModel);
        model.setHorario(dto.getHorario());

        return model;
    }

    public AgendamentoDTO map(AgendamentoModel agendamentoModel){
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamentoModel.getId());
        dto.setId_Professor(agendamentoModel.getProfessorModel().getId());
        dto.setId_Sala(agendamentoModel.getSalaModel().getId());
        dto.setHorario(agendamentoModel.getHorario());

        return dto;
    }
}
