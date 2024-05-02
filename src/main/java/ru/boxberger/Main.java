package ru.boxberger;

public class Main {
    public static void main(String[] args) {
        Comparer pagesComparer = new Comparer(Parser.getPairsToday(), Parser.getPairsYesterday());
        System.out.println(pagesComparer.createMessage());
    }
}