package helvidios.search.webcrawler.url;

import java.io.IOException;
import org.junit.Test;
import helvidios.search.webcrawler.HtmlDocument;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UrlExtractorTest {
    @Test
    public void extractUrls() throws IOException, Exception {
        HtmlDocument doc = new HtmlDocument(
            "http://www.mycrawler.net/dir1/dir2/page.html", 
            new String(getClass().getClassLoader().getResourceAsStream("page1.html").readAllBytes()));

        UrlExtractor extractor = new SimpleUrlExtractor();
        List<String> urls = extractor.getUrls(doc);
        List<String> expected = Arrays.asList(
            "https://www.w3schools.com/html/",
            "http://www.mycrawler.net/html/default.asp",
            "http://www.mycrawler.net/admin",
            "http://www.mycrawler.net/dir1/dir2/start"
        );
        Collections.sort(expected);
        assertThat(urls, is(expected));
    }
}