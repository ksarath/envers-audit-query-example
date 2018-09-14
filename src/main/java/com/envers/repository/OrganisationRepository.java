package com.envers.repository;

import com.envers.domain.vo.OrganisationVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends CrudRepository<OrganisationVO, Long> {}
