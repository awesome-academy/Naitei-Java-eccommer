package app.repository;

import java.util.List;

import app.model.Blog;

public interface BlogRepository extends BaseRepository<Long, Blog>{
	Blog findById(Long id);
	public List<Blog> getTop3BlogsByDate();
}
