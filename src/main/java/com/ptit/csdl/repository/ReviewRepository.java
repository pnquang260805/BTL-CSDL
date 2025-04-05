package com.ptit.csdl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.csdl.entity.ProductReview;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview, Long> {

}
