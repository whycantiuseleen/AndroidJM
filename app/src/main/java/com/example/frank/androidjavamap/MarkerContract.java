package com.example.frank.androidjavamap;

import android.provider.BaseColumns;
import android.widget.ImageView;

/**
 * Created by Frank on 15/11/2017.
 */

public class MarkerContract {

    public static final class MarkerEntry implements BaseColumns{

        public static final String TABLE_NAME = "SpendingRecord";
        public static final String COL_AMOUNT = "Amount";
        public static final String COL_REMARKS = "Remarks";
//        public static final ImageView COL_IMAGE = "image";
    }
}
