package com.sankuai.waimai.router.components;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.sankuai.waimai.router.R;
import com.sankuai.waimai.router.core.OnCompleteListener;
import com.sankuai.waimai.router.core.Debugger;
import com.sankuai.waimai.router.core.UriRequest;

/**
 * 默认的全局 {@link OnCompleteListener} ，在跳转失败时弹Toast提示
 *
 * Created by jzj on 2018/3/26.
 */
public class DefaultOnCompleteListener implements OnCompleteListener {

    public static final DefaultOnCompleteListener INSTANCE = new DefaultOnCompleteListener();

    @Override
    public void onSuccess(@NonNull UriRequest request) {

    }

    @Override
    public void onError(@NonNull UriRequest request, int resultCode) {
        String text = request.getStringField(UriRequest.FIELD_ERROR_MSG, null);
        if (TextUtils.isEmpty(text)) {
            switch (resultCode) {
                case CODE_NOT_FOUND:
                    text = request.getContext().getString(R.string.wm_router_error_none_support);
                    break;
                case CODE_FORBIDDEN:
                    text = request.getContext().getString(R.string.wm_router_error_none_permission);
                    break;
                default:
                    text = request.getContext().getString(R.string.wm_router_error_start);
            }
        }
        text += "(" + resultCode + ")";
        if (Debugger.isEnableDebug()) {
            text += "\n" + request.getUri().toString();
        }
        Toast.makeText(request.getContext(), text, Toast.LENGTH_LONG).show();
    }
}
