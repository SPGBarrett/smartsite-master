package com.barrett.aialertservice.vm;

import lombok.Data;

import java.util.List;

/**
 * @program: smartsite-master
 * @description: Helmet alert info from AI module
 * @author: Barrett
 * @create: 2020-05-05 13:59
 **/

public class HelmetAlertInfoInput {
    String aid; //算法id
    String cid; //摄像头id
    String cid_url; //摄像头拉流地址
    long time_stamp; //时间戳
    int status; //状态值
    String srcpic_name; //报警输出图片关联的原始分析图片
    String srcpic_data; //原始分析图片，base64格式编码
    String pic_name; //报警图片命名
    String pic_data; //报警结果图片，base64格式编码
    Heads data;

    public HelmetAlertInfoInput() {
    }

    public HelmetAlertInfoInput(String aid, String cid, String cid_url, long time_stamp, int status, String srcpic_name, String srcpic_data, String pic_name, String pic_data, Heads data) {
        this.aid = aid;
        this.cid = cid;
        this.cid_url = cid_url;
        this.time_stamp = time_stamp;
        this.status = status;
        this.srcpic_name = srcpic_name;
        this.srcpic_data = srcpic_data;
        this.pic_name = pic_name;
        this.pic_data = pic_data;
        this.data = data;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCid_url() {
        return cid_url;
    }

    public void setCid_url(String cid_url) {
        this.cid_url = cid_url;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSrcpic_name() {
        return srcpic_name;
    }

    public void setSrcpic_name(String srcpic_name) {
        this.srcpic_name = srcpic_name;
    }

    public String getSrcpic_data() {
        return srcpic_data;
    }

    public void setSrcpic_data(String srcpic_data) {
        this.srcpic_data = srcpic_data;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public String getPic_data() {
        return pic_data;
    }

    public void setPic_data(String pic_data) {
        this.pic_data = pic_data;
    }

    public Heads getData() {
        return data;
    }

    public void setData(Heads data) {
        this.data = data;
    }


    public static class Heads{
        int numOfHead; //检测到未戴安全帽的数量
        int alertFlag;
        List<HeadDetail> headInfo;

        public Heads() {
        }

        public Heads(int numOfHead, int alertFlag, List<HeadDetail> headInfo) {
            this.numOfHead = numOfHead;
            this.alertFlag = alertFlag;
            this.headInfo = headInfo;
        }

        public int getNumOfHead() {
            return numOfHead;
        }

        public void setNumOfHead(int numOfHead) {
            this.numOfHead = numOfHead;
        }

        public int getAlertFlag() {
            return alertFlag;
        }

        public void setAlertFlag(int alertFlag) {
            this.alertFlag = alertFlag;
        }

        public List<HeadDetail> getHeadInfo() {
            return headInfo;
        }

        public void setHeadInfo(List<HeadDetail> headInfo) {
            this.headInfo = headInfo;
        }

        public static class HeadDetail{
            int x; //报警框左上角坐标x
            int y; //报警框左上角坐标y
            int width; //报警框宽度
            int height; //报警框高度
            int numOfHelmet; //安全帽数量
            String color; //安全帽颜色

            public HeadDetail() {
            }

            public HeadDetail(int x, int y, int width, int height, int numOfHelmet, String color) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.numOfHelmet = numOfHelmet;
                this.color = color;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getNumOfHelmet() {
                return numOfHelmet;
            }

            public void setNumOfHelmet(int numOfHelmet) {
                this.numOfHelmet = numOfHelmet;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}
