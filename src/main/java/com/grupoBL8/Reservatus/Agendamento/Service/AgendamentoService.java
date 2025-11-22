package com.grupoBL8.Reservatus.Agendamento.Service;

import com.grupoBL8.Reservatus.Agendamento.AgendamentoDTO;
import com.grupoBL8.Reservatus.Agendamento.AgendamentoMapper;
import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import com.grupoBL8.Reservatus.Agendamento.Repository.AgendamentoRepository;
import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import com.grupoBL8.Reservatus.Professor.Repository.ProfessorRepository;
import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import com.grupoBL8.Reservatus.Sala.Repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    // Dependencia
    private final AgendamentoRepository agendamentoRepository;
    private final ProfessorRepository professorRepository;
    private final SalaRepository salaRepository;
    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ProfessorRepository professorRepository, SalaRepository salaRepository, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.professorRepository = professorRepository;
        this.salaRepository = salaRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    // Listar Todos
    public List<AgendamentoDTO> listarTodos(){
        List<AgendamentoModel> modelListarTodos = agendamentoRepository.findAll();
       return modelListarTodos.stream()
                .map(agendamentoMapper::map)
               .collect(Collectors.toList());
    }

    // Listar Por Id
    public Optional<AgendamentoDTO> listarPorId(Long id){
        Optional<AgendamentoModel> modelListaId = agendamentoRepository.findById(id);
       return modelListaId.map(agendamentoMapper::map);
    }

    // Salvar
    public AgendamentoDTO salvar (AgendamentoDTO dto){
       // Buscar Professor
        ProfessorModel professorModel = professorRepository.findById(dto.getIdProfessor())
                .orElseThrow(()-> new RuntimeException("Professor não encontrado"));

        // Buscar Sala
        SalaModel salaModel = salaRepository.findById(dto.getIdSala())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        // Mapear DTO para Model
        AgendamentoModel model = agendamentoMapper.map(dto, professorModel, salaModel);

        // Salvar
        AgendamentoModel salvar = agendamentoRepository.save(model);

        // Retorno DTO
        return agendamentoMapper.map(salvar);
    }

    // Atualizar
    public Optional<AgendamentoDTO> atualizar(Long id, AgendamentoDTO dtoAtualizado){
      return agendamentoRepository.findById(id)
              .map(agendamento ->{

                ProfessorModel professor = professorRepository.findById(dtoAtualizado.getIdProfessor())
                            .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

                SalaModel sala = salaRepository.findById(dtoAtualizado.getIdSala())
                        .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

                    // Atualiza entidade
                    agendamento.setProfessorModel(professor);
                    agendamento.setSalaModel(sala);
                    agendamento.setHorario(dtoAtualizado.getHorario());

                  // Salvar Atualizado
                  AgendamentoModel salvo = agendamentoRepository.save(agendamento);

                  return agendamentoMapper.map(salvo);

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
