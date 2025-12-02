package com.grupoBL8.Reservatus.Agendamento.Controller;

import com.grupoBL8.Reservatus.Agendamento.AgendamentoDTO;
import com.grupoBL8.Reservatus.Agendamento.Model.AgendamentoModel;
import com.grupoBL8.Reservatus.Agendamento.Service.AgendamentoService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<AgendamentoDTO>> listarTodos(){
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    // Listar Por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<AgendamentoDTO> listarPorId(@PathVariable Long id){
        return agendamentoService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar
    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody AgendamentoDTO dto) {
        try {
            AgendamentoDTO salvo = agendamentoService.salvar(dto);
            return ResponseEntity.ok(salvo);
        }
        catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage()); // <-- aqui volta só a mensagem
        }
    }


    // Atualizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AgendamentoDTO> atualizar(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO){
        return agendamentoService.atualizar(id, agendamentoDTO)
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
