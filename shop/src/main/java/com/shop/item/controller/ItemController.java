package com.shop.item.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping("/admin/item/new")
    public String makeItem(){
        return "item form";
    }
}
