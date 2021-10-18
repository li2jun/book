package com.web;

import com.bean.Book;
import com.bean.Page;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ahuthor lzy
 * @create 2021-10-11
 */
public class ClientBookServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    protected void priceQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int minPrice = WebUtils.stringToInterge(req.getParameter("min"), 0);
        if(minPrice < 0){
            minPrice = 0;
        }
        int maxPrice = WebUtils.stringToInterge(req.getParameter("max"), Integer.MAX_VALUE);
        if(maxPrice < 0){
            maxPrice = 0;
        }
        int pageNo = WebUtils.stringToInterge(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.stringToInterge(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageTotalCount = bookService.queryPageItemsByPriceCount(minPrice, maxPrice);
        List<Book> books = bookService.queryPageItemsByPrice(pageNo, pageSize, minPrice, maxPrice);
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0){
            pageTotal+=1;
        }
        req.setAttribute("min", minPrice);
        req.setAttribute("max", maxPrice);
        String url = "client/bookServlet?action=priceQuery&min="+minPrice+"&max="+maxPrice;
        Page<Book> page = new Page<Book>(pageNo,pageTotal,pageTotalCount,url,books);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageNo = WebUtils.stringToInterge(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.stringToInterge(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageTotalCount = bookService.queryPageTotalCount();
        List<Book> books = bookService.queryPageItems(pageNo, pageSize);
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0){
            pageTotal+=1;
        }
        String url = "client/bookServlet?action=pageQuery";
        Page<Book> page = new Page<Book>(pageNo,pageTotal,pageTotalCount,url,books);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method method = ClientBookServlet.class.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
