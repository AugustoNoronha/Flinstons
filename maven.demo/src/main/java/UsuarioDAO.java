import java.nio.charset.StandardCharsets;
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

public class UsuarioDAO extends DAO {
	
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean add(Usuario usuario) throws NoSuchAlgorithmException {
		boolean status = false;
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(
			  usuario.getPassWord().getBytes(StandardCharsets.UTF_8));
			String sha256hex = new String(Hex.encode(hash));
			
			String sql = "INSERT INTO users (name, email, password) "
		               + "VALUES ('" + usuario.getName() + "', " + "'"
		               + usuario.getEmail() + "', " + "'" + sha256hex + "'" + ");";
			PreparedStatement st = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.print(sql);
			final ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
			    final int lastId = rs.getInt(1);
			    System.out.print(lastId);
			}
			st.executeUpdate();
			st.close();
			
			
			//if (rs.next()) {
			  
			    //String sqlFav =  "INSERT INTO favoritos (user_id)" + "VALUES ('" + lastId + " ');";
				//PreparedStatement stFav = conexao.prepareStatement(sqlFav);
				//stFav.executeUpdate();
				//stFav.close();
			//	status = true;
			//}
			
			
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}