package maven.demo;

import static spark.Spark.*;

public class HelloWord2{
    public static void main(String[] arg){
        get("/hello", (request, response) -> "Hello World!");
    }
}
