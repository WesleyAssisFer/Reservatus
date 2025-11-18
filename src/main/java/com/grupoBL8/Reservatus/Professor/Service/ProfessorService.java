package com.grupoBL8.Reservatus.Professor.Service;

import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import com.grupoBL8.Reservatus.Professor.Repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    // Dependencia
    private ProfessorRepository professorRepository;
    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    // Lista todos
    public List<ProfessorModel> listarTodos(){
        return professorRepository.findAll();
    }

    // Listar por Id
    public Optional<ProfessorModel> listarPorId(Long id ){
        if(id == null || id <= 0){
            return Optional.empty();
        }else {
            return professorRepository.findById(id);
        }

    }

    // Salvar
    public ProfessorModel salvar(ProfessorModel professorModel){
        return professorRepository.save(professorModel);
    }

    // Atualizar
    public Optional<ProfessorModel> atualizarProfessor(Long id,ProfessorModel professorAtulizado){
        return listarPorId(id)
                .map(professorAntigo -> {
                    professorAntigo.setNome(professorAtulizado.getNome());

                    professorRepository.save(professorAntigo);
                    return professorAntigo;
                });
    }

    // deletar por id

    public boolean deletarProfessor(Long id, ProfessorModel professorModel){
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
