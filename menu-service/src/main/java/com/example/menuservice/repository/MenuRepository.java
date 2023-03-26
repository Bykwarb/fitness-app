package com.example.menuservice.repository;

import com.example.menuservice.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    List<Menu> getMenusByIsDefaultIsTrue();

    List<Menu> getMenusByCreatorId(Long creatorId);
}
