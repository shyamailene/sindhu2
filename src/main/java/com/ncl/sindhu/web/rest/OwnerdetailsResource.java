package com.ncl.sindhu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ncl.sindhu.domain.Ownerdetails;

import com.ncl.sindhu.repository.OwnerdetailsRepository;
import com.ncl.sindhu.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Ownerdetails.
 */
@RestController
@RequestMapping("/api")
public class OwnerdetailsResource {

    private final Logger log = LoggerFactory.getLogger(OwnerdetailsResource.class);

    private static final String ENTITY_NAME = "ownerdetails";

    private final OwnerdetailsRepository ownerdetailsRepository;

    public OwnerdetailsResource(OwnerdetailsRepository ownerdetailsRepository) {
        this.ownerdetailsRepository = ownerdetailsRepository;
    }

    /**
     * POST  /ownerdetails : Create a new ownerdetails.
     *
     * @param ownerdetails the ownerdetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ownerdetails, or with status 400 (Bad Request) if the ownerdetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ownerdetails")
    @Timed
    public ResponseEntity<Ownerdetails> createOwnerdetails(@Valid @RequestBody Ownerdetails ownerdetails) throws URISyntaxException {
        log.debug("REST request to save Ownerdetails : {}", ownerdetails);
        if (ownerdetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ownerdetails cannot already have an ID")).body(null);
        }
        Ownerdetails result = ownerdetailsRepository.save(ownerdetails);
        return ResponseEntity.created(new URI("/api/ownerdetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ownerdetails : Updates an existing ownerdetails.
     *
     * @param ownerdetails the ownerdetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ownerdetails,
     * or with status 400 (Bad Request) if the ownerdetails is not valid,
     * or with status 500 (Internal Server Error) if the ownerdetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ownerdetails")
    @Timed
    public ResponseEntity<Ownerdetails> updateOwnerdetails(@Valid @RequestBody Ownerdetails ownerdetails) throws URISyntaxException {
        log.debug("REST request to update Ownerdetails : {}", ownerdetails);
        if (ownerdetails.getId() == null) {
            return createOwnerdetails(ownerdetails);
        }
        Ownerdetails result = ownerdetailsRepository.save(ownerdetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ownerdetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ownerdetails : get all the ownerdetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ownerdetails in body
     */
    @GetMapping("/ownerdetails")
    @Timed
    public List<Ownerdetails> getAllOwnerdetails() {
        log.debug("REST request to get all Ownerdetails");
        return ownerdetailsRepository.findAll();
    }

    /**
     * GET  /ownerdetails/:id : get the "id" ownerdetails.
     *
     * @param id the id of the ownerdetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ownerdetails, or with status 404 (Not Found)
     */
    @GetMapping("/ownerdetails/{id}")
    @Timed
    public ResponseEntity<Ownerdetails> getOwnerdetails(@PathVariable Long id) {
        log.debug("REST request to get Ownerdetails : {}", id);
        Ownerdetails ownerdetails = ownerdetailsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ownerdetails));
    }

    /**
     * DELETE  /ownerdetails/:id : delete the "id" ownerdetails.
     *
     * @param id the id of the ownerdetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ownerdetails/{id}")
    @Timed
    public ResponseEntity<Void> deleteOwnerdetails(@PathVariable Long id) {
        log.debug("REST request to delete Ownerdetails : {}", id);
        ownerdetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
