package wocap.neusoft.com.xiaolongbaomanage.manage;

import android.content.Context;


import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import wocap.neusoft.com.xiaolongbaomanage.api.xiaolongbaoapi;
import wocap.neusoft.com.xiaolongbaomanage.http.Constants;
import wocap.neusoft.com.xiaolongbaomanage.http.MyGsonConverterFactory;

/**
 * Created by wangm on 2017/2/18.
 */

public class RetrofitHelper {
    private Context mCntext;
    OkHttpClient client ;
    private xiaolongbaoapi doubanapi;
    private static RetrofitHelper instance = null;


    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        mCntext = mContext;
        init();
    }
    private void init() {
        resetApp();
    }

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Logger.d(message);
//            Log.i("RetrofitLog","retrofitBack = "+message);
        }
    });

    private void initOkHttp(){
        if(client == null){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            client = new OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)
                    .cookieJar(new CookieJar() {
                        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url, cookies);
                            cookieStore.put(HttpUrl.parse(url.host()), cookies);
                            for(Cookie cookie:cookies){
                                System.out.println("cookie Name:"+cookie.name());
                                System.out.println("cookie Path:"+cookie.path());
                            }
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                            if(cookies==null){
                                System.out.println("没加载到cookie");
                            }
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    })
                    .build();
        }
    }
    private void resetApp() {


    }

    /**
     * 豆瓣接口请求
     */
    public xiaolongbaoapi getDoubanapi(){
        initOkHttp();
        if (doubanapi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl( Constants.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(MyGsonConverterFactory.create(new GsonBuilder().create()))
                    .build();
            doubanapi = retrofit.create(xiaolongbaoapi.class);
        }
        return doubanapi;
    }








}
