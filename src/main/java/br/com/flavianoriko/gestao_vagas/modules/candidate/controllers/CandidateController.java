package br.com.flavianoriko.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.flavianoriko.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.flavianoriko.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.flavianoriko.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/")
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
