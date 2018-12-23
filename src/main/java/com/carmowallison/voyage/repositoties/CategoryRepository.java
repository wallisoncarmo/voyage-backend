package com.carmowallison.voyage.repositoties;


import com.carmowallison.voyage.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByIdIn(List<Integer> ids);
}
