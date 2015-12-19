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
    return "SESSION ID: " + h.getId() + "<br />"+
    		"<a href='/netLogin'>Zaloguj się jako klient</a> - <a href='/innerLogin'>Zaloguj się jako pracownik</a>" +
    		"<br /><a href='/about'>ABOUT</a>";
  }
  
  @RequestMapping("/about")
  @ResponseBody
  public String about() {
    return "First try.";
  }

}
