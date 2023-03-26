package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.exception.MenuNotFoundException;
import com.example.menuservice.repository.MenuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultMenuService implements MenuService{

    private final MenuRepository menuRepository;

    public DefaultMenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void creatMenu(Menu menu, boolean isDefault) {
        menu.setIsDefault(isDefault);
        menuRepository.save(menu);
    }

    @Override
    public void updateMenu(Menu menu, boolean isDefault) {
        if(menu.getIsDefault() == isDefault){
            menuRepository.save(menu);
        }else if (isDefault){
            menuRepository.save(menu);
        }
    }

    @Override
    public Menu getMenu(Long menuId) throws MenuNotFoundException {
        return menuRepository.findById(menuId).orElseThrow(()-> new MenuNotFoundException("Product with id " + menuId + " not founded."));
    }

    @Override
    public List<Menu> getDefaultMenus() {
        return menuRepository.getMenusByIsDefaultIsTrue();
    }

    @Override
    public List<Menu> getCustomMenus(Long creatorId) {
        return menuRepository.getMenusByCreatorId(creatorId);
    }

}
