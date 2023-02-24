package com.example.videoapp.util;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videoapp.R;

public class LoadingDialog {
    private static final String TAG = "LoadingDialog";
    private static LoadingDialog mLoadingDialog = new LoadingDialog();
    private static TextView mMessageTextView;
    private static Dialog mDialog;

    private LoadingDialog() {
    }

    public static LoadingDialog Builder(Context context,String msg) {
        initDialog(context,msg);
        return mLoadingDialog;
    }

    private static void initDialog(Context context,String msg) {
        Log.d(TAG, "initDialog: ");
        View view = LayoutInflater.from(context).inflate(R.layout.alertlog_loading, null);

        mMessageTextView = view.findViewById(R.id.iv_rotateCircle);

        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);
        setMessage(msg);
    }

    private static void setMessage(String message) {
        if (mMessageTextView != null) {
            mMessageTextView.setText(message);
        }
    }

    public void show() {
        Log.e(TAG, "show: ");
        if (mDialog != null && !mDialog.isShowing()) {
            Log.d(TAG, "not");
            mDialog. show();
        } else {
            Toast.makeText(mDialog.getContext(), "空", Toast.LENGTH_SHORT).show();
        } 
    }

    public void dismiss() {
        Log.d(TAG, "dismiss: ");
        if (mDialog != null && mDialog.isShowing()) {
            Log.d(TAG, "dismiss: 2");

            mDialog.dismiss();
        }
    }
}
