package com.envers.service;

import com.envers.domain.vo.UserVO;

import java.util.List;

public interface UserService {
    /**
     * Retrieves all users from database
     *
     * @return
     */
    List<UserVO> findAll();

    /**
     * Finds the user by id
     *
     * @param id
     * @return
     */
    UserVO findByDetailPk(Long id);

    /**
     * Adds a new user to the database
     *
     * @param userVO
     * @return
     */
    UserVO save(UserVO userVO);

    /**
     * Updates a user to the database
     *
     * @param userVO
     * @return
     */
    UserVO update(UserVO userVO);

    /**
     * Deletes a user by id from database
     *
     * @param id
     * @return
     */
    Long deleteById(Long id);

    /**
     * Retrieves all versions of a user
     *
     * @param id
     * @return
     */
    List<UserVO> findAllRevisions(Long id);
}
