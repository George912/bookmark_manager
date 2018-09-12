package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bellintegrator.core.domain.Category;
import ru.bellintegrator.core.exception.ServiceException;
import ru.bellintegrator.service.CategoryService;

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
    @GetMapping
    public String list(Model model) {
        LOGGER.debug("call list method");

        try {
            List<Category> categoryList = categoryService.list();
            LOGGER.debug("category list for presentation: " + categoryList);
            model.addAttribute("categoryList", categoryList);
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
        LOGGER.debug("call info method");
        Category category;
        try {
            category = categoryService.findById(id);
            model.addAttribute("category", category);
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

    @GetMapping(value = "category/delete/{id}")
    public String showSingleCategoryRemovalConfirmer(@PathVariable Long id, HttpServletRequest httpServletRequest, Model model) {
        LOGGER.debug("call showSingleCategoryRemovalConfirmer(id=" + id + ")");
        try {
            model.addAttribute("category", categoryService.findById(id));
            return "categories/delete_confirm";
        } catch (ServiceException e) {
            LOGGER.error("Exception while category retrieving from database: ", e);
        }
        return "redirect:error";
    }

    @GetMapping(value = "deleteAll/{categoryId}")
    public String showBatchCategoryRemovalConfirmer(@PathVariable Long categoryId, Model model) {
        LOGGER.debug("call showBatchCategoryRemovalConfirmer(categoryId=" + categoryId + ")");
        try {
            model.addAttribute("category", categoryService.findById(categoryId));
            return "categories/delete_all_confirm";
        } catch (ServiceException e) {
            LOGGER.error("Exception while category retrieving from database: ", e);
        }
        return "redirect:error";
    }

    @DeleteMapping(value = "category/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        LOGGER.debug("call delete(id=" + id + ")");
        try {
            categoryService.delete(id);
            model.addAttribute("categoryList", categoryService.list());
        } catch (ServiceException e) {
            LOGGER.error("Exception while category removing: ", e);
        }
        return "categories/list";
    }

    @DeleteMapping(value = "deleteAll/{categoryId}")
    public String deleteAll(@PathVariable Long categoryId, Model model) {
        LOGGER.debug("call deleteAll(categoryId = " + categoryId);
        try {
            categoryService.deleteAll(categoryId);
            model.addAttribute("category", categoryService.findById(categoryId));
            return "categories/viewer";
        } catch (ServiceException e) {
            LOGGER.error("Exception while subcategories removing: ", e);
        }
        return "redirect:error";
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
            model.addAttribute("categoryList", categoryService.list());
        } catch (ServiceException e) {
            LOGGER.error("Exception while updating category: ", e);
        }
        return "categories/list";
    }
}