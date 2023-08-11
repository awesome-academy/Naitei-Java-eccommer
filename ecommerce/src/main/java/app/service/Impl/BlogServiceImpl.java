package app.service.Impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Blog;
import app.repository.BlogRepository;
import app.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private static final Logger logger = Logger.getLogger(BlogServiceImpl.class);

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public List<Blog> findAll() {
		try {
			return blogRepository.findAll();
		} catch (Exception e) {
			logger.error(e);
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public Blog findById(Long id) {
		try {
			return blogRepository.findById(id);
		} catch (Exception e) {
			logger.error(e);
		return null;		
	}
}
}
