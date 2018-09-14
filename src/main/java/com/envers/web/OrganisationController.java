package com.envers.web;

import com.envers.domain.vo.OrganisationVO;
import com.envers.service.OrganisationService;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/organisations")
public class OrganisationController {

    private static final Logger logger = LoggerFactory.getLogger(OrganisationController.class);

    private final OrganisationService orgService;

    @Autowired
    public OrganisationController(OrganisationService orgService) {
        this.orgService = orgService;
    }

    @GetMapping
    public List<OrganisationVO> findAllUsers() {
        logger.info("Find all organisations");
        return this.orgService.findAll();
    }

    @GetMapping(path = "/{id}")
    public OrganisationVO findById(@PathVariable Long id) {
        logger.info(String.format("Finding by id: %s", id));
        return this.orgService.findByDetailPk(id);
    }

    @PostMapping
    public OrganisationVO saveUser(@RequestBody OrganisationVO orgVO) {
        Validate.notNull(orgVO, "The user cannot be null");
        logger.info(String.format("Saving user: %s", orgVO.toString()));
        return this.orgService.save(orgVO);
    }

    @PutMapping
    public OrganisationVO updateUser(@RequestBody OrganisationVO orgVO) {
        Validate.notNull(orgVO, "The user cannot be null");
        logger.info(String.format("Updating user: %s", orgVO.toString()));
        return this.orgService.update(orgVO);
    }

    @DeleteMapping(path = "/{id}")
    public Long deleteProduct(@PathVariable Long id) {
        logger.info(String.format("Deleting user: %s", id));
        return this.orgService.deleteById(id);
    }

    @GetMapping(path = "/{id}/revisions")
    public List<OrganisationVO> findUserRevisions(@PathVariable Long id) {
        logger.info(String.format("Finding user revisions by id: %s", id));
        return this.orgService.findAllRevisions(id);
    }
}
