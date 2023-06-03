package com.neu.group.controller.utils;

import javax.servlet.http.Part;

public class UpLoadFileUtil {
    // 从Part中提取上传文件名
    static public String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1) ;
            }
        }
        return "";
    }
}
