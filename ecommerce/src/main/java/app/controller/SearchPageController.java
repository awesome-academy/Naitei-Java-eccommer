package app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
public class SearchPageController {
	@Controller
	public class SearchpageController {

	    @RequestMapping("/")
	    public String homePage() {
	        return "views/user/home/index";
	    }

	    @RequestMapping("/{category}")
	    public ResponseEntity<String> displayCategory(@PathVariable("category") String category) {
	        
	        String htmlContent = "<h2>You clicked on: " + category + "</h2>";

	       
	        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
	    }
	}
	
}
