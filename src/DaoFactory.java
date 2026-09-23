package fr.lndr.jdbc;

public class DaoFactory {
	
	public static ArticleDao createArticleDao()	{
		return new ArticleDaoJDBC(DB.getConnection());
	}
	
	

}
