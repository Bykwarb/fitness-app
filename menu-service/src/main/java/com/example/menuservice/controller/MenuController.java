package com.example.menuservice.controller;

import com.example.menuservice.entity.Menu;
import com.example.menuservice.exception.MenuNotFoundException;
import com.example.menuservice.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/menu-service")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/default/create")
    public ResponseEntity<?> createDefaultMenu(@RequestBody Menu menu){
        menuService.creatMenu(menu, true);
        return ResponseEntity.ok(responseMessage("Custom menu successfully created"));
    }

    @GetMapping("/default/menus")
    public ResponseEntity<?> getDefaultMenus(){
        return ResponseEntity.ok(menuService.getDefaultMenus());
    }

    @PutMapping("/default/menu")
    public ResponseEntity<?> updateDefaultMenu(@RequestBody Menu menu){
        menuService.updateMenu(menu, true);
        return ResponseEntity.ok(responseMessage("Default menu successfully updated"));
    }

    @PostMapping("/custom/create")
    public ResponseEntity<?> createCustomMenu(@RequestBody Menu menu){
        menuService.creatMenu(menu, false);
        return ResponseEntity.ok(responseMessage("Custom menu successfully created"));
    }

    @GetMapping("/custom/menus")
    public ResponseEntity<?> getCustomMenus(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(menuService.getCustomMenus(userId));
    }
    @PutMapping("/custom/menus")
    public ResponseEntity<?> updateCustomMenu(@RequestBody Menu menu){
        menuService.updateMenu(menu, false);
        return ResponseEntity.ok(responseMessage("Custom menu successfully updated"));
    }
    @GetMapping("/menu")
    public ResponseEntity<?> getMenu(@RequestParam("id") Long menuId) throws MenuNotFoundException {
        return ResponseEntity.ok(menuService.getMenu(menuId));
    }

    @ExceptionHandler(value = MenuNotFoundException.class)
    public ResponseEntity<?> productNotFounded(){
        Menu menu = new Menu();
        menu.setName("Dish with this id not found");
        return ResponseEntity.ok(menu);
    }

    private Map<String, String> responseMessage(String message){
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

}
