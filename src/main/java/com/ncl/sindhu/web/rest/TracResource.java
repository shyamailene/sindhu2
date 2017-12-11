package com.ncl.sindhu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ncl.sindhu.domain.Trac;

import com.ncl.sindhu.domain.User;
import com.ncl.sindhu.repository.TracRepository;
import com.ncl.sindhu.repository.UserRepository;
import com.ncl.sindhu.security.SecurityUtils;
import com.ncl.sindhu.service.MailService;
import com.ncl.sindhu.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing Trac.
 */
@RestController
@RequestMapping("/api")
public class TracResource {

    private final Logger log = LoggerFactory.getLogger(TracResource.class);

    private static final String ENTITY_NAME = "trac";

    private final TracRepository tracRepository;
	
	@Autowired
    private MailService mailService;	
	
	private final UserRepository userRepository;

    public TracResource(TracRepository tracRepository, UserRepository userRepository) {
        this.tracRepository = tracRepository;
		this.userRepository=userRepository;
    }

    /**
     * POST  /tracs : Create a new trac.
     *
     * @param trac the trac to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trac, or with status 400 (Bad Request) if the trac has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tracs")
    @Timed
    public ResponseEntity<Trac> createTrac(@Valid @RequestBody Trac trac) throws URISyntaxException {
        log.debug("REST request to save Trac : {}", trac);
        if (trac.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new trac cannot already have an ID")).body(null);
        }
		String userid= SecurityUtils.getCurrentUserLogin();
        User user=userRepository.findOneByLogin(userid).get();
		trac.setUser(user);
        Trac result = tracRepository.save(trac);
        mailService.sendEmail(user,trac);		
        return ResponseEntity.created(new URI("/api/tracs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tracs : Updates an existing trac.
     *
     * @param trac the trac to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trac,
     * or with status 400 (Bad Request) if the trac is not valid,
     * or with status 500 (Internal Server Error) if the trac couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tracs")
    @Timed
    public ResponseEntity<Trac> updateTrac(@Valid @RequestBody Trac trac) throws URISyntaxException {
        log.debug("REST request to update Trac : {}", trac);
        if (trac.getId() == null) {
            return createTrac(trac);
        }
        Trac result = tracRepository.save(trac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trac.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tracs : get all the tracs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tracs in body
     */
    @GetMapping("/tracs")
    @Timed
    public List<Trac> getAllTracs() {
        log.debug("REST request to get all Tracs");
        return tracRepository.findByUserIsCurrentUser();
    }

    /**
     * GET  /tracs/:id : get the "id" trac.
     *
     * @param id the id of the trac to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trac, or with status 404 (Not Found)
     */
    @GetMapping("/tracs/{id}")
    @Timed
    public ResponseEntity<Trac> getTrac(@PathVariable Long id) {
        log.debug("REST request to get Trac : {}", id);
        Trac trac = tracRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(trac));
    }

    /**
     * DELETE  /tracs/:id : delete the "id" trac.
     *
     * @param id the id of the trac to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tracs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrac(@PathVariable Long id) {
        log.debug("REST request to delete Trac : {}", id);
        tracRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
