package ru.anatomica.cookstarter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anatomica.cookstarter.entities.Menu;
import ru.anatomica.cookstarter.repositories.MenuRepository;

import java.util.List;

@Service
public class MenuService {
    private MenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllCategories() {
        return menuRepository.findAll();
    }

    public List<Menu> getCategoriesByIds(List<Long> ids) {
        return menuRepository.findAllById(ids);
    }
}
