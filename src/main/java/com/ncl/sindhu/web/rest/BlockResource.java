package com.ncl.sindhu.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ncl.sindhu.domain.Block;

import com.ncl.sindhu.repository.BlockRepository;
import com.ncl.sindhu.web.rest.util.HeaderUtil;
import com.ncl.sindhu.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Block.
 */
@RestController
@RequestMapping("/api")
public class BlockResource {

    private final Logger log = LoggerFactory.getLogger(BlockResource.class);

    private static final String ENTITY_NAME = "block";

    private final BlockRepository blockRepository;

    public BlockResource(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    /**
     * POST  /blocks : Create a new block.
     *
     * @param block the block to create
     * @return the ResponseEntity with status 201 (Created) and with body the new block, or with status 400 (Bad Request) if the block has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/blocks")
    @Timed
    public ResponseEntity<Block> createBlock(@Valid @RequestBody Block block) throws URISyntaxException {
        log.debug("REST request to save Block : {}", block);
        if (block.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new block cannot already have an ID")).body(null);
        }
        Block result = blockRepository.save(block);
        return ResponseEntity.created(new URI("/api/blocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /blocks : Updates an existing block.
     *
     * @param block the block to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated block,
     * or with status 400 (Bad Request) if the block is not valid,
     * or with status 500 (Internal Server Error) if the block couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/blocks")
    @Timed
    public ResponseEntity<Block> updateBlock(@Valid @RequestBody Block block) throws URISyntaxException {
        log.debug("REST request to update Block : {}", block);
        if (block.getId() == null) {
            return createBlock(block);
        }
        Block result = blockRepository.save(block);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, block.getId().toString()))
            .body(result);
    }

    /**
     * GET  /blocks : get all the blocks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of blocks in body
     */
    @GetMapping("/blocks")
    @Timed
    public ResponseEntity<List<Block>> getAllBlocks(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Blocks");
        Page<Block> page = blockRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blocks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /blocks/:id : get the "id" block.
     *
     * @param id the id of the block to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the block, or with status 404 (Not Found)
     */
    @GetMapping("/blocks/{id}")
    @Timed
    public ResponseEntity<Block> getBlock(@PathVariable Long id) {
        log.debug("REST request to get Block : {}", id);
        Block block = blockRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(block));
    }

    /**
     * DELETE  /blocks/:id : delete the "id" block.
     *
     * @param id the id of the block to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/blocks/{id}")
    @Timed
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id) {
        log.debug("REST request to delete Block : {}", id);
        blockRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
