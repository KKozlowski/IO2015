package io.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  public String index(HttpSession h) {
    return "SESSION ID: " + h.getId() + "<br />Proudly handcrafted by " +
        "<b>pankamil</b> :)"+
    	"<br /><a href='/about'>CLICK</a>";
  }
  
  @RequestMapping("/about")
  @ResponseBody
  public String about() {
    return "First try.";
  }

}
