package br.com.flavianoriko.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.flavianoriko.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.flavianoriko.gestao_vagas.modules.candidate.useCases.ListAllJobsFilterUserCase;
import br.com.flavianoriko.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.flavianoriko.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.flavianoriko.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
   
   @Autowired
   private ProfileCandidateUseCase profileCandidateUseCase;

   @Autowired 
   private CreateCandidateUseCase createCandidateUseCase;

   @Autowired
   private ListAllJobsFilterUserCase listAllJobsFilterUserCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
      try{
         var result =  this.createCandidateUseCase.execute(candidateEntity);
         return ResponseEntity.ok().body(result);
      }
      catch(Exception e)
      {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
      //return this.candidateRepository.saveAndFlush(candidateEntity);
    }

    @GetMapping("/consulta")
    public String getCandidate(@RequestParam Map<String, String> allParams) {
       System.out.println("Candidato");
       return "Teste" +allParams.entrySet();
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('Candidate')")
    @Tag(name = "Candidato", description = "Informacoes do candidato") /*explicacoes no swagger, titulo.*/
    @Operation(summary =  "Listagem de vagas disponivels", description = "lista de vagas")
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
         @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
      })
    })
    public List<JobEntity> findJobByFilter(@RequestParam String filter)
    {
      return this.listAllJobsFilterUserCase.execute(filter);
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request){
      var idCandidate = request.getAttribute("candidate_id");
      try{
         var profile = this.profileCandidateUseCase
         .execute(UUID.fromString(idCandidate.toString()));
         return ResponseEntity.ok().body(profile);
      }
      catch(Exception e)
      {
         return ResponseEntity.badRequest().body(e.getMessage());
      }

    }
    
}
