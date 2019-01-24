package com.example.demo.bean;

public class Item {
    private String name;
    private String color;
    private String img;

    public Item(String name, String color, String img) {
        this.name = name;
        this.color = color;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
