package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.service.CategoryService;
import ru.bellintegrator.model.Category;
import ru.bellintegrator.utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private static final Logger LOGGER = Logger.getLogger(CategoryController.class);
    private CategoryService categoryService;

    public CategoryController() {
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Получает список категорий из источника данных
     * и передаёт в представление для рендеринга
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Category> categories;
        LOGGER.debug("call list method");

        try {
            if (categoryService != null) {
                categories = categoryService.list();
                LOGGER.debug("category list for presentation: " + categories);
                model.addAttribute("categories", categories);
            }
        } catch (ServiceException e) {
            LOGGER.debug("Exception while receiving category list: ", e);
        }
        return "categories/list";
    }

    /**
     * Получает категорию по id из источника данных
     * и передаёт в представление для просмотра детальной информации
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("category/viewer")
    public String info(@RequestParam(value = "categoryId") Long id, Model model) {
        Category category;
        LOGGER.debug("call info method");

        try {
            if (categoryService != null) {
                category = categoryService.findById(id);
                model.addAttribute("category", category);
            }
        } catch (ServiceException e) {
            LOGGER.debug("Exception while receiving category: ", e.getCause());
        }
        return "categories/viewer";
    }

    @GetMapping("category/editor")
    public String showEditor(@RequestParam(value = "categoryId") Long id, Model model) {
        LOGGER.debug("Call edit(id=" + id + ")");
        Category category;
        List<Category> categoryList;

        //todo: ?spring exception resolver
        try {
            if (id != -1) {
                category = categoryService.findById(id);
            } else {
                category = new Category("for test");
            }

            categoryList = categoryService.list();
            model.addAttribute("category", category);
            model.addAttribute("categoryList", category != null ? categoryList : new ArrayList<Category>());

        } catch (ServiceException e) {
            LOGGER.error("Exception while retrieving category or category list: ", e.getCause());
        }

        return "categories/editor";
    }

    @PostMapping(value = "category/editor")
    public String update(Category category, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.debug("Call update(category=" + category + ")");

        //todo: check bindingResult
        try {
            if (category.getId() != null) {
                categoryService.update(category);
            } else {
                categoryService.add(category);
            }
        } catch (ServiceException e) {
            LOGGER.error("Exception while updating category: ", e);
        }
        return "redirect:viewer?categoryId=" + UrlUtil.encodeUrlPathSegment(category.getId().toString(), httpServletRequest);
    }
}
