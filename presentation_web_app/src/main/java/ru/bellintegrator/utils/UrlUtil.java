package ru.bellintegrator.utils;

import org.apache.log4j.Logger;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Вспомогательный класс для преобразования url
 * в целевую кодировку
 */
public class UrlUtil {
    private static final Logger LOGGER = Logger.getLogger(UrlUtil.class);

    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        LOGGER.debug("Call encodeUrlPathSegment(pathSegment=" + pathSegment + ")");

        String encoding = httpServletRequest.getCharacterEncoding();
        encoding = encoding != null ? encoding : WebUtils.DEFAULT_CHARACTER_ENCODING;

        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, encoding);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Exception while encoding path segment: ", e);
        }
        return pathSegment;
    }
}
