package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ecommerce")
public class SearchController {

    @GetMapping
    public String showHomePage() {
        return "views/user/home/index"; 
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam("keyword") String keyword) {
        boolean hasSearchResult = false; 
        String searchResultHTML = "";       
        if (hasSearchResult) {
            return searchResultHTML;
        } else {
            String notFoundMessage = "<h1>No search results found for: " + keyword + "</h1>";
            return notFoundMessage;
        }
    }
}