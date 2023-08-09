package app.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Product;
import app.repository.ProductRepository;
import app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		try {
			return productRepository.findAll();
		} catch (Exception e) {
			logger.error(e);
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public Product findById(Long id) {
	
		try {
			return productRepository.findById(id);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Product> getTop6Products() {
		return productRepository.getTop6Products();
	}
	
}
