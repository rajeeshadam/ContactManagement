package com.task.contactmanagement.binder;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.task.contactmanagement.helper.ImageHandler;
import com.task.contactmanagement.utilities.RoundImageTransform;

/**
 * Created by rajeesh on 30/1/17.
 */

public class ListBinder {
    @BindingAdapter("android:src")
    public static void bindImage(ImageView view, String url) {
        System.out.println("inside"+url);
        Glide.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new RoundImageTransform(view.getContext()))
                .into(new ImageHandler(view));


    }

}