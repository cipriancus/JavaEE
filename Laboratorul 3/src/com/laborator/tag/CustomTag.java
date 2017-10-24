package com.laborator.tag;


import com.laborator.model.Record;
import com.laborator.repository.RecordRepository;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CustomTag extends SimpleTagSupport {
    RecordRepository recordRepository= RecordRepository.getInstance();

    private String name;
    private String category;

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Record record = recordRepository.getRecordByName(name,category);
        if(record!=null){
            out.println("Record " + name +  " has age " + record.getAge());
        }else{
            out.println("Does not exist");
        }
    }
}