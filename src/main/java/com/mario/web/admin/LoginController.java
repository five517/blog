package com.mario.web.admin;


import com.mario.pojo.User;
import com.mario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    //注入Service
    @Autowired
    private UserService userService;
    //跳转到登录页面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    //跳转到登录成功页面
    @PostMapping("/login")
    //@RequestParam 表示该参数必须要有
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            //防止前端得到密码
            user.setPassword(null);
            /*这个bug找了5天！！！！！！！！！！！！！！！！*/
            session.setAttribute("user", user);
            //用户存在则到登录成功页面
            return "admin/index";
        } else {
            //用户名密码错误
            //重定向回登录页面
            //model只适合当前请求域，重定向后是另外一个请求，所以这里用RedirectAttributes
            attributes.addFlashAttribute("message","别试了，别试了，你进不去的！");
            return "redirect:/admin";
        }
    }
    //注销,需要清空session
    @GetMapping("/logout")
    public String Logout(HttpSession session){
    //        session.invalidate()是销毁跟用户关联session,例如有的用户强制关闭浏览器,而跟踪用户的信息的session还存在,可是用户已经离开了。
    //虽然session 生命周期浏览默认时间30分,但是在30分钟内别的用户还可以访问到前一个用户的页面,需销毁用户的session。
    //session.removeAttribute()移除session中的某项属性。
    //在spring例子中宠物商店的注销登录的代码：
    //request.getSession().removeAttribute("userSession");
    ////    注销用户，使session失效。
    //request.getSession().invalidate();
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
