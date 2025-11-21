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
        List<ProfessorModel> listarProfessores = professorRepository.findAll();
        return listarProfessores.stream()
                .map(professorMapper::map)
                .collect(Collectors.toList());
    }

    // Listar por Id
    public ProfessorDTO listarPorId(Long id ){
      Optional<ProfessorModel> professorListarId = professorRepository.findById(id);
        return professorListarId.map(professorMapper::map)
                .orElse(null);

    }

    // Salvar
    public ProfessorModel salvar(ProfessorModel professorModel){
        return professorRepository.save(professorModel);
    }

    // Atualizar
    public Optional<ProfessorModel> atualizarProfessor(Long id,ProfessorModel professorAtualizado){
        return listarPorId(id)
                .map(professor -> {
                    professor.setNome(professorAtualizado.getNome());
                    return professorRepository.save(professor);
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
