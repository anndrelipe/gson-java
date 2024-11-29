package aula2.exemplo.classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private List<Post> posts;

    public User (String name, String username, String email, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.posts = new ArrayList<>();
    }

    public void createPost (Post newPost) {
        this.posts.add(newPost);
    }

    public void seeAllPosts () {
        for (var post: this.posts) {
            System.out.println("User id - " + post.userId() +  "| Post id - " +post.id()+ "| Title - "+ post.title() + "| Body - "+post.body());
        }
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

}
