package com.target.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import com.target.model.ProductDetails;

public interface ProductRepository extends PagingAndSortingRepository<ProductDetails, String>, QueryDslPredicateExecutor<ProductDetails> {

	@RestResource(rel = "by-id")
	Page<ProductDetails> findById(String itemCode);
}
