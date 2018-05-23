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
@Service("iconUrlParser")
public class IconUrlParser {
    private static final Logger LOGGER = Logger.getLogger(IconUrlParser.class);
    private static final String ICON_PATTERN = "(\\.net|\\.png|\\.gif|\\.ico)";

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
    public Elements retrieveLinkElements(String url) throws IllegalArgumentException, IOException {
        if (url == null)
            throw new IllegalArgumentException("Method arg \"url\" must not be null");
        else
            return retrieveDocument(url).select("link[href]");
    }

    //todo:docs, logger
    public URL rettrieveIconUrl(Elements linkElements) {
        for (Element src : linkElements) {
            if (isIconLink(src.text())) {
            }
        }
        return null;
    }

    //todo:docs, logger
    private boolean isIconLink(String href) {
        Pattern pattern = Pattern.compile(ICON_PATTERN);
        Matcher matcher = pattern.matcher(href);
        if (href == null)
            throw new IllegalArgumentException("Method arg \"href\" must not be null");
        else
            return matcher.matches();
    }
}
