package com.java.bean;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "fileUploadManagedBean")
@SessionScoped
public class FileUploadManagedBean {
    UploadedFile file;

    String typeOfFile;

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void fileUploadListener(FileUploadEvent e) {
        this.file = e.getFile();
        this.typeOfFile = file.getContentType();
    }
}