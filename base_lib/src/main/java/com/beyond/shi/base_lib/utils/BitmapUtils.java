package com.beyond.shi.base_lib.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.beyond.shi.base_lib.utils.transformation.CropCircleTransformation;
import com.beyond.shi.base_lib.utils.transformation.CropSquareTransformation;
import com.beyond.shi.base_lib.utils.transformation.RoundedCornersTransformation;
import com.beyond.shi.base_lib.utils.transformation.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Created by wangjinya on 2016/7/13.
 */
public class BitmapUtils {
    /**
     * 加载网络图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initImage(Context context, ImageView imageView, String url
            , int erroResourceId,
                                 int loadingResourceId) {
        Picasso.with(context)
                .load(url)
                .noFade()
                .error(erroResourceId)
                .placeholder(loadingResourceId)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initCircleImage(Context context, ImageView imageView,
                                       String url, int erroResourceId,
                                       int loadingResourceId) {
        Picasso.with(context)
                .load(url)
                .error(erroResourceId)
                .placeholder(loadingResourceId)
                .transform(new CropCircleTransformation())
                .into(imageView);
    }

    /**
     * 加载方形的图片，以最短宽度为边长
     *
     * @param context
     * @param imageView
     * @param url
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initSquareImageView(Context context, ImageView imageView,
                                           String url, int erroResourceId,
                                           int loadingResourceId) {
        Picasso.with(context)
                .load(url)
                .error(erroResourceId)
                .placeholder(loadingResourceId)
                .transform(new CropSquareTransformation())
                .into(imageView);
    }

    /**
     * 带圆角的图片
     *
     * @param context
     * @param imageView         目标imageview
     * @param url
     * @param radus             半径的长度
     * @param margin            与最外层的间隔
     * @param erroResourceId    加载失败时的图片
     * @param loadingResourceId 加载中的图片
     */
    public static void initRoundedCornerImage(Context context, ImageView imageView,
                                              String url, int radus, int margin, int erroResourceId,
                                              int loadingResourceId) {
        Picasso.with(context)
                .load(url)
                .error(erroResourceId)
                .noFade()
                .placeholder(loadingResourceId)
                .transform(new RoundedCornersTransformation(radus, margin))
                .into(imageView);
    }

    /**
     * 带描边的圆角矩形图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param borderWidth
     * @param borderColor
     * @param radiu
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initRoundedCornerImage(Context context, ImageView imageView,
                                              String url, float borderWidth, int borderColor
            , float radiu, int erroResourceId,

                                              int loadingResourceId) {
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(borderColor)
                .borderWidthDp(borderWidth)
                .cornerRadiusDp(radiu)
                .build();
        Picasso.with(context)
                .load(url)
                .error(erroResourceId)
                .noFade()
                .placeholder(loadingResourceId)
                .transform(transformation)
                .into(imageView);
    }

    /**
     * 带描边的椭圆形的图片
     *
     * @param context
     * @param imageView
     * @param url
     * @param borderWidth
     * @param borderColor
     * @param radiu
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initOvalCornerImage(Context context, ImageView imageView,
                                           String url, float borderWidth, int borderColor
            , float radiu, int erroResourceId,

                                           int loadingResourceId) {
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(borderColor)
                .borderWidthDp(borderWidth)
                .cornerRadiusDp(radiu)
                .oval(true)
                .build();
        Picasso.with(context)
                .load(url)
                .error(erroResourceId)
                .noFade()
                .placeholder(loadingResourceId)
                .transform(transformation)
                .into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param file              File对象
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initImage(Context context, ImageView imageView, File file
            , int erroResourceId,
                                 int loadingResourceId) {
        Picasso.with(context).load(file).error(erroResourceId).placeholder(loadingResourceId)
                .into(imageView);
    }

    /**
     * 加载resource下图片
     *
     * @param context
     * @param imageView
     * @param resourceId        图片id
     * @param erroResourceId
     * @param loadingResourceId
     */
    public void initImage(Context context, ImageView imageView, int resourceId
            , int erroResourceId,
                          int loadingResourceId) {
        Picasso.with(context)
                .load(resourceId)
                .error(erroResourceId)
                .placeholder(loadingResourceId)
                .into(imageView);
    }

    /**
     * 加载由Uri指定的图片
     * @param context
     * @param imageView
     * @param uri
     * @param erroResourceId
     * @param loadingResourceId
     */
    public void initImageView(Context context, ImageView imageView, Uri uri
            , int erroResourceId,
                              int loadingResourceId) {
        Picasso.with(context)
                .load(uri)
                .error(erroResourceId)
                .placeholder(loadingResourceId)
                .into(imageView);
    }
}
