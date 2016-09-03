package com.cms.app.controller;

import com.cms.app.model.User;
import com.cms.app.service.UserService;
import com.cms.app.validators.UserFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller class to handle requests related user login
 *
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    UserFormValidator userFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }

    /**
     * Method to get handle get get request to login page.
     *
     * @param error
     * @return the view name need to be render.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, @RequestParam(value = "error",required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout){
        model.addAttribute("error",error);
        if(logout != null) {
            model.addAttribute("logout", true);
        }
        return "login";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userForm")@Validated User user,
                             BindingResult result, Model model, final RedirectAttributes redirectAttributes){

        logger.debug("saveOrUpdateUser() : {}", user);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("error", "Username Already Exist");
            model.addAttribute("userForm",user);
            return "admin";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            userService.save(user);
            return "redirect:/admin";
        }
    }

    @RequestMapping(value = "/user/suspend", method = RequestMethod.GET)
    public String updateUserEnabled(@ModelAttribute("userForm") User user, @RequestParam(value = "suspend") String suspend,@RequestParam(value = "enabled") Boolean enabled,
                             BindingResult result, Model model, final RedirectAttributes redirectAttributes){

        user.setUsername(suspend);
        user.setEnabled(enabled);
        logger.debug("saveOrUpdateUser() : {}", user);
        userService.updateUserEnabled(user);
        return "redirect:/admin";
    }

}
