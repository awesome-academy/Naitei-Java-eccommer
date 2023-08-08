package app.service;

import java.util.List;

import app.model.Product;

public interface ProductService extends BaseService<Long, Product> {
	public List<Product> getTop6Products();
}

