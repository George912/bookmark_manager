package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.service.BookmarkService;
import ru.bellintegrator.db.service.CategoryService;
import ru.bellintegrator.model.Bookmark;
import ru.bellintegrator.model.Category;
import ru.bellintegrator.utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {
    private static final Logger LOGGER = Logger.getLogger(BookmarkController.class);
    private BookmarkService bookmarkService;
    private CategoryService categoryService;

    public BookmarkController() {
    }

    @Autowired
    public void setBookmarkService(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
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
            List<Category> categoryList = categoryService.list();
            model.addAttribute("bookmark", bookmark);
            model.addAttribute("categoryList", categoryList != null ? categoryList : new ArrayList<Category>());

        } catch (ServiceException e) {
            LOGGER.error("Exception while retrieving bookmark: ", e.getCause());
        }

        return "bookmarks/editor";
    }

    @PostMapping(value = "bookmark/editor")
    public String update(Bookmark bookmark, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.debug("Call update(bookmark=" + bookmark + ")");

        //todo: check bindingResult
        try {
            if (bookmark.getId() != null) {
                bookmarkService.update(bookmark);
            } else {
                bookmarkService.add(bookmark);
            }
        } catch (ServiceException e) {
            LOGGER.error("Exception while updating bookmark: ", e);
        }
        return "redirect:viewer?bookmarkId=" + UrlUtil.encodeUrlPathSegment(bookmark.getId().toString(), httpServletRequest);
    }

    @DeleteMapping(value = "bookmark/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        LOGGER.debug("call delete(id=" + id + ")");

        try {
            Long categoryId = bookmarkService.findById(id).getCategory().getId();
            bookmarkService.delete(id);
            model.addAttribute("category", categoryService.findById(categoryId));
            return "categories/viewer";
        } catch (ServiceException e) {
            LOGGER.error("Exception while bookmark removing: ", e);
        }
        return "redirect:error";
    }

    @GetMapping(value = "bookmark/delete/{id}")
    public String showBookmarkDeleteConfirm(@PathVariable Long id, HttpServletRequest httpServletRequest, Model model) {
        LOGGER.debug("call showBookmarkDeleteConfirm(id=" + id + ")");
        Bookmark bookmark;

        try {
            bookmark = bookmarkService.findById(id);
            model.addAttribute("bookmark", bookmark);
            return "bookmarks/delete_confirm";
        } catch (ServiceException e) {
            LOGGER.error("Exception while bookmark retrieving from database: ", e);
        }
        return "redirect:error";
    }
}
