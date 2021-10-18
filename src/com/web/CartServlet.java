package com.web;

import com.bean.Book;
import com.bean.Cart;
import com.bean.User;
import com.google.gson.Gson;
import com.service.BookService;
import com.service.CartService;
import com.service.impl.BookServiceImpl;
import com.service.impl.CartServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @ahuthor lzy
 * @create 2021-10-13
 */
public class CartServlet extends HttpServlet {
    CartService cartService = new CartServiceImpl();

    protected void ajaxAddToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1，获取图书编号
        int id = WebUtils.stringToInterge(req.getParameter("id"), 0);
        //判断该session域中是否又cart，若没有创建一个，若有用用相同一个cart
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cartService.addToCart(cart, id);
        Integer totalCount = cart.getTotalCount();
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        req.setAttribute("totalCnt",totalCount);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);

    }
    protected void empty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cartService.emptyCart(cart);
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        int id = WebUtils.stringToInterge(req.getParameter("id"), 0);
        cartService.delete(cart,id);
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = WebUtils.stringToInterge(req.getParameter("id"), 0);
        //判断该session域中是否又cart，若没有创建一个，若有用用相同一个cart
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cartService.addToCart(cart, id);
        resp.sendRedirect(req.getHeader("Referer"));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        try {
            Method method = CartServlet.class.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
