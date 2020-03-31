package com.simple.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

//	@Query(value =  " SELECT R1.* FROM( " + 
//			"				SELECT product.id as product_id, product.name as product_name, product.content, product.price, product.sale_price, product.image, product.created_at as product_created_at " + 
//			"				From product " + 
//			"				ORDER BY product.created_at " + 
//			"			)R1 " + 
//			"			LIMIT 6 OFFSET ?1", nativeQuery = true)
//	List<Product> findProducts(Long morePage);
	
	Page<Product> findAll(Pageable paging);
	
	
	
	
//	@Query(value = "	SELECT R1.* FROM( " + 
//			"					SELECT product.id as product_id, product.name as product_name, product.content, product.price, product.sale_price, product.image, product.created_at as product_created_at, user.id as user_id, user.email, user.name as user_name, user.birth, user.gender, user.created_at as user_created_at " + 
//			"					FROM product " + 
//			"					JOIN user ON user.id = ?1 " + 
//			"					ORDER BY product.created_at " + 
//			"	        )R1 " + 
//			"	        LIMIT 6 OFFSET ?2", nativeQuery = true)
//	List<Product> findProductsAndUser(Long userId, Long morePage);
	
//	Page<Product> findProductAndUserByUserId(Long userId, Pageable paging);
	
//	Page<Product> findProductAndBasketAndUserByUserId(Long userId, Pageable paging);
	
	
	
	
	Product findProductById(Long id);

	
	
//	@Query(value =  "		SELECT product.id as product_id, product.name as product_name, product.content,	product.price, product.sale_price, product.image, product.created_at as product_created_at, user.id as user_id, user.email, user.name as user_name, user.birth, user.gender, user.created_at as user_created_at " + 
//			"		FROM product " + 
//			"		JOIN user ON user.id = ?1 " + 
//			"		WHERE product.id = ?2 ", nativeQuery = true)
//	Product findProductAndUserByProductIdAndUserId(Long userId, Long productId);
	
//	Product findByUserIdAndId(Long userId, Long id);
	
	

//	@Query(value =  " 		SELECT product.id as product_id, product.name as product_name, product.content, product.price, product.sale_price, product.image, product.created_at as product_created_at, user.id as user_id, user.email, user.name as user_name, user.birth, user.gender, user.created_at as user_created_at " + 
//			"		FROM product " + 
//			"		JOIN user ON user.id = ?1 " + 
//			"		JOIN basket ON basket.user_id=user.id " + 
//			"		WHERE product.id = basket.product_id " + 
//			"		GROUP BY product.name " + 
//			"		ORDER BY basket.created_at", nativeQuery = true)
//	List<Product> findProductAndBasketByUserId(Long userId);
	
//	List<Product> findByUserIdOrderBy(Long userId);
	
	
	
}
