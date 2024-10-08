package br.com.flavianoriko.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.flavianoriko.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.flavianoriko.gestao_vagas.modules.company.entities.JobEntity;
import br.com.flavianoriko.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(@RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        // jobEntity.setCompany_id(UUID.fromString(companyId.toString()));
        var jobEntity = JobEntity.builder()
                .level(createJobDTO.getLevel())
                .benefits(createJobDTO.getBenefits())
                .description(createJobDTO.getDescription())
                .title(createJobDTO.getTitle())
                .company_id(UUID.fromString(companyId.toString()))
                .build();

        return this.createJobUseCase.execute(jobEntity);
    }

}
