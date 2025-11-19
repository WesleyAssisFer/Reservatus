package com.grupoBL8.Reservatus.Sala.Controller;

import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import com.grupoBL8.Reservatus.Sala.Service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sala")
public class SalaController {
    // Dependencia
    private final SalaService salaService;
    public SalaController(SalaService salaService){
        this.salaService = salaService;
    }

    // Listar Todos
    @GetMapping("/listar")
    public ResponseEntity<List<SalaModel>> listarTodos(){
        return ResponseEntity.ok(salaService.listarTodos());
    }

    // Listar Por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<SalaModel> listarId(@PathVariable Long id){
        return salaService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar
    @PostMapping("/salvar")
    public ResponseEntity<SalaModel> salvar(@RequestBody SalaModel salaModel){
        return ResponseEntity.ok(salaService.salvar(salaModel));
    }

    // Atualizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SalaModel> atualizar(@PathVariable Long id, @RequestBody SalaModel salaModel){
            return salaService.atualizar(id, salaModel)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        if(salaService.deletar(id)){
            return ResponseEntity.ok("Sala de Id " + id + " deletado com sucesso");
        }else {
            return ResponseEntity.status(404).body("Id " + id + " não encontrado");
        }
    }
}
