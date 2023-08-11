package app.repository;

import app.model.Blog;

public interface BlogRepository extends BaseRepository<Long, Blog>{
	Blog findById(Long id);
}
