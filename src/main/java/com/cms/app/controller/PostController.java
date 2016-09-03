package com.cms.app.controller;

import com.cms.app.model.Post;
import com.cms.app.service.PostService;
import com.cms.app.service.RoleService;
import com.cms.app.utils.CustomUtill;
import com.cms.app.utils.PostStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by burusothman on 8/31/16.
 */
@Controller
public class PostController{

    @Autowired
    PostService postService;
    @Autowired
    RoleService roleService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws ServletException {
        // Convert multipart object to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

    }

    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute("postForm") Post post, @RequestParam String action,BindingResult result,
                        Model model,HttpServletRequest request, final RedirectAttributes redirectAttributes) {

        if (action.equals("preview")) {
            post.setStatus(PostStatus.Draft);
            CustomUtill.converStringfromByte(post);
            redirectAttributes.addFlashAttribute("post", post);

            return "redirect:/post/preview";
        } else if (action.equals("draft")) {
            post.setStatus(PostStatus.Draft);
            postService.createPost(post);
            CustomUtill.converStringfromByte(post);

            model.addAttribute("post", post);

            return "postView";

        } else if (action.equals("publish")) {
            post.setStatus(PostStatus.ReadyPublish);
            postService.createPost(post);
            CustomUtill.converStringfromByte(post);
            return "page";
        }

        return "home";
    }

    @RequestMapping(value = "/post/view", method = RequestMethod.GET)
    public String showPost(HttpServletResponse response,HttpServletRequest request, Model model ) {
        if(roleService.isAdminUser()) {
            List<Post> posts = postService.getAll();
            for(Post post : posts){
                CustomUtill.converStringfromByte(post);
            }
            model.addAttribute("posts", posts);
            return "view";
        }else{
            List<Post> posts = postService.getAllByUser();
            for(Post post : posts){
                CustomUtill.converStringfromByte(post);
            }
            model.addAttribute("posts", posts);
            return "viewUser";
        }
    }

    @RequestMapping(value = "/post/viewUser", method = RequestMethod.GET)
    public String showUserPost(HttpServletResponse response,HttpServletRequest request, Model model ) {

            List<Post> posts = postService.getAllByUser();
            model.addAttribute("posts", posts);
            return "viewUser";
    }

    @RequestMapping(value = "/post/state", method = RequestMethod.POST)
    public String changeState(HttpServletResponse response,HttpServletRequest request, Model model ) {
        int id = ServletRequestUtils.getIntParameter(request, "id", 0);
        String date = ServletRequestUtils.getStringParameter(request, "date","");
        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        if(id > 0){
            Post post = postService.get(id);
            PostStatus status = post.getStatus();
            post.setStatus(status.next());
            if(post.getStatus() == PostStatus.Published){
                if(date.trim().length()>0){
                    try {
                        post.setPublish(sourceFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                post.setPublish(new Date());
            }
            postService.updatePost(post);
        }

        if(roleService.isAdminUser()) {
            List<Post> posts = postService.getAll();
            for(Post post : posts){
                CustomUtill.converStringfromByte(post);
            }
            model.addAttribute("posts", posts);
            return "view";
        }else{
            List<Post> posts = postService.getAllByUser();
            for(Post post : posts){
                CustomUtill.converStringfromByte(post);
            }
            model.addAttribute("posts", posts);
            return "viewUser";
        }
    }

    @RequestMapping(value = "/post/preview", method = RequestMethod.GET)
    public String showPreview(HttpServletResponse response,HttpServletRequest request, Model model, @ModelAttribute("post") final Post post ) {
        model.addAttribute("post",post);
        return "page";
    }

    @RequestMapping(value = "/post/previews", method = RequestMethod.POST)
    public String previews(HttpServletResponse response,HttpServletRequest request, Model model,final RedirectAttributes redirectAttributes ) {
        int id = ServletRequestUtils.getIntParameter(request, "id", 0);
        Post post = postService.get(id);
        CustomUtill.converStringfromByte(post);
        redirectAttributes.addFlashAttribute("post", post);

        return "redirect:/post/preview";
    }
    @RequestMapping(value = "/post/edit", method = RequestMethod.POST)
    public String edit(HttpServletResponse response,HttpServletRequest request, Model model,final RedirectAttributes redirectAttributes ) {
        int id = ServletRequestUtils.getIntParameter(request, "id", 0);
        Post post = postService.get(id);
        CustomUtill.converStringfromByte(post);
        model.addAttribute("post", post);
        return "home";
    }


    }
