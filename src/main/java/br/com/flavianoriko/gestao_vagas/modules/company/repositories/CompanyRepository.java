package br.com.flavianoriko.gestao_vagas.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flavianoriko.gestao_vagas.modules.company.entities.CompanyEntity;

import java.util.UUID;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
 Optional<CompanyEntity>findByUsernameOrEmail(String email, String username);    
}
