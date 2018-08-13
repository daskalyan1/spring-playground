package com.example.demo;

public class MathService {

    private String operation = "";
    private Integer x;
    private Integer y;

    public String getOperation(){
        return operation;
    }
    public Integer getX(){
        return x;
    }
    public Integer getY(){
        return y;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }
    public void setX(Integer x){
        this.x = x;
    }
    public void setY(Integer y){
        this.y = y;
    }
}
