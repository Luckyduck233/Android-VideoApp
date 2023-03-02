package com.example.videoapp.api;

public class ApiConfig {
    public static final String BASE_URL = "http://192.168.31.32:8080/renren-fast";
    public static final String BASE_URL2 = "http://47.112.180.188:8080/renren-fast";
    public static final String LOGIN = "/app/login";
    public static final String REGISTER = "/app/register";
    public static final String VIDEO_LIST = "/app/videolist/list";
//    各类型视频列表
    public static final String VIDEO_LIST_BY_CATEGORY = "/app/videolist/getListByCategoryId";
    //    视频类型列表
    public static final String VIDEO_CATEGORY_LIST = "/app/videocategory/list";

    //    资讯列表
    public static final String NEWS_LIST = "/app/news/api/list";

    public static final String SP_TOKEN_NAME = "sp_ttit";
    public static final int PAGE_SIZE = 5;
//    随机头像
    public static final String RANDOM_HEAD = "https://api.btstu.cn/sjtx/api.php";


//    easy mock
    public static final String EM_BASE_URL = "https://mock.presstime.cn/mock/63e33df047892c65279783be/example";

    public static final String EM_LOGIN = "/login";

    public static final String EM_VIDEO_LIST_TEST = "/getVideoListTest";

    public static final String EM_TEST = "/test";

    public static final String EM_NEWS_LIST = "/getNewsList";

    //    fast mock
    public static final String FM_BASE_URL = "https://www.fastmock.site/mock/95f4f67a1d4e83457a1256a0e118556d/get_test";

    public static final String FM_TEST_TIMEOUT = "/api/testTimeout";
}
