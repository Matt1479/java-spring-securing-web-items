package com.matt1479.securingwebitems;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller 
public class ItemWebController {
    
    private final ItemService itemService;

    public ItemWebController(ItemService itemService) {
        this.itemService = itemService;
        this.createItems();
    }

    @GetMapping("/items")
    public String getItems(Model model) {
        model.addAttribute("items", itemService.getItems());
        return "items";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/items/{id}")
    public String deleteItem(@PathVariable Long id) {
        if (!itemService.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Item of id %d is not found".formatted(id)
            );
        }

        itemService.deleteItem(id);
        return "redirect:/items";
    }

    private void createItems() {
        this.itemService.createItem(
            new Item("An apple", null, "Apple", 0.99));
        this.itemService.createItem(
            new Item("A banana", null, "Banana", 1.15));
        this.itemService.createItem(
            new Item("A carrot", null, "Carrot", 0.97));
    }

}
