package com.carmowallison.voyage.repositoties;


import com.carmowallison.voyage.domain.Category;
import com.carmowallison.voyage.domain.Photograph;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhotographRepository extends JpaRepository<Photograph, Integer> {
    List<Photograph> findByTitleContaining(String title);

    List<Photograph> findByTitleLike(String title);

    Page<Photograph> findByTitleContainingAndCategoryIn(String title, List<Category> ids, Pageable pageRequest);

    Page<Photograph> findByTitleContaining(String title, Pageable pageRequest);
}
