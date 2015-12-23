package org.shopkeeper.api;
import static spark.Spark.*;

public class Api {
    public static void main(String[] args) {

        get("/hello", (request, response) -> "hello World");
        get("/hellos", (request, response) -> "hello Worlds");


    }
}
