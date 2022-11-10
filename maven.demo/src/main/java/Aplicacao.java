import java.util.List;

import com.google.gson.Gson;

import SERVICES.ItemServices;

import static spark.Spark.*;




public class Aplicacao {
	
	private static UsuarioServices usuarioServices = new UsuarioServices();
	private static ItemServices itemServices = new ItemServices();
	
	public static void main(String[] args) throws Exception {
		port(4200);
		
		staticFiles.location("/public/Front-end");
		
		get("/hello",(req,res)-> "helloword");
		get("/", (req,res) -> "index.html");
        post("/insert", (request, response) -> usuarioServices.add(request, response));
        post("addfav", (request, response) -> "helloword");
        Gson gson = new Gson();
        get("/getitem", (request, response) -> itemServices.getAll(request, response),gson::toJson);

	}
}