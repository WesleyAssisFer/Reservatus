package com.grupoBL8.Reservatus.Sala.Service;

import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import com.grupoBL8.Reservatus.Sala.Repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    // Dependencia
    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository){
        this.salaRepository = salaRepository;
    }

    // Listar Todos
    public List<SalaModel> listarTodos(){
        return salaRepository.findAll();
    }

    // Listar por Id
    public Optional<SalaModel> listarPorId(Long id){
        return salaRepository.findById(id);
    }

    // Salvar
    public SalaModel salvar(SalaModel salaModel){
        return salaRepository.save(salaModel);
    }

    // Atualizar
    public Optional<SalaModel> atualizar( Long id,SalaModel salaAtualizada){
        return salaRepository.findById(id)
                .map(sala -> {
                    sala.setNome(salaAtualizada.getNome());
                    return salaRepository.save(sala);
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
