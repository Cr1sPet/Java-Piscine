package edu.school21.reflection.classes;

public class Car {

    private String brand;
    private Integer releaseYear;

    private Integer price;

    public Car() {
        this.brand = "Default brand";
        this.releaseYear = 0;
        this.price = 0;
    }

    public void sound() {
        System.out.println("Brum-Brum");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", releaseYear=" + releaseYear +
                ", price=" + price +
                '}';
    }
}
