package com.beyond.shi.base_lib.utils;

import android.content.Context;
import android.widget.ImageView;

import com.beyond.shi.base_lib.utils.transformation.CropCircleTransformation;
import com.beyond.shi.base_lib.utils.transformation.CropSquareTransformation;
import com.beyond.shi.base_lib.utils.transformation.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * Created by wangjinya on 2016/7/13.
 */
public class BitmapUtils {
    //圆形图片
    public static final int CIRCLE_IMAGE_MODE = 1;
    //正方形
    public static final int SQUARE_IMAGE_MODE = 2;


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
     * 加载圆形图片或者方形图片（根据长宽最小的为边宽）
     *
     * @param context
     * @param imageView
     * @param url
     * @param IMAGE_MODE        有两种类型（圆形 CIRCLE_IMAGE_MODE和正方形 SQUARE_IMAGE_MODE)
     * @param erroResourceId
     * @param loadingResourceId
     */
    public static void initImage(Context context, ImageView imageView,
                                 String url, int IMAGE_MODE, int erroResourceId,
                                 int loadingResourceId) {
        RequestCreator placeholder = Picasso.with(context)
                .load(url)
                .noFade()
                .error(erroResourceId)
                .placeholder(loadingResourceId);
        switch (IMAGE_MODE) {
            case BitmapUtils.CIRCLE_IMAGE_MODE:
                placeholder.transform(new CropCircleTransformation());
                break;
            case BitmapUtils.SQUARE_IMAGE_MODE:
                placeholder.transform(new CropSquareTransformation());
                break;
        }
        placeholder.into(imageView);
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
}
