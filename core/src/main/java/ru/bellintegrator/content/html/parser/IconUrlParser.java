package ru.bellintegrator.content.html.parser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Получает url икноки для загрузки из html страницы
 * <p>
 * Created by YANesterov on 23.05.2018.
 */
//todo: refactoring, internationalization
@Service("iconUrlParser")
public class IconUrlParser {
    private static final Logger LOGGER = Logger.getLogger(IconUrlParser.class);
    private static final String ICON_PATTERN = "(\\.net|\\.png|\\.gif|\\.ico)";
    private static final String FULL_URL_PATTERN = "\"^((https?:\\\\/\\\\/){1}[a-z0-9._-]*)\"";
    private static final String INVALID_HREF_EXCEPTION_MESSAGE = "Method arg \"href\" must not be null";
    private static final String INVALID_URL_EXCEPTION_MESSAGE = "Method arg \"url\" must not be null";
    private static final String LINK_HREF_SELECTOR = "link[href]";

    public IconUrlParser() {
        LOGGER.info("IconUrlParser instance created");
    }

    //todo: logger

    /**
     * Получает объектную модель страницы
     *
     * @param url - адрес страницы
     * @return объектная модель страницы
     */
    private Document retrieveDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    //todo:docs, logger
    private Elements retrieveLinkElements(String url) throws IOException {
        return retrieveDocument(url).select(LINK_HREF_SELECTOR);
    }

    //todo:docs, logger
    public URL rettrieveIconUrl(String url) throws IllegalArgumentException {
        if (url == null)
            throw new IllegalArgumentException(INVALID_URL_EXCEPTION_MESSAGE);

        try {
            Elements linkElements = retrieveLinkElements(url);

            if (linkElements != null) {
                for (Element link : linkElements) {
                    if (isUrlMatchPattern(link.text(), ICON_PATTERN, INVALID_HREF_EXCEPTION_MESSAGE)) {
                        if (isUrlMatchPattern(link.text(), FULL_URL_PATTERN, INVALID_HREF_EXCEPTION_MESSAGE)) {
                            return new URL(url);
                        } else {
                            String site = retrieveSiteUrl(url, FULL_URL_PATTERN);
                            return new URL(site + link.text());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //todo:docs, logger
    private String retrieveSiteUrl(String url, String pattern) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(url);
        return matcher.group(0);
    }

    //todo:docs, logger
    private boolean isUrlMatchPattern(String href, String pattern, String exceptionMsg) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(href);

        if (href == null)
            throw new IllegalArgumentException(exceptionMsg);
        else
            return matcher.find();
    }
}
