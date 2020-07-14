package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: java bean
 * @author: Barrett
 * @create: 2019-10-07 10:09
 **/
public class MultiFiles {
    private int id;
    private String path;
    private String suffix;
    private String time;
    private String url_link;
    private String parent;
    private String type;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getPath() {
        return this.path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public String getSuffix() {
        return this.suffix;
    }


    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getUrl_link() {
        return this.url_link;
    }


    public void setUrl_link(String url_link) {
        this.url_link = url_link;
    }


    public String getParent() {
        return this.parent;
    }


    public void setParent(String parent) {
        this.parent = parent;
    }


    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public MultiFiles() {
    }


    public MultiFiles(int id, String path, String suffix, String time, String url_link, String parent, String type) {
        this.id = id;
        this.path = path;
        this.suffix = suffix;
        this.time = time;
        this.url_link = url_link;
        this.parent = parent;
        this.type = type;
    }
}
