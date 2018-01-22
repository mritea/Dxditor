package itor.topnetwork.com.dxditor.bean;

/**
 * Created by handong on 2018/1/22.
 */

public class User {
    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private  int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


}
