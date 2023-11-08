package com.tcc.taskcomplete.controller;

import com.tcc.taskcomplete.loja.Item;
import com.tcc.taskcomplete.loja.ItemRepository;
import com.tcc.taskcomplete.loja.dadosItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loja")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public void cadastrar(@RequestBody dadosItem item) {
        itemRepository.save(new Item(item));
    }
}
