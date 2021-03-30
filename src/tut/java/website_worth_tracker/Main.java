package tut.java.website_worth_tracker;

public class Main {

    public static void main(String[] args) {
        SiteScraper.getOverallRevenue();
        SiteScraper.getRevenueMap().forEach((key, value) -> System.out.printf("%s: %s%n", key, value));
    }
}
