package com.example.menuservice.service;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.exception.MenuNotFoundException;

import java.util.List;

public interface MenuService {
    void creatMenu(Menu menu, boolean isDefault);
    void updateMenu(Menu menu, boolean isDefault);
    Menu getMenu(Long menuId) throws MenuNotFoundException;
    List<Menu> getDefaultMenus();
    List<Menu> getCustomMenus(Long creatorId);

}
