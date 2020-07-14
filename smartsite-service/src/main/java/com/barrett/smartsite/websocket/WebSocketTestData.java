package com.barrett.smartsite.websocket;

import lombok.Data;

/**
 * @program: smartsite
 * @description: Java bean for web socket test
 * @author: Barrett
 * @create: 2019-10-14 11:47
 **/
@Data
public class WebSocketTestData {
    public String displayHead;
    public String msgBody;
    public String testInfo;
    public int index;
}
