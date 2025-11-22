package com.grupoBL8.Reservatus.Sala;

import com.grupoBL8.Reservatus.Sala.Model.SalaModel;
import org.springframework.stereotype.Component;

@Component
public class SalaMapper {

    public SalaModel map(SalaDTO salaDTO){
    SalaModel salaModel = new SalaModel();
    salaModel.setId(salaDTO.getId());
    salaModel.setNome(salaDTO.getNome());

      return salaModel;
    }

    public SalaDTO map(SalaModel salaModel){
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(salaModel.getId());
        salaDTO.setNome(salaModel.getNome());

        return salaDTO;
    }
}
