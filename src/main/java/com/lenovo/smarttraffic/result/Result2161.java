package com.lenovo.smarttraffic.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author: LBX
 * date:   On 2019/5/31
 */
public class Result2161 {

    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"id":1,"name":"故宫","ticket":80,"img":"/images/spot/gugong.png","info":"北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。","tel":"010-88888888","rating":5}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAILBean> ROWSDETAIL;

    public static Result2161 objectFromData(String str) {

        return new Gson().fromJson(str, Result2161.class);
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<ROWSDETAILBean> getROWSDETAIL() {
        return ROWSDETAIL;
    }

    public void setROWSDETAIL(List<ROWSDETAILBean> ROWSDETAIL) {
        this.ROWSDETAIL = ROWSDETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * id : 1
         * name : 故宫
         * ticket : 80
         * img : /images/spot/gugong.png
         * info : 北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。
         * tel : 010-88888888
         * rating : 5
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("ticket")
        private int ticket;
        @SerializedName("img")
        private String img;
        @SerializedName("info")
        private String info;
        @SerializedName("tel")
        private String tel;
        @SerializedName("rating")
        private int rating;

        public static ROWSDETAILBean objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAILBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTicket() {
            return ticket;
        }

        public void setTicket(int ticket) {
            this.ticket = ticket;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
    }
}
