package com.laborator.controller;

import com.laborator.repository.CategoryRepository;
import com.laborator.repository.RecordRepository;
import com.laborator.model.*;

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

public class RecordController extends javax.servlet.http.HttpServlet {

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
        String getAll = request.getParameter("getAll");
        String captchaInput = request.getParameter("captchaInput");

        HttpSession session = request.getSession(true);

        //get user records
        List<Record> allMyRecords = (List<Record>) session.getAttribute("allMyRecords");

        if (allMyRecords == null) {
            //user is new, create list
            allMyRecords = new ArrayList<>();
        }

        if (category != null && captchaInput != null && name != null && age != null && category.length() > 0 && name.length() > 0 && age.length() > 0 && captchaInput.length() > 0) {
            Category category_obj = categoryRepository.getCategoryByName(category);

            String generated_captcha = getCookie("generatedCaptcha", request).getValue();
            if (generated_captcha != null && captchaInput.equals(generated_captcha)) {
                if (category_obj != null) {

                    addCookie("defaultValue", category, response, 360);

                    //get all records
                    if (getAll != null) {
                        addCookie("getAll", "1", response, 360);

                    } else {
                        addCookie("getAll", "0", response, 360);
                    }

                    Record temporaryRecord = new Record(name, age, category_obj);

                    if (recordRepository.addRecord(temporaryRecord) == true) {
                        allMyRecords.add(temporaryRecord);//insert

                        session.setAttribute("allMyRecords", allMyRecords);

                        RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/result.jsp");
                        errorDispatcher.forward(request, response);
                    } else {
                        //duplicate entry
                        addCookie("error", "4", response, 360);

                        RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
                        errorDispatcher.forward(request, response);
                    }
                } else {
                    //no category
                    addCookie("error", "3", response, 360);

                    RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
                    errorDispatcher.forward(request, response);
                }
            } else {
                //captcha error
                addCookie("error", "2", response, 360);

                RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
                errorDispatcher.forward(request, response);
            }
        } else {
            //poor input
            addCookie("error", "1", response, 360);

            RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
            errorDispatcher.forward(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        requestDispatcher.forward(request, response);
    }
}
