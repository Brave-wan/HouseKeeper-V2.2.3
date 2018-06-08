package com.housekeeper.community.ui.activity;

import android.content.Intent;
import android.graphics.PointF;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.housekeeper.community.R;
import com.housekeeper.community.ui.widget.ScanView;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.base.BasePresenter;

/**
 * Created by root on 18-5-22.
 */

public class QRCodeActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener, View.OnClickListener {
    QRCodeReaderView qrCodeReaderView;
    ScanView scan_view;
    TextView tx_not_owen;

    @Override

    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.community_key_manager_qr_title));
        showBackwardViewIco(R.mipmap.back_image);
        qrCodeReaderView = findViewById(R.id.qr_code_view);
        scan_view = findViewById(R.id.scan_view);
        tx_not_owen = findViewById(R.id.tx_not_owen);
        tx_not_owen.setOnClickListener(this);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        qrCodeReaderView.setQRDecodingEnabled(true);
        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);
        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);
        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();
        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();
        scan_view.play();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        ToastUtils.showShort(text);

    }


    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tx_not_owen) {
            startActivity(new Intent(this, NotOwenActivity.class));
        }
    }
}
