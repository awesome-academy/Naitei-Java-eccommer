package app.repository;


import app.model.Product;

public interface ProductRepository extends BaseRepository<Long, Product>{

	Product findById(Long id);
 
}