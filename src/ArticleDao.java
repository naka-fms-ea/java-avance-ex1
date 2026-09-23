package fr.lndr.jdbc;

import java.util.List;

public interface ArticleDao {

	void insert(Article obj);

	void update(Article obj);

	void deleteById(Integer id);

	Article findById(Integer id);

	List<Article> findAll();
	
}
