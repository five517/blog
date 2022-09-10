package com.mario.web;

import com.mario.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
    @RequestMapping("/")
    public String test(){
        return "index";
    }
    @GetMapping("/t1")
    public String test1(){
        int i=5/0;
        return "test";
    }
    @GetMapping("/t2")
    public String test2(){
        String blog=null;
        if(blog==null){
            throw new NotFoundException("博客不存在");
        }
        return "test";
    }
//    @RequestMapping("/{id}/{name}")
//    public String test3(@PathVariable("id") int id,@PathVariable("name") String name){
//        System.out.println("-------test01-------");
//        return "test01";
//    }

}
