package com.grupoBL8.Reservatus.Professor.Controller;


import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import com.grupoBL8.Reservatus.Professor.Service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
// Dependencia
    public final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    //Listar Todos os Professores
    @GetMapping("/listar")
    public ResponseEntity<List<ProfessorModel>> listarTodos(){
        return ResponseEntity.ok(professorService.listarTodos());
    }

    // Listar professor por ID
    @GetMapping("/listarId/{id}")
    public ResponseEntity<ProfessorModel> listarPorId(@PathVariable Long id){
       return professorService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar
    @PostMapping("/salvar")
    public ResponseEntity<ProfessorModel> salvarProfessor(@RequestBody ProfessorModel professorModel){
        return ResponseEntity.ok(professorService.salvar(professorModel));
    }

    // Atualizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProfessorModel> atualiziarProfessor(@PathVariable Long id,@RequestBody ProfessorModel professorModel){
        return professorService.atualizarProfessor(id, professorModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        if(professorService.deletarProfessor(id)){
            return ResponseEntity.ok("Professor de id " + id + " deletado com sucesso");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
