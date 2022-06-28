package com.shorturl.controller;

import com.shorturl.model.URLEntity;
import com.shorturl.service.URLService;
import com.shorturl.util.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class URLController {
    private final URLService urlService;
    private final URLValidator urlValidator;

    @Autowired
    public URLController(URLService urlService, URLValidator urlValidator) {
        this.urlService = urlService;
        this.urlValidator = urlValidator;
    }


    @GetMapping("/")
    public String mainPage(Model model, @ModelAttribute("urlEntity") URLEntity urlEntity) {
        return "main";
    }

    @PostMapping("/")
    public String shorterPage(Model model, @Valid @ModelAttribute("urlEntity") URLEntity urlEntity,
                              HttpServletRequest http, BindingResult bindingResult) {
        String hostName = http.getRequestURL().toString();

        urlValidator.validate(urlEntity, bindingResult);

        if (bindingResult.hasErrors()) {
            return "main";
        }


        URLEntity entity = urlService.saveUrl(urlEntity, urlEntity.getPrimaryUrl());

        model.addAttribute("url", hostName + entity.getConvertedUrl());
        return "main";
    }

    @GetMapping("/{path}")
    public String redirectPage(@PathVariable("path") String path) {
        System.out.println(path);
        String url = urlService.findByConvertedUrl(path).getPrimaryUrl();
        // System.out.println(url);
        return "redirect:" + url;
    }
}
