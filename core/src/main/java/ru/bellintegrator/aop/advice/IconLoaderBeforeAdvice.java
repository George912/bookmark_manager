package ru.bellintegrator.aop.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import ru.bellintegrator.model.Bookmark;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Совет для загрузки иконки сайта
 * и установки её закладке
 * Created by YANesterov on 21.05.2018.
 */
@Aspect
public class IconLoaderBeforeAdvice {
    private static final Logger LOGGER = Logger.getLogger(IconLoaderBeforeAdvice.class);
    private static final String FAVICON_PATH = "icon.png";
    private static final String ICON_LOAD_EXCEPTION = "exception while load icon: ";
    private static final String DEFAULT_ICON_PATH = "ru/bellintegrator/img/default.png";

    @Autowired
    private ResourceLoader resourceLoader;

    @Before("@annotation(ru.bellintegrator.aop.AdviceRequired)")
    public void before(JoinPoint joinPoint) {
        LOGGER.debug("call before(method=" + joinPoint.getSignature().getName() + ", arg=" + joinPoint.getArgs()[0] + ")");
        LOGGER.debug("aop in work");
        Bookmark bookmark = (Bookmark) joinPoint.getArgs()[0];
        byte[] iconBytes = null;
        try {
            URL faviconURL = new URL(bookmark.getUrl());
            iconBytes = loadIcon(new File(faviconURL.toURI()));
            LOGGER.debug("load icon from url");
        } catch (Exception e) {
            LOGGER.debug(ICON_LOAD_EXCEPTION, e);
        }

        if (iconBytes == null) {
            try {
                iconBytes = loadIcon(resourceLoader.getResource("classpath:" + DEFAULT_ICON_PATH).getFile());
                LOGGER.debug("load default icon");
            } catch (IOException e) {
                LOGGER.debug(ICON_LOAD_EXCEPTION, e);
            }
        }
        bookmark.setIcon(iconBytes);
    }

    /**
     * Загружает иконку в массив байт
     *
     * @param file файл иконки
     * @return массив байт
     */
    private byte[] loadIcon(Object file) {
        LOGGER.debug("call loadIcon(" + file + ")");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            if(file.getClass())
            ImageIO.write(ImageIO.read(file), "png", baos);
            baos.flush();
        } catch (IOException e) {
            LOGGER.debug(ICON_LOAD_EXCEPTION, e);
        }
        return baos.toByteArray();
    }
}
