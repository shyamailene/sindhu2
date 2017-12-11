package com.ncl.sindhu.repository;

import com.ncl.sindhu.domain.Trac;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Trac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TracRepository extends JpaRepository<Trac,Long> {

    @Query("select trac from Trac trac where trac.user.login = ?#{principal.username}")
    List<Trac> findByUserIsCurrentUser();
    
}
