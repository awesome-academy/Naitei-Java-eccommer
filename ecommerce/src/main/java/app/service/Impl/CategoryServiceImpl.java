package app.service.Impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Category;
import app.repository.CategoryRepository;
import app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		try {
			return categoryRepository.findAll();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

}
