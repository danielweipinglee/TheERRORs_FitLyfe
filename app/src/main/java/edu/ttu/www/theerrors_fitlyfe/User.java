package edu.ttu.www.theerrors_fitlyfe;

/**
 * Created by Matthew on 3/22/2018.
 */

public class User {

    private String name;
    private String age;
    private String email;
    private String password;
    private String height;
    private String weight;
    private String username;
    private String gender;

    public User() {
    }

    public User(String name, String age, String email, String password, String height, String weight, String username) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.username = username;
        this.gender = gender;

    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAge() {return age;}

    public void setAge(String age) {this.age = age;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}
}
