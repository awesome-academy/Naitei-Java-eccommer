package app.repository;

import java.util.List;

import app.model.Product;

public interface ProductRepository extends BaseRepository<Long, Product> {

	public Product findById(Long id);

	public List<Product> getTop6Products();

	public List<Product> getProductsAndReviews();

	public void updateProduct(Product updatedProduct);

}