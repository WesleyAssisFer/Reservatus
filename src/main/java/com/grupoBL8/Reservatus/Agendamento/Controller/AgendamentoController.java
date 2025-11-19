package com.grupoBL8.Reservatus.Agendamento.Controller;

import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import com.grupoBL8.Reservatus.Agendamento.Service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    // Dependecia
    private final AgendamentoService agendamentoService;
    public AgendamentoController(AgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }

    // Listar Todos
    @GetMapping("/listar")
    public ResponseEntity<List<AgendamentoModel>> listarTodos(){
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    // Listar Por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<AgendamentoModel> listarPorId(@PathVariable Long id){
        return agendamentoService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar
    @PostMapping("/salvar")
    public ResponseEntity<AgendamentoModel> salvar(@RequestBody AgendamentoModel agendamentoModel){
        return ResponseEntity.ok(agendamentoService.salvar(agendamentoModel));
    }

    // Atualizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AgendamentoModel> atualizar(@PathVariable Long id, @RequestBody AgendamentoModel agendamentoModel){
        return agendamentoService.atualizar(id, agendamentoModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        if(agendamentoService.deletar(id)){
            return ResponseEntity.ok("Agendamento de Id " + id + " deletado com sucesso");
        }else{
            return ResponseEntity.status(404).body("Agendamento de Id " + id + " nao encontrado");
        }
    }
}
