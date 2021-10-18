package com.web;

import com.bean.Book;
import com.bean.Page;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @ahuthor lzy
 * @create 2021-10-09
 */
public class BookServlet extends HttpServlet {
    private  BookService bookService = new BookServiceImpl();
    protected void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageNo = WebUtils.stringToInterge(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.stringToInterge(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int pageTotalCount = bookService.queryPageTotalCount();
        List<Book> books = bookService.queryPageItems(pageNo, pageSize);
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0){
            pageTotal+=1;
        }
        String url = "manager/bookServlet?action=pageQuery";
        Page<Book> page = new Page<Book>(pageNo,pageTotal,pageTotalCount,url,books);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        bookService.deleteBookById(i);
        int pageNo = WebUtils.stringToInterge(req.getParameter("pageNo"), 1);
        resp.sendRedirect("/book/manager/bookServlet?action=pageQuery&pageNo=" + pageNo);

    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = new Book();
        WebUtils.copyParaToBean(parameterMap,book);
        bookService.updateBookById(book);
        int pageNo = WebUtils.stringToInterge(req.getParameter("pageNo"), 1);
        resp.sendRedirect("/book/manager/bookServlet?action=pageQuery&pageNo=" + pageNo);
    }
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = new Book();
        WebUtils.copyParaToBean(parameterMap, book);
        bookService.addBook(book);
        int pageTotal = WebUtils.stringToInterge(req.getParameter("pageTotal"), 1);
        resp.sendRedirect("/book/manager/bookServlet?action=pageQuery&pageNo="+ pageTotal);
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        Book book = bookService.queryBookById(i);
        //在req.setAttribute中要用请求转发，来获取域对象中的值
        req.setAttribute("book", book);
        int pageNo = WebUtils.stringToInterge(req.getParameter("pageNo"), 1) + 1;
        req.getRequestDispatcher("/pages/manager/book_edit.jsp?action=update&id="+i+"&pageNo="+pageNo ).forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method method = BookServlet.class.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
