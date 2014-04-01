package com.patrix.timeregistration.business.impl;

import com.patrix.persistence.entity.CaseEntity;
import com.patrix.persistence.entity.CaseReferenceEntity;
import com.patrix.persistence.repository.CaseRepository;
import com.patrix.timeregistration.business.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 2014-04-01.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Autowired
    private CaseRepository caseRepository;

    @Value("${testdata.numEntities:1000}")
    private int numEntities;

    @Override
    public long createTestData() {
        for (int i = 0; i < numEntities; i++) {
            caseRepository.save(createCaseEntity(i));
        }
        caseRepository.flush();
        return caseRepository.count();
    }

    @Override
    public long deleteTestData() {
        final long num = caseRepository.count();
        caseRepository.deleteAll();
        caseRepository.flush();
        return num;
    }

    private CaseEntity createCaseEntity(final int n) {
        final CaseEntity caseEntity = new CaseEntity();
        caseEntity.setCaseNumber("caseNumber-" + n);
        caseEntity.setCaseCatchWord("none");
        caseEntity.setCaseLocation("Sweden");
        caseEntity.setCaseAbstract("This is an extremely useless abstract");
        caseEntity.setCaseNumberExtension(String.valueOf((int)Math.random()*n));
        for (int i = 0; i < 10; i++) {
            final CaseReferenceEntity caseReferenceEntity = new CaseReferenceEntity();
            caseReferenceEntity.setTypeLabel("Type-" + i);
            caseReferenceEntity.setTypeName("TypeName");
            caseEntity.addCaseReferenceEntity(caseReferenceEntity);
        }
        return caseEntity;
    }
}
