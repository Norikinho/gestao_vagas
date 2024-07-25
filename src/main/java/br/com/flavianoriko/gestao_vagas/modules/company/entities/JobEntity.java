package br.com.flavianoriko.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name="job")
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    private String title;

    private String description;

    private String level;

    private String benefits;


    
    @ManyToOne()
    @JoinColumn(name = "company_id", nullable=false, insertable=false, updatable=false)
    private CompanyEntity companyEntity;

    @Column(name="company_id")
    private UUID company_id;


    @CreationTimestamp
    private LocalDateTime createdAt;
    
}