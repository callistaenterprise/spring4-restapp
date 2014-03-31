package com.patrix.persistence.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Example case reference entity.
 */
@Data
@Entity(name = "case_reference")
public class CaseReferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "type_name", length = 128)
    private String typeName;

    @Column(name = "type_label", length = 64)
    private String typeLabel;

    @Column(name = "text", length = 2048)
    private String text;

    @ManyToOne
    @JoinColumn(name="case_id")
    private CaseEntity caseEntity;
}
