package app.service;

import java.util.List;

import app.model.Blog;


public interface BlogService extends BaseService<Long, Blog>{
	public List<Blog> getTop3BlogsByDate();
}
