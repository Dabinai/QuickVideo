package com.quickvideo.quickvideo.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by powersen on 2017/12/5.
 * 评论
 */

public class PingLunBean {

    /**
     * msg : 成功
     * ret : {"pnum":1,"totalRecords":4,"records":20,"list":[{"msg":"超喜欢男神的这部片子，就是喜欢。","phoneNumber":"悲伤的恋曲","dataId":"ff8080815c5dce45015c5e77b07c0675","userPic":"","time":"2017-05-25 15:25:21","likeNum":0},{"msg":"没想到这个电影在手机电影APP里居然可以看到，赞一个。","phoneNumber":"伪装坚强","dataId":"ff8080815c5dce45015c5e77b0790673","userPic":"","time":"2017-05-19 20:25:21","likeNum":0},{"msg":"呵呵，这种电影，不想评价更多！","phoneNumber":"忧别人之忧","dataId":"ff8080815c5dce45015c5e77b0760672","userPic":"","time":"2017-05-18 11:25:21","likeNum":0},{"msg":"这片子早就应该看了，幸好没有错过这么好的片子。","phoneNumber":"陌路丢了谁","dataId":"ff8080815c5dce45015c5e77b07b0674","userPic":"","time":"2017-05-15 19:25:21","likeNum":0}],"totalPnum":1}
     * code : 200
     */

    public String msg;
    public RetBean ret;
    public String code;

    public static PingLunBean objectFromData(String str) {

        return new Gson().fromJson(str, PingLunBean.class);
    }

    public static class RetBean {
        /**
         * pnum : 1
         * totalRecords : 4
         * records : 20
         * list : [{"msg":"超喜欢男神的这部片子，就是喜欢。","phoneNumber":"悲伤的恋曲","dataId":"ff8080815c5dce45015c5e77b07c0675","userPic":"","time":"2017-05-25 15:25:21","likeNum":0},{"msg":"没想到这个电影在手机电影APP里居然可以看到，赞一个。","phoneNumber":"伪装坚强","dataId":"ff8080815c5dce45015c5e77b0790673","userPic":"","time":"2017-05-19 20:25:21","likeNum":0},{"msg":"呵呵，这种电影，不想评价更多！","phoneNumber":"忧别人之忧","dataId":"ff8080815c5dce45015c5e77b0760672","userPic":"","time":"2017-05-18 11:25:21","likeNum":0},{"msg":"这片子早就应该看了，幸好没有错过这么好的片子。","phoneNumber":"陌路丢了谁","dataId":"ff8080815c5dce45015c5e77b07b0674","userPic":"","time":"2017-05-15 19:25:21","likeNum":0}]
         * totalPnum : 1
         */

        public int pnum;
        public int totalRecords;
        public int records;
        public int totalPnum;
        public List<ListBean> list;

        public static RetBean objectFromData(String str) {

            return new Gson().fromJson(str, RetBean.class);
        }

        public static class ListBean {
            /**
             * msg : 超喜欢男神的这部片子，就是喜欢。
             * phoneNumber : 悲伤的恋曲
             * dataId : ff8080815c5dce45015c5e77b07c0675
             * userPic :
             * time : 2017-05-25 15:25:21
             * likeNum : 0
             */

            public String msg;
            public String phoneNumber;
            public String dataId;
            public String userPic;
            public String time;
            public int likeNum;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }
        }
    }
}
