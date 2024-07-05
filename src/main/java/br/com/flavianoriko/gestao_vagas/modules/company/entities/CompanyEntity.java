package br.com.flavianoriko.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity (name = "Company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    //private String cnpj;
    private String username;
    
    @Email(message="Email invalido!")
    private String email;
    
    private String password;
    private String website;
    private String description;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    

}
