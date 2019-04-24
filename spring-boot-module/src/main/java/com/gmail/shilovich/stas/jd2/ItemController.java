package com.gmail.shilovich.stas.jd2;

import com.gmail.shilovich.stas.jd2.servicemodule.ItemService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/home")
    public String getItems(Model model) {
        List<ItemDTO> list = itemService.getItems();
        model.addAttribute(list);
        return "items";
    }
}
