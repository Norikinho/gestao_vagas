package br.com.flavianoriko.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.flavianoriko.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.flavianoriko.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
   
   @Autowired 
   private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
      return this.candidateRepository.saveAndFlush(candidateEntity);
    }

    @GetMapping("/consulta")
    public String getCandidate(@RequestParam Map<String, String> allParams) {
       System.out.println("Candidato");
       return "Teste" +allParams.entrySet();
    }
    
}
