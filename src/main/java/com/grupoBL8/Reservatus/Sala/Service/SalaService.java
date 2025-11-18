package com.grupoBL8.Reservatus.Sala.Service;

import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import com.grupoBL8.Reservatus.Sala.Repository.SalaRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    // Dependencia
    private final SalaRespository salaRespository;

    public SalaService(SalaRespository salaRespository){
        this.salaRespository = salaRespository;
    }

    // Listar Todos
    public List<SalaModel> listarTodos(){
        return salaRespository.findAll();
    }

    // Listar por Id
    public Optional<SalaModel> listarPorId(Long id){
        return salaRespository.findById(id);
    }

    // Salvar

}
