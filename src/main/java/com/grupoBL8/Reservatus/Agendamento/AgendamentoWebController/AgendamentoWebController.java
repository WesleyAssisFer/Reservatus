package com.grupoBL8.Reservatus.Agendamento.Controller;

import com.grupoBL8.Reservatus.Agendamento.AgendamentoDTO;
import com.grupoBL8.Reservatus.Agendamento.Service.AgendamentoService;
import com.grupoBL8.Reservatus.Professor.Service.ProfessorService;
import com.grupoBL8.Reservatus.Sala.Service.SalaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/web/agendamento")
public class AgendamentoWebController {

    private final AgendamentoService agendamentoService;
    private final ProfessorService professorService;
    private final SalaService salaService;

    public AgendamentoWebController(AgendamentoService agendamentoService, ProfessorService professorService, SalaService salaService) {
        this.agendamentoService = agendamentoService;
        this.professorService = professorService;
        this.salaService = salaService;
    }

    @GetMapping("/calendario")
    public String calendario(
            @RequestParam Long salaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            Model model
    ) {

        List<AgendamentoDTO> agendamentos = agendamentoService.listarPorSalaEDia(salaId, data);

        model.addAttribute("agendamentos", agendamentos);
        model.addAttribute("sala", salaService.listarPorId(salaId).orElse(null));
        model.addAttribute("data", data);

        // horários padronizados
        List<LocalTime> horarios = List.of(
                LocalTime.of(7, 30),
                LocalTime.of(8, 15),
                LocalTime.of(9, 0),
                LocalTime.of(10, 10),
                LocalTime.of(10, 55),
                LocalTime.of(11, 40),
                LocalTime.of(13, 00),
                LocalTime.of(13, 45),
                LocalTime.of(14, 30),
                LocalTime.of(15, 30),
                LocalTime.of(16, 15),
                LocalTime.of(17, 00)

        );

        model.addAttribute("horarios", horarios);

        return "calendario";
    }

    @GetMapping("/form")
    public String form(@RequestParam Long salaId,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) java.time.LocalTime horario,
                       Model model) {

        model.addAttribute("professores", professorService.listarTodos());
        model.addAttribute("salaId", salaId);
        model.addAttribute("data", data);
        model.addAttribute("horario", horario);

        return "agendar";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam Long salaId,
            @RequestParam Long professorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) java.time.LocalTime horario
    ) {

        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setIdSala(salaId);
        dto.setIdProfessor(professorId);
        dto.setHorario(data.atTime(horario));

        agendamentoService.salvar(dto);

        return "redirect:/web/agendamento/calendario?salaId=" + salaId + "&data=" + data;
    }
}
