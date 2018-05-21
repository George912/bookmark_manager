package ru.bellintegrator.aop.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

/**
 * Совет для загрузки иконки сайта
 * и установки её закладке
 * Created by YANesterov on 21.05.2018.
 */
@Aspect
public class IconLoaderBeforeAdvice {
    private static final Logger LOGGER = Logger.getLogger(IconLoaderBeforeAdvice.class);
    @Autowired
    private ResourceLoader resourceLoader;

    @Before("@annotation(ru.bellintegrator.aop.AdviceRequired)")
    public void before() {
        LOGGER.debug("AOP advice in work");
//        LOGGER.debug("call before(" + method.getName() + ", " + objects[0].toString() + ", " + o.toString() + ")");
//
//        Bookmark bookmark = (Bookmark) objects[0];
//        String url = bookmark.getUrl();
//        BufferedImage icon = null;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            String fullURLString = url + "/favicon.ico";
//            URL faviconURL = new URL(fullURLString);
//            icon = ImageIO.read(faviconURL);
//        } catch (IOException e) {
//            LOGGER.debug("exception while executing before method: ", e);
//            icon = ImageIO.read(resourceLoader.getResource("classpath:default.png").getFile());
//        }
//        ImageIO.write(icon, "png", baos);
//        baos.flush();
//        bookmark.setIcon(baos.toByteArray());
    }
}
