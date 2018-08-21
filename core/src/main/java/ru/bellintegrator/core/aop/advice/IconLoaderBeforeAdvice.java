package ru.bellintegrator.core.aop.advice;

import net.sf.image4j.codec.ico.ICODecoder;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import ru.bellintegrator.core.parser.IconUrlParser;
import ru.bellintegrator.core.domain.Bookmark;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Совет для загрузки иконки сайта
 * и установки её закладке
 * Created by YANesterov on 21.05.2018.
 */
@Aspect
public class IconLoaderBeforeAdvice {
    private static final Logger LOGGER = Logger.getLogger(IconLoaderBeforeAdvice.class);
    private static final String ICON_LOAD_EXCEPTION = "exception while load icon: ";
    private static final String DEFAULT_ICON_PATH = "ru/bellintegrator/img/default.png";

    @Autowired
    private ResourceLoader resourceLoader;
    private IconUrlParser iconUrlParser;

    @Before("@annotation(ru.bellintegrator.core.aop.AdviceRequired)")
    public void before(JoinPoint joinPoint) {
        LOGGER.debug("call before(method=" + joinPoint.getSignature().getName() + ", arg=" + joinPoint.getArgs()[0] + ")");
        Bookmark bookmark = (Bookmark) joinPoint.getArgs()[0];
        byte[] iconBytes = null;
//        try {
//            URL faviconURL = iconUrlParser.rettrieveIconUrl(bookmark.getUrl());
//            if (faviconURL != null) {
//                iconBytes = loadIcon(faviconURL.openStream());
//                LOGGER.debug("load icon from url");
//            }
//        } catch (Exception e) {
//            LOGGER.debug(ICON_LOAD_EXCEPTION, e);
//        }

        if (iconBytes == null) {
            try {
                iconBytes = loadIcon(resourceLoader.getResource("classpath:" + DEFAULT_ICON_PATH).getInputStream());
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
     * @param inputStream входной поток, содержащий иконку
     * @return массив байт
     */
    private byte[] loadIcon(InputStream inputStream) {
        LOGGER.debug("call loadIcon(" + inputStream + ")");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (iconUrlParser.isIcoUrl()) {
            try {
                List<BufferedImage> images = ICODecoder.read(inputStream);
                if(!images.isEmpty()){
                    ImageIO.write(images.get(0), "png", baos);
                }
            } catch (IOException e) {
                LOGGER.debug(ICON_LOAD_EXCEPTION, e);
            }
        }

        try {
            ImageIO.write(ImageIO.read(inputStream), "png", baos);
            baos.flush();
        } catch (IOException e) {
            LOGGER.debug(ICON_LOAD_EXCEPTION, e);
        }
        return baos.toByteArray();
    }

    @Autowired
    @Qualifier("iconUrlParser")
    public void setIconUrlParser(IconUrlParser iconUrlParser) {
        this.iconUrlParser = iconUrlParser;
    }
}
