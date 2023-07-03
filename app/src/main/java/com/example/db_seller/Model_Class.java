package com.example.db_seller;

public class Model_Class {

    private Integer connection;

    private Integer result;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ModelClass{" +
                "connection=" + connection +
                ", result=" + result +
                '}';
    }
}
