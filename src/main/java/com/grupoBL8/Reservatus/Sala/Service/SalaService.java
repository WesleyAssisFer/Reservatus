package com.grupoBL8.Reservatus.Sala.Service;

import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import com.grupoBL8.Reservatus.Sala.Repository.SalaRepository;
import com.grupoBL8.Reservatus.Sala.SalaDTO;
import com.grupoBL8.Reservatus.Sala.SalaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaService {
    // Dependencia
    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;

    public SalaService(SalaRepository salaRepository, SalaMapper salaMapper){
        this.salaRepository = salaRepository;
        this.salaMapper = salaMapper;
    }

    // Listar Todos
    public List<SalaDTO> listarTodos(){
        List<SalaModel> modelListarTodos = salaRepository.findAll();
        return modelListarTodos.stream()
                .map(salaMapper::map)
                .collect(Collectors.toList());
    }

    // Listar por Id
    public Optional<SalaDTO> listarPorId(Long id){
        Optional<SalaModel> modelListarId = salaRepository.findById(id);
        return modelListarId.map(salaMapper::map);
    }

    // Salvar
    public SalaDTO salvar(SalaDTO salaDTO){
        SalaModel model = salaMapper.map(salaDTO);
       SalaModel salvarModel = salaRepository.save(model);
        return salaMapper.map(salvarModel);
    }

    // Atualizar
    public Optional<SalaDTO> atualizar( Long id,SalaDTO salaDTO){
        return salaRepository.findById(id)
                .map(sala -> {
                    sala.setNome(salaDTO.getNome());
                    SalaModel salvarModel = salaRepository.save(sala);
                    return salaMapper.map(salvarModel);
                });
    }

    // Deletar
    public boolean deletar(Long id){
        if(salaRepository.existsById(id)) {
            salaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }


}
