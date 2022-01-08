package com.hgun.sti.controller.validators;

import com.hgun.sti.models.Paciente;
import com.hgun.sti.models.erros.PacienteError;

public class PacienteValidator {
    public static PacienteError validarPaciente(Paciente paciente){

        var pacienteError = new PacienteError();

        //nome
        if(paciente.getNome() == null || paciente.getNome().isEmpty()){
            pacienteError.setNome("O nome não pode estar vazio!");
        }else if(paciente.getNome().length() < 4){
            pacienteError.setNome("O nome do paciente está muito curto! (min: 4 letras)");
        }else if(!paciente.getNome().matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}")){
            pacienteError.setNome("O nome do paciente deve conter apenas letras e espaços");
        }

        //preccp
        if(paciente.preccp != null && !paciente.preccp.isEmpty() && !paciente.preccp.equals("") ){
            if(!paciente.getPreccp().matches("^[\\$]?[-+]?[\\d\\.,]*[\\.,]?\\d+$")){
                pacienteError.setPreccp("O PRECCP deve conter apenas números!");
            }
        }

        //idade
        pacienteError.setIdade(validaIdade(paciente.getIdade()));

        //sexo
        if(paciente.getSexo() == null || paciente.getSexo().equals("") || paciente.getSexo().equals(" ") ){
            pacienteError.setSexo("O sexo é inválido!");
        }

        //prontuario
        if(paciente.prontuario != null && !paciente.prontuario.isEmpty() && !paciente.prontuario.equals("") ){
            if(!paciente.getPreccp().matches("^[\\$]?[-+]?[\\d\\.,]*[\\.,]?\\d+$")){
                pacienteError.setPreccp("O prontuário deve conter apenas números!");
            }
        }

        //especialidade
        if(paciente.getTipoEspecialidade() == null){
            pacienteError.setTipoEspecialidade("O tipo de especialidade não pode ser vazio!");
        }

        //falta validar o telefone;

        return pacienteError;
    }

    public static String validaIdade(String idade){
        if(idade == null || idade.isEmpty()){
            return "A idade não pode ser vazia!";
        }

        var anos = Integer.parseInt(idade);

        if(anos < 0){
            return "A idade é inválida!";
        }

        return null;
    }
}
