package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bellintegrator.db.dao.impl.orm.hibernate.BookmarkDaoImpl;
import ru.bellintegrator.db.exception.DAOException;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.model.Bookmark;
import ru.bellintegrator.db.service.BookmarkService;
import ru.bellintegrator.model.Category;
import ru.bellintegrator.utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {
    private static final Logger LOGGER = Logger.getLogger(BookmarkController.class);
    private BookmarkService bookmarkService;

    public BookmarkController() {
    }

    @Autowired
    public void setBookmarkService(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    /**
     * Получает закладку по id из источника данных
     * и передаёт в представление для просмотра детальной информации
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("bookmark/viewer")
    public String info(@RequestParam(value = "bookmarkId") Long id, Model model) {
        LOGGER.debug("Call info(id=" + id + ")");

        //todo: ?spring exception resolver
        try {
            model.addAttribute("bookmark", bookmarkService.findById(id));

        } catch (ServiceException e) {
            LOGGER.error("Exception while retrieving bookmark: ", e.getCause());
        }
        return "bookmarks/viewer";
    }

    @GetMapping("bookmark/editor")
    public String showEditor(@RequestParam(value = "bookmarkId") Long id, Model model) {
        LOGGER.debug("Call edit(id=" + id + ")");
        Bookmark bookmark;

        //todo: ?spring exception resolver
        try {
            if (id != -1) {
                bookmark = bookmarkService.findById(id);
            } else {
                bookmark = new Bookmark("for test", "for test");
            }
            model.addAttribute("bookmark", bookmark);

        } catch (ServiceException e) {
            LOGGER.error("Exception while retrieving bookmark: ", e.getCause());
        }

        return "bookmarks/editor";
    }

    @PostMapping(value = "bookmark/editor")
    public String update(Bookmark bookmark, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.debug("Call update(bookmark=" + bookmark + ")");

        //todo: check bindingResult, id(add or update)
        try {
            if(bookmark.getId()!=null){
                bookmarkService.update(bookmark);
            }else{
                bookmarkService.add(bookmark);
            }

        } catch (ServiceException e) {
            LOGGER.error("Exception while updating bookmark: ", e);
        }
        return "redirect:viewer?bookmarkId=" + UrlUtil.encodeUrlPathSegment(bookmark.getId().toString(), httpServletRequest);
    }
}
