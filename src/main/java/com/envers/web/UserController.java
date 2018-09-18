package com.envers.web;

import com.envers.domain.vo.UserOrgVO;
import com.envers.domain.vo.UserVO;
import com.envers.service.UserService;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserVO> findAllUsers() {
        logger.info("Find all users");
        return this.userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public UserVO findById(@PathVariable Long id) {
        logger.info(String.format("Finding by id: %s", id));
        return this.userService.findByDetailPk(id);
    }

    @PostMapping
    public UserVO saveUser(@RequestBody UserVO userVO) {
        Validate.notNull(userVO, "The user cannot be null");
        for(UserOrgVO userOrg: userVO.getOrganisations()) {
            userOrg.setUser(userVO);
        }
        logger.info(String.format("Saving user: %s", userVO.toString()));
        return this.userService.save(userVO);
    }

    @PutMapping
    public UserVO updateUser(@RequestBody UserVO userVO) {
        Validate.notNull(userVO, "The user cannot be null");
        logger.info(String.format("Updating user: %s", userVO.toString()));
        return this.userService.update(userVO);
    }

    @DeleteMapping(path = "/{id}")
    public Long deleteProduct(@PathVariable Long id) {
        logger.info(String.format("Deleting user: %s", id));
        return this.userService.deleteById(id);
    }

    @GetMapping(path = "/{id}/revisions")
    public List<UserVO> findUserRevisions(@PathVariable Long id) {
        logger.info(String.format("Finding user revisions by id: %s", id));
        return this.userService.findAllRevisions(id);
    }
}
