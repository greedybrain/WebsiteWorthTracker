package tut.java.website_worth_tracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiteScraper {
    private static Document response;
    private static String query;

    static {
        try {
            response = getHTMLData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Scrape website based on users site option = DONE
    private static Document getHTMLData() throws IOException {
        CLI cli = new CLI();
        query = cli.getUserQuery();
        String URL = String.format("https://www.worthofweb.com/website-value/%s/", query);
        return Jsoup.connect(URL).get();
    }

    // Get results
    private static List<String> getRevenueAmountsBreakdown() {
        List<String> amounts = new ArrayList<>();

        Elements cardBodies = response.getElementsByClass("card-body");
        Element revenueCard = cardBodies.get(9);
        Elements pTags = revenueCard.select("p");

        for (Element pTag : pTags) if(containsDollarSign(pTag.text())) amounts.add(pTag.text());

        return amounts;
    }

    public static void getOverallRevenue() {
        Element overallRevenue = response.getElementsByClass("card-body").get(6);
        Elements pTags = overallRevenue.select("p");
        for (Element pTag : pTags) if(containsDollarSign(pTag.text())) {
            System.out.printf("%nOverall Revenue for %s: %s%n", query, pTag.text());
        };

    }

    public static HashMap<String, String> getRevenueMap() {
        List<String> amounts = getRevenueAmountsBreakdown();
        HashMap<String, String> breakdown = new HashMap<>();

        breakdown.put("Daily", amounts.get(0));
        breakdown.put("Monthly", amounts.get(1));
        breakdown.put("Yearly", amounts.get(2));

        return breakdown;
    }

    private static Boolean containsDollarSign(String tagText) {
        return tagText.contains("$");
    }

}
