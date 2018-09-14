package com.envers.repository;

import com.envers.domain.vo.UserVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserVO, Long> {}
