package com.java.laborator;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.TreeMap;
import java.util.Set;

public class ProblemaUnu extends javax.servlet.http.HttpServlet {

    private Map<String,String> storage;
    private PrintWriter out;

    private OutputStream output;
    private Properties prop;

    @Override
    public void init() throws ServletException {
        super.init();
        storage=new TreeMap<>();
        try {
            output = new FileOutputStream(new File("output.properties"),true);
        }catch (Exception e){
            e.printStackTrace();
        }
        prop=new Properties();
    }

    private void log(HttpServletRequest request,ServletContext servletContext){
        servletContext.log("-------------------");
        servletContext.log(request.getMethod());
        servletContext.log(request.getHeader("User-Agent"));
        servletContext.log(request.getHeader("HTTP_X_FORWARDED_FOR"));
        servletContext.log(request.getHeader("Language"));
        servletContext.log("-------------------");
    }

    private void printHTML(HttpServletResponse response){
        out.print("<html><head><title>Hello</title></head>");
        out.print("<body><div>");

        Set<String> keySet;
        synchronized (this){
            keySet=storage.keySet();
        }

        for(String iterator : keySet){
            out.print("<p> Key is "+iterator+" value is "+storage.get(iterator));
        }
        out.print("</div></body></html>");
    }

    private void printDesktop(HttpServletResponse response){
        Set<String> keySet;
        synchronized (this){
            keySet=storage.keySet();
        }
        for(String iterator : keySet){
            out.print(iterator+'='+storage.get(iterator)+',');
        }
    }

    private synchronized void printFile(String key, String value){
        prop.setProperty(key,value);
        try {
            prop.store(output, null);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        out = new PrintWriter(response.getWriter());

        String key=request.getParameter("key");
        String value=request.getParameter("value");

        if(key.length()!=0 && value.length()!=0)
            synchronized (this) {
                storage.put(key, value);
            }

        log(request,getServletContext());

        String client=request.getHeader("User-Agent");

        if(client.contains("Mozilla")||client.contains("Chrome")||client.contains("Safari"))
            printHTML(response);
        else
          printDesktop(response);

        printFile(key,value);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }
}
