package SERVICES;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import APP.JsonTransformer;
import DAO.ItemsDAO;
import DAO.UsuarioDAO;
import MODEL.Items;
import MODEL.Usuario;


import java.time.LocalDate;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import spark.Request;
import spark.Response;



public class ItemServices {
		
	private ItemsDAO itemDAO;
	
	public ItemServices() {
		itemDAO = new ItemsDAO();
	}
	
	private String form;
	private String html;
	
	//add
	public Object add (Request request, Response response) throws NoSuchAlgorithmException {
		String modelo = request.queryParams("modelo");
		String preco = request.queryParams("preco");
		String desc = request.queryParams("desc");
		String link = request.queryParams("link");
		String img = request.queryParams("img");
		String resp = request.body();
		
		Items items = new Items(modelo, preco, desc, link, img);
		
		if(itemDAO.add(items) == true) {
            resp = "Cadastro realizado!";
            response.status(201); // 201 Created
		} else {
			resp = "Não foi possível cadastar!";
			response.status(404); // 404 Not found
		}
			return "<h2>+ resp + <h2/>";
	}
	
	//addInFav
	public Object addInFav (Request request, Response response) throws NoSuchAlgorithmException {
		String modelo = request.queryParams("modelo");
		String preco = request.queryParams("preco");
		String desc = request.queryParams("categoria");
		String link = request.queryParams("link");
		String img = request.queryParams("img");
		String resp = request.body();
		Items items = new Items(modelo, preco, desc, link, img);
		
		if(itemDAO.addInFav(items) == true) {
            resp = "Cadastro realizado!";
            html = "<h1 style=\"color:blue\">" + "O item " + items.getModelo() +" foi adicionado nos favoritos" + "</h1>" 
                    + "<a href=\"/list.html\">Voltar</a>";
            response.status(201); // 201 Created
		} else {
			resp = "Não foi possível cadastar!";
			response.status(404); // 404 Not found
		}
			return html;
	}
	
	
	//get
	public Object getAll(Request request, Response response) {
		
		List<Object> list = new ArrayList();
		JSONObject obj = new JSONObject();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Type","application/json; charset=UTF-8");
	    List<Items> res = itemDAO.get();
        for(int i = 0; i < res.size(); i++) {
        	
       		obj.put("modelo",res.get(i).getModelo());
       		obj.put("preco",res.get(i).getPreco());
       		obj.put("link",res.get(i).getLink());
       		obj.put("img",res.get(i).getImg());
       		obj.put("cat", res.get(i).getDesc());
       		
       		list.add(obj.clone());
       		System.out.println(obj);
        }
       
      
       
		return list;
	}	

	public Object getById(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Items items = (Items) itemDAO.get(id);
		  String resp = "";
		
		if (items != null) {
			response.status(200); // success
			resp = "item " + id + " econtrado.";
        } else {
            response.status(404); // 404 Not found
            resp = "item " + id + " não encontrado.";
    		
    		
        }

		return resp;
	}
	
	public Object getFav(Request request, Response response) {
		
		List<Object> list = new ArrayList();
		JSONObject obj = new JSONObject();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Type","application/json; charset=UTF-8");
	    List<Items> res = itemDAO.getFav();
        for(int i = 0; i < res.size(); i++) {
        	
        	obj.put("id", res.get(i).getCodigo());
       		obj.put("modelo",res.get(i).getModelo());
       		obj.put("preco",res.get(i).getPreco());
       		obj.put("link",res.get(i).getLink());
       		obj.put("img",res.get(i).getImg());
       		obj.put("cat", res.get(i).getDesc());
       		
       		list.add(obj.clone());
       		System.out.println(obj);
        }
       
      
       
		return list;
	}	
	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        System.out.print(id);
        Items item = itemDAO.get(id);
        System.out.print(item);
        String resp = "";       

        if (item != null) {
            itemDAO.delete(id);
            response.status(200); // success
            resp = "item (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "item (" + id + ") não encontrado!";
        }
		
         html = "<h1 style=\"color:blue\">" + "O item " + item.getModelo() +" foi excluido dos favoritos" + "</h1>" 
                + "<a href=\"/listfav.html\">Voltar</a>";
         return html;
	}
}

	
