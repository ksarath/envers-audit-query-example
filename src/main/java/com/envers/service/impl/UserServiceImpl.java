package com.envers.service.impl;

import com.envers.domain.vo.OrganisationVO;
import com.envers.domain.vo.UserOrgVO;
import com.envers.domain.vo.UserVO;
import com.envers.repository.UserRepository;
import com.envers.service.UserService;
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
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserVO> findAll() {
        List<UserVO> users = new ArrayList();
        CollectionUtils.addAll(users, this.userRepository.findAll().iterator());
        return Collections.unmodifiableList(users);
    }

    @Override
    public UserVO findByDetailPk(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public UserVO save(UserVO userVO) {
        return this.userRepository.save(userVO);
    }

    @Override
    public UserVO update(UserVO userVO) {
        return this.userRepository.save(userVO);
    }

    @Override
    public Long deleteById(Long id) {
        this.userRepository.delete(id);
        return id;
    }

    @Override
    public List<UserVO> findAllRevisions(Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(UserVO.class, true, true);
        query.add(AuditEntity.id().eq(id));
        List revisions = query.getResultList();

        List<UserVO> users = new ArrayList<>();
        for(Object rev : revisions) {
            UserVO userRev = (UserVO) rev;
            List<UserOrgVO> userOrgs = new ArrayList<>();
            for(UserOrgVO userOrgRev : userRev.getOrganisations()) {
                OrganisationVO orgRev = userOrgRev.getOrganisation();
                OrganisationVO orgVo = new OrganisationVO(orgRev.getId(), orgRev.getName());
                UserOrgVO userOrgVo = new UserOrgVO(userOrgRev.getId(), null, orgVo, userOrgRev.isPreferred());
                userOrgs.add(userOrgVo);
            }

            UserVO userVo = new UserVO(userRev.getId(), userRev.getName(), userOrgs);
//            for(UserOrgVO userOrgVo : userVo.getOrganisations()) {
//                userOrgVo.setUser(userVo);
//            }
            users.add(userVo);
        }

        return users;
    }
}
