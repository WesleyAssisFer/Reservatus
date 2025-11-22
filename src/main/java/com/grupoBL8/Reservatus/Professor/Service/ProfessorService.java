package com.grupoBL8.Reservatus.Professor.Service;

import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import com.grupoBL8.Reservatus.Professor.ProfessorDTO;
import com.grupoBL8.Reservatus.Professor.ProfessorMapper;
import com.grupoBL8.Reservatus.Professor.Repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    // Dependencia
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper){
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    // Lista todos
    public List<ProfessorDTO> listarTodos(){
        List<ProfessorModel> listarTodosModel = professorRepository.findAll();
        return listarTodosModel.stream()
                .map(professorMapper::map)
                .collect(Collectors.toList());
    }

    // Listar por Id
    public Optional<ProfessorDTO> listarPorId(Long id ){
        Optional<ProfessorModel> listarModel = professorRepository.findById(id);
       return listarModel.map(professorMapper::map);
    }

    // Salvar
    public ProfessorDTO salvar(ProfessorDTO professorDTO){
        ProfessorModel model = professorMapper.map(professorDTO);
        ProfessorModel salvar = professorRepository.save(model);
        return professorMapper.map(salvar);

    }

    // Atualizar
    public Optional<ProfessorDTO> atualizarProfessor(Long id,ProfessorDTO professorDTO){
        return professorRepository.findById(id)
                .map(professor -> {
                    professor.setNome(professorDTO.getNome());
                    ProfessorModel modelSalvo = professorRepository.save(professor);
                    return professorMapper.map(modelSalvo);
                });
    }

    // deletar por id
    public boolean deletarProfessor(Long id){
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
