package com.grupoBL8.Reservatus.Agendamento.Service;

import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import com.grupoBL8.Reservatus.Agendamento.Repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {
    // Dependencia
    private final AgendamentoRepository agendamentoRepository;
    public AgendamentoService(AgendamentoRepository agendamentoRepository){
        this.agendamentoRepository = agendamentoRepository;
    }

    // Listar Todos
    public List<AgendamentoModel> listarTodos(){
        return agendamentoRepository.findAll();
    }

    // Listar Por Id
    public Optional<AgendamentoModel> listarPorId(Long id){
        return agendamentoRepository.findById(id);
    }

    // Salvar
    public AgendamentoModel salvar (AgendamentoModel agendamentoModel){
        return agendamentoRepository.save(agendamentoModel);
    }

    // Atualizar
    public Optional<AgendamentoModel> atualizar(Long id, AgendamentoModel agendamentoAtualizado){
        return listarPorId(id)
                .map(agendamento -> {
                    agendamento.setHorario(agendamentoAtualizado.getHorario());
                    agendamento.setProfessorModel(agendamentoAtualizado.getProfessorModel());
                    agendamento.setSalaModel(agendamentoAtualizado.getSalaModel());
                  return agendamentoRepository.save(agendamento);
                });
    }

    // Deletar
    public boolean deletar(Long id){
        if(listarPorId(id).isPresent()){
            agendamentoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

}
