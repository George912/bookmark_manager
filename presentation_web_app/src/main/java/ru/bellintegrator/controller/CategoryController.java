package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.model.Category;
import ru.bellintegrator.db.service.CategoryService;

import java.util.List;

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
                LOGGER.debug("category list for presentation: "+categories);
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
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "category/{id}")
    public String info(@PathVariable("id") Long id, Model model){
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
}
