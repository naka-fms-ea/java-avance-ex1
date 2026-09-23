package fr.lndr.jdbc;

import java.util.List;

import java.util.Scanner;


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArticleDao articleDao = DaoFactory.createArticleDao();
		System.out.println("=== TEST 1: Article findById ====");
		Article article = articleDao.findById(8);
		System.out.print(article);
		
		System.out.println("\n=== TEST 2: Article findByAll ====");

		List<Article> list = articleDao.findAll();

		for (Article obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: Article insert ====");

		Article newArticle = new Article("Description Test", "Brand Test", 88.0);
		articleDao.insert(newArticle);
		System.out.println("Inserted! New id = " + newArticle.getId());
		
		System.out.println("\n=== TEST 4: Article update ====");

		article = articleDao.findById(17);
	
		article.setBrand("Brand Test Test");
		articleDao.update(article);
		System.out.println("Update completed");
		
		System.out.println("\n=== TEST 5: Article delete ====");

		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		articleDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
		
		
	}

}
