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
    public List<ProfessorModel> listarTodos(){
        return professorRepository.findAll();
    }

    // Listar por Id
    public Optional<ProfessorModel> listarPorId(Long id ){
        return professorRepository.findById(id);
    }

    // Salvar
    public ProfessorDTO salvar(ProfessorModel professorModel){
        ProfessorModel modelSalvar = professorRepository.save(professorModel);
            return professorMapper.map(modelSalvar);
    }

    // Atualizar
    public Optional<ProfessorDTO> atualizarProfessor(Long id,ProfessorModel professorAtualizado){
        return professorRepository.findById(id)
                .map(professor -> {
                    professor.setNome(professorAtualizado.getNome());
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
