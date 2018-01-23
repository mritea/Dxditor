package itor.topnetwork.com.dxditor.bean;

/**
 * Created by handong on 2018/1/22.
 */

public class User {
    public User(String age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(String age, String name, String hobby, String color, String money) {
        this.age = age;
        this.name = name;
        this.hobby = hobby;
        this.color = color;
        this.money = money;
    }

    private  String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String hobby;
    private String color;
    private String money;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
