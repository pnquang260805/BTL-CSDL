package com.ptit.csdl.repository;

import java.util.List;

import com.ptit.csdl.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    public List<Category> findByTag(String tag);
}
