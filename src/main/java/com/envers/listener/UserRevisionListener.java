/**
 * This class sets the userId attribute in the Audit table
 */
package com.envers.listener;

import com.envers.domain.AuditEnversInfo;
import org.hibernate.envers.RevisionListener;

import java.util.UUID;

public class UserRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;
        auditEnversInfo.setUserId(UUID.randomUUID().toString());
    }

}
