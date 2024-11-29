import java.io.IOException;

import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

import aula2.exemplo.classes.Post;
import aula2.exemplo.classes.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String url = "https://jsonplaceholder.typicode.com/users";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                                        .uri(URI.create(url))
                                        .build();
        HttpResponse response = client.send(req, HttpResponse.BodyHandlers.ofString());

        HttpRequest nova_req = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();
        HttpResponse novo_res = client.send(nova_req, HttpResponse.BodyHandlers.ofString());

        // INSTANCIA GSON
        Gson gson = new Gson();

        // TRAZ O RESPONSE BODY COMO UM ARRAY JSON
        JsonArray jsonArray = gson.fromJson(response.body().toString(), JsonArray.class);
        JsonArray postsArray = gson.fromJson(novo_res.body().toString(), JsonArray.class);

        // PEGA O PRIMEIRO INDICE.
        JsonObject object = jsonArray.get(0).getAsJsonObject();

        // Criando objeto User a partir de um json.
        User userTeste = gson.fromJson(object, User.class);
        User trueUser = new User(userTeste.getName(), userTeste.getUsername(), userTeste.getEmail(), userTeste.getPhone());

        System.out.println(userTeste.getEmail());
        System.out.println(userTeste.getUsername());
        System.out.println(userTeste.getPhone());
        System.out.println(userTeste.getName());

        for (int i = 0; i < postsArray.size(); i++) {
            if (postsArray.get(i).getAsJsonObject().get("userId").toString().equals("1")) {
                Post novo_post = gson.fromJson(postsArray.get(i).getAsJsonObject(), Post.class);
                trueUser.createPost(novo_post);

            }
        }

        trueUser.seeAllPosts();

    }
}