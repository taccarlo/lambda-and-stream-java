package com.company;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

class Product {
    private final int id;
    private final String name;
    private final int expirationMonth;
    private double cost;

    public Product(int id, String name, double cost, int expirationMonth) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.expirationMonth = expirationMonth;
    }

    public void setIncrement(int i) {
        this.cost += i;
    }

    @Override
    public String toString() {
        return id + " " + name + ' ' + cost + ' ' + expirationMonth;
    }

    public double getCost() {
        return this.cost;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

public class Main {

    @SuppressWarnings("FieldMayBeFinal")
    private static Product[] arrayOfProds = {
            new Product(3, "Bread", 2.0, 7),
            new Product(2, "Cake", 5.0, 6),
            new Product(1, "Milk", 3.0, 12),
            new Product(4, "Meat", 3.0, 10)
    };

    public static void main(String[] args) {


    }

    /* increment the cost of each product of 2 */
    @Test
    public void incrementCost() {
        Stream.of(arrayOfProds).forEach(e -> e.setIncrement(2));
        stampResult();
    }

    @Test
    public void stampResult() {
        Stream.of(arrayOfProds).forEach(System.out::println);
    }

    @Test
    public void stampComponentOfResult() {
        Stream.of(arrayOfProds).map(Product::getName).forEach(System.out::println);
    }

    @Test
    public void getProductsMoreExpensive() {
        long i = Stream.of(arrayOfProds)
                .filter(e -> e.getCost() > 3)
                .count();
        System.out.println(i);
        i = Stream.of(arrayOfProds)
                .filter(e -> e.getCost() >= 3)
                .count();
        System.out.println(i);

    }

    @Test
    public void getSumOfAllTheProducts() {
        double a = 0;
        for (Product h : arrayOfProds) {
            a += h.getCost();
        }
        double b = Stream.of(arrayOfProds).mapToDouble(Product::getCost).sum();
        assert (a == b);
    }

    @Test
    public void findById() {
        assert(findById(1).equals("Milk"));
    }

    public String findById(int i) {
        Product b = Stream.of(arrayOfProds).filter(a -> i == a.getId()).findAny().orElse(null);
        if(b==null)
            return "";
        return b.getName();
    }

}
