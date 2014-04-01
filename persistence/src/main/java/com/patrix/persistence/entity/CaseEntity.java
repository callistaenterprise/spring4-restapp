package com.patrix.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Example case entity.
 */
@Data
@Entity(name = "case")
public class CaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "case_abstract", length = 2048)
    private String caseAbstract;

    @Column(name = "case_catch_word", length = 32)
    private String caseCatchWord;

    @Column(name = "case_number", length = 24)
    private String caseNumber;

    @Column(name = "case_number_extension", length = 16)
    private String caseNumberExtension;

    @Column(name = "case_location", length = 128)
    private String caseLocation;

    @Column(name = "closed_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date closedDate;

    @Column(name = "order_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "last_edit_ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEditTimestamp;

    @OneToMany(mappedBy = "caseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaseReferenceEntity> caseReferenceEntities = new LinkedList<CaseReferenceEntity>();

    @PrePersist
    void onPrePerist() {
        setOrderDate(new Date());
    }

    @PreUpdate
    void onPreUpdate() {
        setLastEditTimestamp(new Date());
    }

    //
    public boolean addCaseReferenceEntity(final CaseReferenceEntity caseReferenceEntity) {
        if (!caseReferenceEntities.contains(caseReferenceEntity)) {
            caseReferenceEntity.setCaseEntity(this);
            return caseReferenceEntities.add(caseReferenceEntity);
        }
        return false;
    }

    //
    public boolean removeCaseReferenceEntity(final CaseReferenceEntity caseReferenceEntity) {
        if (caseReferenceEntities.contains(caseReferenceEntity)) {
            caseReferenceEntity.setCaseEntity(null);
            return caseReferenceEntities.remove(caseReferenceEntity);
        }
        return false;
    }
}
