package com.barrett.aialertservice.vm;

import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-05-16 10:58
 **/
public class ClothesAlertInfoInput {
    String aid; //算法id
    String cid; //摄像头id
    String cid_url; //摄像头拉流地址
    long time_stamp; //时间戳
    int status; //状态值
    String srcpic_name; //报警输出图片关联的原始分析图片
    String srcpic_data; //原始分析图片，base64格式编码
    String pic_name; //报警图片命名
    String pic_data; //报警结果图片，base64格式编码
    Clothes data;

    public ClothesAlertInfoInput() {
    }

    public ClothesAlertInfoInput(String aid, String cid, String cid_url, long time_stamp, int status, String srcpic_name, String srcpic_data, String pic_name, String pic_data, Clothes data) {
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

    public Clothes getData() {
        return data;
    }

    public void setData(Clothes data) {
        this.data = data;
    }

    public static class Clothes {
        int alert_flag;
        List<ClothesDetail> body_info;
        int report_num;

        public Clothes() {
        }

        public Clothes(int alert_flag, List<ClothesDetail> body_info, int report_num) {
            this.alert_flag = alert_flag;
            this.body_info = body_info;
            this.report_num = report_num;
        }

        public int getAlert_flag() {
            return alert_flag;
        }

        public void setAlert_flag(int alert_flag) {
            this.alert_flag = alert_flag;
        }

        public List<ClothesDetail> getBody_info() {
            return body_info;
        }

        public void setBody_info(List<ClothesDetail> body_info) {
            this.body_info = body_info;
        }

        public int getReport_num() {
            return report_num;
        }

        public void setReport_num(int report_num) {
            this.report_num = report_num;
        }

        public static class ClothesDetail {
            int height; //报警框高度
            int width; //报警框宽度
            int x; //报警框左上角坐标x
            int y; //报警框左上角坐标y

            public ClothesDetail() {
            }

            public ClothesDetail(int x, int y, int width, int height) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
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
        }
    }

}
