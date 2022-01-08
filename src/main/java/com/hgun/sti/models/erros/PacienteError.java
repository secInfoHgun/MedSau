package com.hgun.sti.models.erros;

import com.hgun.sti.models.TipoEspecialidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PacienteError {
    public String nome;//
    public String idade;//
    public String sexo;//
    public String telefone;
    public String preccp;//
    public String prontuario;//
    public String tipoEspecialidade;//

    public boolean isEmpty(){
        if(preccp != null && !preccp.isEmpty()){
            return false;
        }else if(nome != null && !nome.isEmpty()){
            return false;
        }else if(idade != null && !idade.isEmpty()){
            return false;
        }else if(sexo != null && !sexo.isEmpty()){
            return false;
        }else if(telefone != null && !telefone.isEmpty()){
            return false;
        }else if(prontuario != null && !prontuario.isEmpty()){
            return false;
        }else if(tipoEspecialidade != null && !tipoEspecialidade.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
