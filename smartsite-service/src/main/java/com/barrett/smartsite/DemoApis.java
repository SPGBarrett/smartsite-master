package com.barrett.smartsite;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @program: smartsite
 * @description:
 * @author: Barrett
 * @create: 2019-10-07 14:26
 **/
@CrossOrigin
@RestController
public class DemoApis
{
    @RequestMapping(value = {"/DemoApis"}, method = {RequestMethod.GET})
    public String demoIndex() { return "This is a demo of SpringBoot Application!"; }
}
