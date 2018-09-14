package com.envers.service;

import com.envers.domain.vo.OrganisationVO;

import java.util.List;

public interface OrganisationService {
    /**
     * Retrieves all organisations from database
     *
     * @return
     */
    List<OrganisationVO> findAll();

    /**
     * Finds the organisation by id
     *
     * @param id
     * @return
     */
    OrganisationVO findByDetailPk(Long id);

    /**
     * Adds a new organisation to the database
     *
     * @param orgVO
     * @return
     */
    OrganisationVO save(OrganisationVO orgVO);

    /**
     * Updates a organisation to the database
     *
     * @param orgVO
     * @return
     */
    OrganisationVO update(OrganisationVO orgVO);

    /**
     * Deletes a organisation by id from database
     *
     * @param id
     * @return
     */
    Long deleteById(Long id);

    /**
     * Retrieves all versions of a organisation
     *
     * @param id
     * @return
     */
    List<OrganisationVO> findAllRevisions(Long id);
}
