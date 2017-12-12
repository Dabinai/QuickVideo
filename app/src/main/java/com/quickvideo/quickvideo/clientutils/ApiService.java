package com.quickvideo.quickvideo.clientutils;

import com.quickvideo.quickvideo.bean.PinDaoBean;
import com.quickvideo.quickvideo.bean.PingLunBean;
import com.quickvideo.quickvideo.bean.ShouYeBean;
import com.quickvideo.quickvideo.bean.SouSuoBean;
import com.quickvideo.quickvideo.bean.XiangQingBean;
import com.quickvideo.quickvideo.leftmenu.menubean.GankBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by powersen on 2017/12/5.
 * 请求数据的方法
 * Get请求：getShouYeData
 * Post请求：频道 getPinDaoData
 *          详情 getXiangQData
 *          搜索 getSouSuoData
 *          评论列表 getPLunData
 */

public interface ApiService {
    //    首页：http://api.svipmovie.com/front/homePageApi/homePage.do 请求方式：GET
    @GET("front/homePageApi/homePage.do")
    Observable<ShouYeBean> getShouYeData();

    //频道：http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=5
    @FormUrlEncoded
    @POST("front/columns/getVideoList.do")
    Observable<PinDaoBean> getPinDaoData(@FieldMap HashMap<String, String> map);

    //详情：http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=70cddbf9d84b4c72bd4311952f03b6d4
    @FormUrlEncoded
    @POST("front/videoDetailApi/videoDetail.do")
    Observable<XiangQingBean> getXiangQData(@FieldMap HashMap<String, String> map);

    //搜索：http://api.svipmovie.com/front/searchKeyWordApi/getVideoListByKeyWord.do?keyword=fd1b4f9a86b547e58acfd78320ee0867&pnum=1
    @FormUrlEncoded
    @POST("front/searchKeyWordApi/getVideoListByKeyWord.do")
    Observable<SouSuoBean> getSouSuoData(@FieldMap HashMap<String, String> map);

    //    评论列表：http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId=CMCC_00000000000000001_621653189
    @FormUrlEncoded
    @POST("front/Commentary/getCommentList.do")
    Observable<PingLunBean> getPLunData(@FieldMap HashMap<String, String> map);

    /** http://gank.io/api/data/福利/10/1
     * 福利列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankBean> getGirlList(@Path("num") int num, @Path("page") int page);

}

