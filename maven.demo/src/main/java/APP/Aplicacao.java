package APP;
import java.util.List;
import static spark.Spark.*;

import SERVICES.ItemServices;
import SERVICES.UsuarioServices;




public class Aplicacao {
	
	private static UsuarioServices usuarioServices = new UsuarioServices();
	private static ItemServices itemServices = new ItemServices();
	
	public static void main(String[] args) throws Exception {
		port(4200);
		
		staticFiles.location("/public/Front-end");
		
		get("/hello",(req,res)-> "helloword");
		get("/", (req,res) -> "index.html");
        post("/insert", (request, response) -> usuarioServices.add(request, response));
        post("/addfav", (request, response) -> itemServices.add(request, response));
        get("/getitem", (request, response) -> itemServices.getAll(request, response));
        get("/getfav", (request, response) -> itemServices.getFav(request, response));
        post("/favitem", (request, response) -> itemServices.addInFav(request, response));
        get("/jogo/:id", (request, response) -> itemServices.getById(request, response));
        get("/fav/delete/:id", (request, response) -> itemServices.delete(request, response));
	}
}