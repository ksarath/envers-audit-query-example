package com.envers;

import com.envers.domain.vo.OrganisationVO;
import com.envers.domain.vo.UserOrgVO;
import com.envers.domain.vo.UserVO;
import com.envers.repository.OrganisationRepository;
import com.envers.repository.UserRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class AuditQueryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrganisationRepository orgRepo;

    @Autowired
    private UserRepository userRepo;

    @After
    public void after() {

    }

    @Test
    @Transactional
    public void auditQueryTest() {
        List<UserOrgVO> userOrgs = new ArrayList<>();
        OrganisationVO org = new OrganisationVO(1L, "org-1");
        UserVO user = new UserVO(1L, "user-1", userOrgs);
        userOrgs.add(new UserOrgVO(1L, user, org, false));

        orgRepo.save(org);
        userRepo.save(user);

        UserVO retrievedUser = userRepo.findOne(1L);
        assertEquals(retrievedUser, user);

        AuditReader reader = AuditReaderFactory.get(entityManager);
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(UserVO.class, true, true);
        query.add(AuditEntity.id().eq(1L));
        List revisions = query.getResultList();
        for(Object rev : revisions) {
            UserVO userRev = (UserVO) rev;
            assertEquals(userRev.getId(), new Long(1L));
            assertEquals(userRev.getName(), "user-1");

            for(UserOrgVO userOrgRev : userRev.getOrganisations()) {
                assertEquals(userOrgRev.getId(), new Long(1L));
                assertEquals(userOrgRev.getUser().getId(), new Long(1L));

                OrganisationVO orgRev = userOrgRev.getOrganisation();
                assertEquals(orgRev.getId(), new Long(1L));
                assertEquals(orgRev.getName(), "org-1");
            }
        }
    }
}
