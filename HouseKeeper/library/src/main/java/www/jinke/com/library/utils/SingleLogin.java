package www.jinke.com.library.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import www.jinke.com.library.base.BaseActivity;
import www.jinke.com.library.widget.MaterialDialog;

/**
 * Created by root on 18-5-24.
 */

public class SingleLogin {

    static MaterialDialog materialDialog;

    public static void init(Context cx) {

    }

    public static void errorState(final Context cx, String code) {
        switch (code) {
            case "-1":
            case "7":
                if (materialDialog != null) {
                    materialDialog.dismiss();
                    materialDialog = null;
                }
                materialDialog = new MaterialDialog(cx);
                materialDialog.setTitle("登录失效");
                materialDialog.setMessage("有一台设备在其他地方登录，是否重新登录");
                materialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClassName("com.jinke.housekeeper", "com.jinke.housekeeper.ui.activity.LoginActivity");
                        cx.startActivity(intent);
                        BaseActivity.exit();
                    }
                });
                materialDialog.show();
                break;
        }
    }
}
