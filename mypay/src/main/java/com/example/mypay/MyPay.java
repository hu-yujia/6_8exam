package com.example.mypay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.mypay.util.OrderInfoUtil2_0;

import java.util.Map;

public class MyPay {
    Context context;
    public MyPay(Context context) {
        this.context=context;
    }

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID =
            "2021000122674695";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHCjgYs+F1G7hZo44cn0FqSLkLmTkBXAacxBhPfipiDgEMot86hlnDAUO+i1sC+lIC6qeoHSVgaEnoNP7EWmFGhUiziVd3du7N4+bIjKS72RbnvuGDY6CR/BT7R+Qu5/neV/cbqeCalYNtczpeejmUenfyWD5L10fypK0LgS2P1SipJ5bRaaujeGOilSV9jnZi2ZYB/+Ys5Os7Pr4EOES0GaiF3TDTLmncMHhcwdFgvdJL8fYfv8AoGg3YGAbEwWtsq78CfrZiETH8Exa4O+HzaB+8LKJfRi+jz4K6TlrhMdY6DLGFmCrW2EYbUwpzUt09SWJuQuP063HvMs84Ax+vAgMBAAECggEAKfGeou2cZceKZy1KAtHke6LC9ztMXYO7+g1ej7Jcb8Bxyv773y3Dy7Ik3JZkWnRt7MH9+VW0OlGqQxnS2CJKfKEBh+Js7IpgeksDb99fe4led/TuDuVvBmkKC1K1KWycS9d+xXTqcplmDSTxSE8me1vezcNi9iOUEWXc6enhwP644cLMq5JFIvDgn398WawaNf7zW69uETgNFJH8kJMjzSCeFrkgZ709ZlwqNx6srLMKuGwD9mHIhNkSiKNxtQ55t4qdbgsJKK7pnItqqrelTMhjBWoIdi1NTeGWE5xHNEejX8UNsOcQ1mW5EEU/DgYkn/p+msBeYPdCohKdE63vQQKBgQDyVauOVsGnBA8ccuKvonnn1Xyq6c7p3oRDwiqutqA0T9XolZ1eY/cSRJzR+dZFkTJ9HxPofbLUMmGIULBNAMtre8XIqgsXHeoNmt2RFcKIb8IgdJBZ1ugf66QidQbiiOWUuLI2xfLJqM8RAixA6bYLcM9+SsX8yahycUSj0FMReQKBgQCOp6XoALs5uqant1Efd9+9DrtSsFPf+f15ll4LJVqjLVOn5rOIk5DR1lZSSUDqZCXGauippc0mOtG7YjSJUNycg4guraloYzCe7bJjTNOYil43C3u2cg9xHo2fFgN5JIyMqvMvkah6AwstEyLfOl4oMgB5RqnpULfPbyzo+WHYZwKBgHNz20ryDEq8xVJBbbxNhRStgjqPL9P9/jel7t3UBhlGG9w2Q4YvzAs5AxmKlMoJ9erkfoCaOjOcWRkvj/7vrPplOoJyeNwnlVWr/Xlcy5Ri4jLCNe+HUom6ozUDWiddHzuujsFHp3bp5NTumxTGA8QJl+FMUG0xyf3T9uawnpLJAoGAJSJ3CaHHGwv/fgE2WN63m1tOYAQOkWteFFyUNoqOwZN3B2oCqjbfqAAHkHwJLfrL6o7B6geDE8ST/BdgXa5y/zsuJBiI1OedLIHrwfWpzzeVtvaCScf/RQ8TBqqQVOSywZtump9sH/bS3fTxSvLCt+vsO6WtbVLZxmHGaw8f7VkCgYEAthFZXzWO1JDW8q/C6736nOw67krHluwzkMGjLEPRUHmdut0LU51GTepunUbdbHD9abt7VSUCIFECdD393Ghi+DhJ5/Vlcf1uuEazT8T6tVlyJBRrbh1IChlwQJFSHokrdjLs2hfB5ZPLvytiyhd1AYw+nnjlZiw8S2jx7o8qnJY=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(context, "支付成功：" + payResult);


                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(context, "支付失败：" + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(context, "支付成功：" + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(context, "支付失败：" + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    /**
     * 支付宝支付业务示例
     */
    public void payV2(View v, String money) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(context, "Error: Missing \\\"APPID\\\" or \\\"RSA_PRIVATE\\\" in PayDemoActivity.");
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, money);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务示例
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            showAlert(context, "Error: Missing \\\"PARTNER\\\", \\\"APP_ID\\\", \\\"RSA_PRIVATE\\\", or \\\"TARGET_ID\\\" in PayDemoActivity");
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask((Activity) context);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 获取支付宝 SDK 版本号。
     */
    public void showSdkVersion(View v) {
        PayTask payTask = new PayTask((Activity) context);
        String version = payTask.getVersion();
        showAlert(context, "\"Alipay SDK version: \"" + version);
    }

    /**
     * 将 H5 网页版支付转换成支付宝 App 支付的示例
     */
    /*public void h5Pay(View v) {
        WebView.setWebContentsDebuggingEnabled(true);
        Intent intent = new Intent(activity, context);
        Bundle extras = new Bundle();

        *//*
         * URL 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
         *
         * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
         * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
         * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
         * 进行测试。
         *
         * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
         * 可以参考它实现自定义的 URL 拦截逻辑。
         *
         * 注意：WebView 的 shouldOverrideUrlLoading(url) 无法拦截直接调用 open(url) 打开的第一个 url，
         * 所以直接设置 url = "https://mclient.alipay.com/cashier/mobilepay.htm......" 是无法完成网页转 Native 的。
         * 如果需要拦截直接打开的支付宝网页支付 URL，可改为使用 shouldInterceptRequest(view, request) 。
         *//*
        String url = "https://m.taobao.com";
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }*/

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton("Confirm", null)
                .setOnDismissListener(onDismiss)
                .show();
    }

    private static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    private static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            sb.append(key).append("=>").append(bundle.get(key)).append("\n");
        }
        return sb.toString();
    }

    public void pay(View view,String money){
        //开启沙箱
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        payV2(view, money);
    }

}
