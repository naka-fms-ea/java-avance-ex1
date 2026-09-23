package fr.lndr.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJDBC implements ArticleDao {
	
	private Connection conn;

	public ArticleDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Article obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO t_articles " + "(Description, Brand, UnitaryPrice) "
					+ "VALUES " + "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getDescription());
			st.setString(2, obj.getBrand());
			st.setDouble(3, obj.getPrice());

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error. No rows affected.");

			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}

	}
	
	@Override
	public List<Article> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT t_articles.* FROM t_articles");

			rs = st.executeQuery();

			List<Article> list = new ArrayList<>();

			while (rs.next()) {

				int rsIdUSer = rs.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsDescription = rs.getString(2);
				String rsMarque = rs.getString(3);
				double rsPrixUnitaire = rs.getDouble(4);
				list.add((new Article(rsIdUSer, rsDescription, rsMarque, rsPrixUnitaire)));
			}
			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public void update(Article obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE t_articles "
					+ "SET Description = ?, Brand = ?, UnitaryPrice = ? " + "WHERE IdArticle = ?");

			st.setString(1, obj.getDescription());
			st.setString(2, obj.getBrand());;
			st.setDouble(3, obj.getPrice());
			st.setInt(4, obj.getId());
            
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}
	}
	
	@Override
	public Article findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT t_articles.* " + "FROM t_articles "
							+ "WHERE IdArticle = ?");
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				int rsIdUSer = rs.getInt(1);	//soit index(de 1 à n) de la colonne, soit le nom de la colonne
				String rsDescription = rs.getString(2);
				String rsMarque = rs.getString(3);
				double rsPrixUnitaire = rs.getDouble(4);
				Article obj = new Article(rsIdUSer, rsDescription, rsMarque, rsPrixUnitaire);
				return obj;
			}
			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM t_articles WHERE IdArticle = ?");
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected == 0) {
				throw new DbException("Id not exists!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}



}
