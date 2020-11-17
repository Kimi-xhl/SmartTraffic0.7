package com.lenovo.smarttraffic.result;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author: LBX
 * date:   On 2019/5/31
 */
public class Result2171 {

    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"id":1,"category":1,"title":"新闻标题","content":"新闻内容","createtime":"2016-05-21 08:19:21"},{"id":2,"category":2,"title":"测试1","content":"内容2","createtime":"2018-05-14 22:48:45"},{"id":3,"category":3,"title":"测试3","content":"内容3","createtime":"2018-05-14 22:49:08"},{"id":4,"category":4,"title":"测试4","content":"内容4","createtime":"2018-05-14 22:49:15"}]
     * RESULT : S
     */

    @SerializedName("ERRMSG")
    private String ERRMSG;
    @SerializedName("RESULT")
    private String RESULT;
    @SerializedName("ROWS_DETAIL")
    private List<ROWSDETAILBean> ROWSDETAIL;

    public static Result2171 objectFromData(String str) {

        return new Gson().fromJson(str, Result2171.class);
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
         * category : 1
         * title : 新闻标题
         * content : 新闻内容
         * createtime : 2016-05-21 08:19:21
         */

        @SerializedName("id")
        private int id;
        @SerializedName("category")
        private int category;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("createtime")
        private String createtime;

        public static ROWSDETAILBean objectFromData(String str) {

            return new Gson().fromJson(str, ROWSDETAILBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
