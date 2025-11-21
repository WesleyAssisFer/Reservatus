package com.grupoBL8.Reservatus.Professor;
import org.springframework.stereotype.Component;
import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;

@Component
public class ProfessorMapper {

    public ProfessorModel map(ProfessorDTO professorDTO){
        ProfessorModel professorModel = new ProfessorModel();
        professorModel.setId(professorDTO.getId());
        professorModel.setNome(professorDTO.getNome());

        return professorModel;
    }

    public ProfessorDTO map(ProfessorModel professorModel){
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professorModel.getId());
        professorDTO.setNome(professorModel.getNome());

        return professorDTO;
    }

}
