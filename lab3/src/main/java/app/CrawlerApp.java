package app;

import java.io.IOException;
import java.util.List;

public class CrawlerApp {

    public static final String GOOGLE_CUSTOM_SEARCH_API_KEY = "AIzaSyCY9fBI1TiA_0oY6hjajt3wjyQp4yGRriU";

    private static final List<String> TOPICS = List.of("Agent Cooper", "Sherlock", "Poirot", "Detective Monk");


    public static void main(String[] args) throws IOException {
        PhotoCrawler photoCrawler = new PhotoCrawler();
        photoCrawler.resetLibrary();
//        photoCrawler.downloadPhotoExamples();
//        photoCrawler.downloadPhotosForQuery(TOPICS.get(0));
        photoCrawler.downloadPhotosForMultipleQueries(TOPICS);
        try {
            Thread.sleep(100_000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}