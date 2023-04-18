package com.javaexamples;


public class Transaction {
    /**
     *
     */
    private Integer id;
    private String date;
    private String description;
    private Double value;
    private String type;

    public Transaction(Integer id, String type) {
        this.id = id;
        this.type = type;
        this.value = 0.0;
    }

    public Transaction(Integer id, String type, Double value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public Transaction(Integer id, String date, String description, Double value, String type) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", date=" + date + ", description=" + description + ", value=" + value
                + ", type=" + type + "]";
    }
}
