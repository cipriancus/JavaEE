package com.laborator.controller;


import com.laborator.model.Category;
import com.laborator.model.Record;
import com.laborator.repository.CategoryRepository;
import com.laborator.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

public class InputController extends HttpServlet {
    private RecordRepository recordRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        recordRepository = RecordRepository.getInstance();
        categoryRepository = CategoryRepository.getInstance();
    }

    private void addCookie(String name, String value, HttpServletResponse response, int ttl) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(ttl);//time to live
        response.addCookie(cookie);
    }

    private Cookie getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies)
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        return null;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        HttpSession session = request.getSession(true);

        //get user records
        List<Record> allMyRecords = (List<Record>) session.getAttribute("allMyRecords");

        if (allMyRecords == null) {
            //user is new, create list
            allMyRecords = new ArrayList<>();
        }

        if (category != null && name != null && age != null && category.length() > 0 && name.length() > 0 && age.length() > 0) {
            Category category_obj = categoryRepository.getCategoryByName(category);

            if (category_obj != null) {

                addCookie("defaultValue", category, response, 360);

                Record temporaryRecord = new Record(name, age, category_obj);

                if (recordRepository.addRecord(temporaryRecord) == true) {
                    allMyRecords.add(temporaryRecord);//insert

                    session.setAttribute("allMyRecords", allMyRecords);

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/result.jsp");
                    requestDispatcher.include(request, response);
                } else {
                    //duplicate entry
                    doGet(request,response);
                }
            } else {
                //no category
                doGet(request,response);
            }
        } else {
            //poor input
            doGet(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/input.jsp");
        requestDispatcher.include(request, response);
    }
}
