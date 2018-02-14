package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.service.BookmarkService;

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
}
