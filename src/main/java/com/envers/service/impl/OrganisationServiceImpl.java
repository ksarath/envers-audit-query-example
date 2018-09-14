package com.envers.service.impl;

import com.envers.domain.vo.OrganisationVO;
import com.envers.repository.OrganisationRepository;
import com.envers.service.OrganisationService;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {
    private static final Logger logger = LoggerFactory.getLogger(OrganisationServiceImpl.class);

    private final OrganisationRepository orgRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public OrganisationServiceImpl(OrganisationRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    @Override
    public List<OrganisationVO> findAll() {
        List<OrganisationVO> users = new ArrayList();
        CollectionUtils.addAll(users, this.orgRepository.findAll().iterator());
        return Collections.unmodifiableList(users);
    }

    @Override
    public OrganisationVO findByDetailPk(Long id) {
        return this.orgRepository.findOne(id);
    }

    @Override
    public OrganisationVO save(OrganisationVO userVO) {
        return this.orgRepository.save(userVO);
    }

    @Override
    public OrganisationVO update(OrganisationVO userVO) {
        return this.orgRepository.save(userVO);
    }

    @Override
    public Long deleteById(Long id) {
        this.orgRepository.delete(id);
        return id;
    }

    @Override
    public List<OrganisationVO> findAllRevisions(Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(OrganisationVO.class, true, true);
        query.add(AuditEntity.id().eq(id));
        List revisions = query.getResultList();

        List<OrganisationVO> organisations = new ArrayList<>();
        for(Object rev : revisions) {
            OrganisationVO orgRev = (OrganisationVO) rev;
            organisations.add(orgRev);
        }

        return organisations;
    }
}
