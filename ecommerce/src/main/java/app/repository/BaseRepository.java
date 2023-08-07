package app.repository;

import java.util.List;

public interface BaseRepository<PK, T> {
	List<T> findAll();
	T findById(PK id);
}
