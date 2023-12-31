package app.service;

import java.util.List;

public interface BaseService<PK, T> {
	List<T> findAll();
	T findById(PK id);
}
