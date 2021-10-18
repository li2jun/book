package com.web;

import com.bean.User;
import com.google.gson.Gson;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().removeAttribute("user");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(null,username,password,null);
        User user1 = userService.login(user);
        if(user1!=null){
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("user", user1);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
    protected void ajaxExistName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        boolean b = userService.existsUsername(username);
        Map<String,Object> existUsernameMap = new HashMap<>();
        existUsernameMap.put("existUsernameMap",b);
        Gson gson = new Gson();
        String s = gson.toJson(existUsernameMap);
        resp.getWriter().write(s);
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
/*        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");*/
        User user = WebUtils.copyParaToBean(req.getParameterMap(), new User());
        //获取动态验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if(req.getParameter("code").equalsIgnoreCase(token)){
            if(!userService.existsUsername(user.getUsername())){
                userService.registerUser(user);
                req.getSession().setAttribute("username", req.getParameter("username"));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

            }else {
                req.setAttribute("msg", "该用户名已经存在");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        }else{
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method method =UserServlet.class.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
