package com.laborator.controller;

import com.laborator.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = UserRepository.getInstance();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && username.length() > 0 && password.length() > 0 && userRepository.login(username, password) == true) {
            req.getSession().setAttribute("user", userRepository.getUserByUsername(username));

            //get input
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/input.jsp");
            rd.forward(req, resp);
        } else {
            //error at login, redirect to login with error
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.include(req, resp);
    }
}
