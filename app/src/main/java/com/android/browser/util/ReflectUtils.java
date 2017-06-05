package com.android.browser.util;

import org.apache.http.HttpHost;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Proxy;
import android.net.http.SslCertificate;
import android.os.StrictMode;
import android.provider.Browser;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Luooh on 2017/5/29.
 */

public class ReflectUtils {

    private static ReflectUtils sInstance;

    private ReflectUtils() {
    }

    public static ReflectUtils getInstance() {
        if (sInstance == null) {
            sInstance = new ReflectUtils();
        }
        return sInstance;
    }

    public Method getMethod(Class<?> clazz, String methodName, Class<?> ...values) {
        Method method = null;
        try {
            if(values != null) {
                method = clazz.getDeclaredMethod(methodName, values);
            }else {
                method = clazz.getDeclaredMethod(methodName);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }


    public Method getMethod(String className, String methodName, Class<?> ...values) {
        try {
            Class<?> clazz = Class.forName(className);
            return getMethod(clazz, methodName, values);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object invokeStaticMethod(Method method, Object ...values) {
        return invokeMethod(method, null, values);
    }

    public Object invokeMethod(Method method, Object classValue, Object ...values) {
        if(method != null) {
            try {
                return method.invoke(classValue, values);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * invoke the WebView enablePlatformNotifications method
     */
    public void enablePlatformNotifications() {
        Method method = getMethod(WebView.class, "enablePlatformNotifications");
        invokeStaticMethod(method);
    }

    public void debugDump(WebView webView) {
        Method method = getMethod(WebView.class, "debugDump");
        invokeMethod(method, webView);
    }

    public void disablePlatformNotifications() {
        Method method = getMethod(WebView.class, "disablePlatformNotifications");
        invokeStaticMethod(method);
    }

    public int getContentWidth(WebView webView) {
        Method method = getMethod(WebView.class, "getContentWidth");
        Object obj = invokeMethod(method, webView);
        return obj == null ? 0 : (Integer) obj;
    }

    public int getVisibleTitleHeight(WebView webView){
        Method method = getMethod(WebView.class, "getVisibleTitleHeight");
        Object obj = invokeMethod(method, webView);
        return obj == null ? 0 : (Integer)obj;
    }

    public void setNavDump(WebSettings webSettings, boolean enabled) {
        Method method = getMethod(WebSettings.class, "setNavDump", boolean.class);
        invokeMethod(method, webSettings, enabled);
    }

    public String getTouchIconUrl(WebView webView) {
        Method method = getMethod(WebView.class, "getTouchIconUrl");
        Object obj = invokeMethod(method, webView);
        return obj == null ? "" : String.valueOf(obj);
    }

    public String getCookie(CookieManager cookieManager, String url, boolean privateBrowsing) {
        Method method = getMethod(CookieManager.class, "getCookie", new Class<?>[]{String.class, Boolean.class});
        Object obj = invokeMethod(method, cookieManager, url, privateBrowsing);
        return obj == null ? "" : String.valueOf(obj);
    }

    public String[] getVisitedHistory(ContentResolver resolver) {
        Method method = getMethod(Browser.class, "getVisitedHistory", ContentResolver.class);
        return (String[]) invokeStaticMethod(method, resolver);
    }

    public void focusIn(InputMethodManager manager, View view) {
        Method method = getMethod(InputMethodManager.class, "focusIn", View.class);
        invokeMethod(method, manager, view);

    }

    public View inflateCertificateView(SslCertificate certificate, Context context) {
        Method method = getMethod(SslCertificate.class, "inflateCertificateView", Context.class);
        Object obj = invokeMethod(method, certificate, context);
        return obj == null ? null : (View) obj;
    }

    public HttpHost getPreferredHttpHost(Context context, String url) {
        Method method = getMethod(Proxy.class, "getPreferredHttpHost", Context.class, String.class);
        Object obj = invokeMethod(method, context, url);
        return obj == null ? null : (HttpHost) obj;
    }

    public boolean suppressDialog(HttpAuthHandler httpAuthHandler) {
        Method method = getMethod(HttpAuthHandler.class, "suppressDialog");
        Object obj = invokeMethod(method, httpAuthHandler);
        return obj == null ? false : (Boolean) obj;
    }

    public ComponentName getWebSearchActivity(SearchManager searchManager){
        Method method = getMethod(SearchManager.class, "getWebSearchActivity");
        Object obj = invokeMethod(method, searchManager);
        return obj == null ? null : (ComponentName) obj;
    }

    public Cursor getSuggestions(SearchManager searchManager, SearchableInfo searchableInfo, String query) {
        Method method = getMethod(SearchManager.class, "getSuggestions", SearchableInfo.class, String.class);
        Object obj = invokeMethod(method, searchManager, searchableInfo, query);
        return obj == null ? null : (Cursor) obj;
    }

    public File getSharedPrefsFile(Context context, String fileName) {
        Method method = getMethod(Context.class, "getSharedPrefsFile", String.class);
        Object obj = invokeMethod(method, context, fileName);
        return obj == null ? null : (File) obj;
    }

    public void sendString(Context context, String msg, String chooserDialogTitle) {
        Method method = getMethod(Browser.class, "", Context.class, String.class, String.class);
        invokeStaticMethod(method, context, msg, chooserDialogTitle);
    }

    public float getVerticalScrollFactor(View view) {
        Method method = getMethod(View.class, "getVerticalScrollFactor");
        Object obj = invokeMethod(method, view);
        return obj == null ? 0.0f : (Float) obj;
    }

    public float getHorizontalScrollFactor(View view) {
        Method method = getMethod(View.class, "getHorizontalScrollFactor");
        Object obj = invokeMethod(method, view);
        return obj == null ? 0.0f : (Float) obj;
    }

    public void invalidateParentIfNeeded(View view) {
        Method method = getMethod(View.class, "invalidateParentIfNeeded");
        invokeMethod(method, view);
    }

    public Object enterCriticalSpan(String name) {
        Method method = getMethod(StrictMode.class, "enterCriticalSpan", String.class);
        return invokeStaticMethod(method, name);
    }

    public void Span_finish(Object strictMode_span) {
        Method method = getMethod("android.os.StrictMode$Span", "finish");
        invokeMethod(method, strictMode_span);
    }

    public int getDrawable(String name) {
        return getSystemResource(name, "drawable", "android");
    }

    public String getString(Context context, String name) {
        return context.getString(getStringId(name));
    }

    public int getStringId(String name) {
        return getSystemResource(name, "string", "android");
    }

    public int getAttr(String name) {
        return getSystemResource(name, "attr", "android");
    }

    public int getResourceId(String name) {
        return getSystemResource(name, "id", "android");
    }

    public int getStyleable(String name) {
        return getSystemResource(name, "styleable", "android");
    }

    public int getLayoutId(String layoutName) {
        return getSystemResource(layoutName, "layout", "android");
    }

    private int getSystemResource(String name, String type, String packageName) {
        return Resources.getSystem().getIdentifier(name, type, packageName);
    }
}
