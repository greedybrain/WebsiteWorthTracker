package tut.java.website_worth_tracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SiteScraperTest {
    @Test
    void getHTMLData() throws IOException {
        String URL = String.format("https://www.worthofweb.com/website-value/%s/", "google");

        Document response = Jsoup.connect(URL).get();
        Boolean containsValidTitle = response.select("title").text().contains("Worth Of Web");

        assertEquals(containsValidTitle, true);
    }

    @Test
    void getRevenueAmountsBreakdown() throws IOException {
        String URL = String.format("https://www.worthofweb.com/website-value/%s/", "yahoo");

        Document response = Jsoup.connect(URL).get();

        Elements cardBodies = response.getElementsByClass("card-body");
        Element revenueCard = cardBodies.get(9);
        Long pTagsAmount = revenueCard.select("p")
                .stream()
                .filter(tag -> tag.text().contains("$"))
                .count();

        assertEquals(3, pTagsAmount);
    }
}