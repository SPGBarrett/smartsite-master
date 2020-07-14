package com.barrett.smartsite.bean;

/**
 * @program: smartsite
 * @description: Java bean
 * @author: Barrett
 * @create: 2019-10-07 10:04
 **/
public class DemoTest {
    private String param_one;
    private String param_two;
    private String demo_one;

    public String getParam_one() {
        return this.param_one;
    }


    public void setParam_one(String param_one) {
        this.param_one = param_one;
    }


    public String getParam_two() {
        return this.param_two;
    }


    public void setParam_two(String param_two) {
        this.param_two = param_two;
    }


    public String getDemo_one() {
        return this.demo_one;
    }


    public void setDemo_one(String demo_one) {
        this.demo_one = demo_one;
    }


    public DemoTest(String param_one, String param_two, String demo_one) {
        this.param_one = param_one;
        this.param_two = param_two;
        this.demo_one = demo_one;
    }
}
