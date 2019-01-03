package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CategorieActivite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategorieActivite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieActiviteRepository extends JpaRepository<CategorieActivite, Long> {

}
