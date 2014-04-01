package com.patrix.persistence.repository;

import com.patrix.persistence.config.AbstractPersistenceTestSupport;
import com.patrix.persistence.entity.CaseEntity;
import com.patrix.persistence.entity.CaseReferenceEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CaseRepositoryTest extends AbstractPersistenceTestSupport {
    @Autowired
    private CaseRepository caseRepository;


    //
    static CaseReferenceEntity createCaseReferenceEntity(final CaseEntity caseEntity, final String text) {

        final CaseReferenceEntity caseReferenceEntity = new CaseReferenceEntity();
        caseReferenceEntity.setText(text);
        caseReferenceEntity.setTypeLabel("TypeLabel");
        caseReferenceEntity.setTypeName("TypeName");

        caseEntity.addCaseReferenceEntity(caseReferenceEntity);

        return caseReferenceEntity;
    }

    //
    static CaseEntity createCaseEntity() {
        final CaseEntity caseEntity = new CaseEntity();
        caseEntity.setCaseAbstract("This is a short abstract");
        caseEntity.setCaseLocation("Stockholm Sweden");
        caseEntity.setCaseCatchWord("interesting");
        caseEntity.setCaseNumber("1234-567-89-0");
        caseEntity.setCaseNumberExtension("ABC");

        createCaseReferenceEntity(caseEntity, "reference A");
        createCaseReferenceEntity(caseEntity, "reference B");

        return caseEntity;

    }

    @Test
    public void thatInsertWorks() {

        final CaseEntity caseEntity = createCaseEntity();

        final CaseEntity savedCaseEntity = caseRepository.save(caseEntity);
        caseRepository.flush();
        assertNotNull(savedCaseEntity);
        assertNotNull(savedCaseEntity.getId());
        assertNotNull(savedCaseEntity.getCaseReferenceEntities());

        final List<CaseEntity> all = caseRepository.findAll();

        assertEquals(1, all.size());
        assertEquals(2, all.get(0).getCaseReferenceEntities().size());
    }

    @Test
    public void thatUpdateWorks() {
        final CaseEntity caseEntity = createCaseEntity();

        final CaseEntity savedCaseEntity = caseRepository.save(caseEntity);
        caseRepository.flush();

        savedCaseEntity.removeCaseReferenceEntity(savedCaseEntity.getCaseReferenceEntities().get(1));
        savedCaseEntity.setCaseNumber("12");

        caseRepository.save(savedCaseEntity);
        caseRepository.flush();

        final List<CaseEntity> all = caseRepository.findAll();
        final CaseEntity updatedCaseEntity = all.get(0);
        assertEquals(updatedCaseEntity.getCaseNumber(), "12");
        assertNotNull(updatedCaseEntity.getLastEditTimestamp());
        assertEquals(1, updatedCaseEntity.getCaseReferenceEntities().size());
    }
}
