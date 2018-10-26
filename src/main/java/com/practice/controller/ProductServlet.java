package com.practice.controller;

import com.practice.model.Product;
import com.practice.service.ProductService;
import com.practice.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ProductServlet{
    private ProductService productService = new ProductServiceImpl();

    @GetMapping
    public ModelAndView showIndex(){
        ModelAndView modelAndView = new ModelAndView("index", "products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("create", new Product());
        return "create";
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect){
        product.setId((int)(Math.random() * 10000));
        productService.save(product);
        redirect.addFlashAttribute("success", "Saved customer successfully!");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id){
        ModelAndView showEdit = new ModelAndView("edit", "editproduct", productService.findByID(id));
        return showEdit;
    }

    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirectAttributes){
        productService.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("success", "Modified successfully !");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView showDel = new ModelAndView("delete", "del", productService.findByID(id));
        return showDel;
    }

    @PostMapping("/del")
    public String del(Product product, RedirectAttributes redirectAttributes){
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("success", "Delete Successfully !");
        return "redirect:/";
    }

    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable int id){
        ModelAndView showView = new ModelAndView("view", "item", productService.findByID(id));
        return showView;
    }

    @GetMapping("/search/{nameProd}")
    public ModelAndView search(@PathVariable String nameProd){
        ModelAndView showSearch = new ModelAndView("search", "search", productService.findByName(nameProd));
        return showSearch;
    }
}