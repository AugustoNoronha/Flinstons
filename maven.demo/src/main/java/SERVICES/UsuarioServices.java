package SERVICES;
import java.util.Scanner;
import DAO.UsuarioDAO;
import MODEL.Usuario;
import java.time.LocalDate;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import spark.Request;
import spark.Response;



public class UsuarioServices {
		
	private UsuarioDAO usuarioDAO;
	
	public UsuarioServices() {
			usuarioDAO = new UsuarioDAO();
	}
	
	public Object add (Request request, Response response) throws NoSuchAlgorithmException {
		String name = request.queryParams("name");
		String email = request.queryParams("email");
		String password = request.queryParams("password");
		//System.out.println("add call on services");
		String resp = request.body();
		//System.out.println(request.body());
		//System.out.println(request.toString());
		
		
		
		Usuario usuario = new Usuario(name, email, password);
		
		if(usuarioDAO.add(usuario) == true) {
            resp = "Cadastro realizado!";
            response.status(201); // 201 Created
		} else {
			resp = "Não foi possível cadastar!";
			response.status(404); // 404 Not found
		}
			return "<h2>+ resp + <h2/>";
	}
}