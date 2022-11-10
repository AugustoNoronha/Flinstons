package DAO;
import java.nio.charset.StandardCharsets;

import MODEL.Items;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;




import static spark.Spark.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.util.encoders.Hex;

public class ItemsDAO extends DAO {
	
	public ItemsDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean add(Items items) throws NoSuchAlgorithmException {
		boolean status = false;
		try {
			
			String sql = "INSERT INTO items (modelo, preco, descricao, img, link) "
		               + "VALUES ('" + items.getModelo() + "', " + "'"
		               + items.getPreco() + "', " + "'" + items.getDesc() + "', "
		               + "'" + items.getImg() + "', " + "'" + items.getLink() + "'" + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			System.out.print(sql);
			st.executeUpdate();
			st.close();
			
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean addInFav(Items items) throws NoSuchAlgorithmException{
		boolean status = false;
	try {
			
			String sql = "INSERT INTO favoritos (modelo, preco, categoria, link, img) "
		               + "VALUES ('" + items.getModelo() + "', " + "'"
		               + items.getPreco() + "', " + "'" + items.getDesc() + "', "
		               + "'" + items.getLink() + "', " + "'" + items.getImg() + "'" + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			System.out.print(sql);
			st.executeUpdate();
			st.close();
			status = true;
			
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	

	public List<Items> get() {
		List<Items> items = new ArrayList<Items>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM items";
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	    
	        	System.out.println(rs.getString("modelo"));
	        	Items p = new Items(rs.getString("modelo"),  rs.getString("preco"),
	        						rs.getString("descricao"),  rs.getString("link"),  
	        						rs.getString("img"));
	        	items.add(p);
	        	
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return items;
	}
	
	public Items get(int codigo) {
		Items item = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM favoritos WHERE id="+codigo;
			ResultSet rs = st.executeQuery(sql);	
			System.out.print(sql);
	        if(rs.next()){            
	        	 item = new Items(rs.getString("modelo"), rs.getString("preco"), rs.getString("categoria"), rs.getString("link"), rs.getString("img"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return item;
	}
	
	
	public List<Items> getFav() {
		List<Items> items = new ArrayList<Items>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM favoritos";
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	    
	        	System.out.println(rs.getString("modelo"));
	        	Items p = new Items(rs.getString("modelo"),  rs.getString("preco"),
	        						rs.getString("categoria"),  rs.getString("link"),  
	        						rs.getString("img"));
	        	p.setCodigo(Integer.parseInt(rs.getString("id")));
	        	items.add(p);
	        	
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return items;
	}
	

	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM favoritos WHERE id = " + codigo;
			st.executeUpdate(sql);
			System.out.print(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}