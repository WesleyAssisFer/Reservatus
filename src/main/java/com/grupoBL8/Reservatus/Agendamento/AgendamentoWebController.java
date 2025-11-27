package com.grupoBL8.Reservatus.Agendamento;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/agendamento")

public class AgendamentoWebController {
@GetMapping("/calendario")
    public String calendario() {
        return "calendario"; // sem /
    }

}
