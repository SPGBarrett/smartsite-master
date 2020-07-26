package com.barrett.assistservice.vm;

import lombok.Data;

@Data
public class PMInputBean {
    int itype;
    String time;
    String cono;
    String data;
    int etype;
    String sign;
    int dtype; // 开发者类型:1企业开发者 2平台开发者 默认为空代表企业开发者
    String dkey;
}
