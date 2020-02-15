package com.android.discovery.viewtest;

import com.android.discovery.viewtest.Bean.BookBean;
import com.android.discovery.viewtest.Bean.BookDetailBean;
import com.android.discovery.viewtest.Bean.BorrowBean;
import com.android.discovery.viewtest.Bean.BorrowDetailBean;
import com.android.discovery.viewtest.Bean.FindBookBean;
import com.android.discovery.viewtest.Bean.LoginBean;
import com.android.discovery.viewtest.Bean.ReSetPasswordBean;
import com.android.discovery.viewtest.Bean.RegBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("api/user/login")
    Observable<LoginBean> getUserInfo(@Query("user_name") String name,
                                      @Query("user_password") String password);

    //注册
    @GET("api/user/reg")
    Observable<RegBean> regUser(@Query("user_name") String name,
                                @Query("user_phone") String phone,
                                @Query("user_password") String password,
                                @Query("user_sex") String sex,
                                @Query("user_age") String age);
    //修改密码
    @GET("api/user/repassword")
    Observable<ReSetPasswordBean> reSetPassword(@Query("user_name") String name,
                                          @Query("user_phone") String phone,
                                          @Query("newpassword") String password);

    //书籍列表
    @GET("api/Book/booklist")
    Observable<List<BookBean>> getBookList();

    //搜索图书
    @GET("api/Book/bookfind")
    Observable<List<FindBookBean>> findBookList(@Query("book_name") String bookName);

    //书籍列表
    @GET("api/Book/bookdetails")
    Observable<BookDetailBean> getBookDetail(@Query("book_id") int bookId);

    //借阅书籍
    @GET("api/Book/borrowbook")
    Observable<BorrowBean> borrowBook(@Query("book_id") int bookId,
                                      @Query("user_id") int userId);

    //借阅列表
    @GET("api/user/getreadlist")
    Observable<List<BorrowDetailBean>> getBorrowBookList(@Query("user_id") int userId);

    //借阅列表
    @GET("api/user/getrecordlist")
    Observable<List<BorrowDetailBean>> getBorrowBookReadyList(@Query("user_id") int userId);




}
