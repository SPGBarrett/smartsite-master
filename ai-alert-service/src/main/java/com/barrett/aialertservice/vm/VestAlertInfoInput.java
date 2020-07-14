package com.barrett.aialertservice.vm;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 10:59
 **/
public class VestAlertInfoInput {
    String aid; //算法id
    String cid; //摄像头id
    String cid_url; //摄像头拉流地址
    long time_stamp; //时间戳
    int status; //状态值
    String srcpic_name; //报警输出图片关联的原始分析图片
    String srcpic_data; //原始分析图片，base64格式编码
    String pic_name; //报警图片命名
    String pic_data; //报警结果图片，base64格式编码
    Vests data;

    public VestAlertInfoInput() {
    }

    public VestAlertInfoInput(String aid, String cid, String cid_url, long time_stamp, int status, String srcpic_name, String srcpic_data, String pic_name, String pic_data, Vests data) {
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

    public Vests getData() {
        return data;
    }

    public void setData(Vests data) {
        this.data = data;
    }

    public static class Vests{
        int alert_flag;
        int alert_num;
        List<VestDetail> alert_info;

        public Vests() {
        }

        public Vests(int alert_flag, int alert_num, List<VestDetail> alert_info) {
            this.alert_flag = alert_flag;
            this.alert_num = alert_num;
            this.alert_info = alert_info;
        }

        public int getAlert_flag() {
            return alert_flag;
        }

        public void setAlert_flag(int alert_flag) {
            this.alert_flag = alert_flag;
        }

        public int getAlert_num() {
            return alert_num;
        }

        public void setAlert_num(int alert_num) {
            this.alert_num = alert_num;
        }

        public List<VestDetail> getAlert_info() {
            return alert_info;
        }

        public void setAlert_info(List<VestDetail> alert_info) {
            this.alert_info = alert_info;
        }

        public static class VestDetail{
            double confidence;
            int height; //报警框高度
            int width; //报警框宽度
            int x; //报警框左上角坐标x
            int y; //报警框左上角坐标y

            public VestDetail() {
            }

            public VestDetail(double confidence, int height, int width, int x, int y) {
                this.confidence = confidence;
                this.height = height;
                this.width = width;
                this.x = x;
                this.y = y;
            }

            public double getConfidence() {
                return confidence;
            }

            public void setConfidence(double confidence) {
                this.confidence = confidence;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
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
        }
    }
}
