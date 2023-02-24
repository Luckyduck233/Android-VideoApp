package com.example.videoapp.util;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtil {

    public static void showConfirmDialog(Context context,String info,final OnConfirmClickListener onConfirmClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(info).setPositiveButton("是",((dialog, which) -> {
            if (onConfirmClickListener != null) {
                onConfirmClickListener.onConfirm();
            }
        })).setNegativeButton("否",((dialog, which) -> {
            dialog.dismiss();
        }));

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}