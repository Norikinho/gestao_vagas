package br.com.flavianoriko.gestao_vagas.modules.company.dto;

import lombok.Data;
//protegendo os dados da entidade
@Data
public class CreateJobDTO {

    private String title;

    private String description;

    private String level;

    private String benefits;

}
