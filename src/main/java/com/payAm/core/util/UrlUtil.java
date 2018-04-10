package com.payAm.core.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/10 - 02:05 PM
 */
public class UrlUtil {

    private HashMap<String, String> params = new HashMap<>();

    public String getValue(String key) {
        return params.get(key);
    }

    public static String getParametersAsString(HashMap<String, String> params) {
        if (params == null || params.size() == 0) {
            return "";
        }
        StringBuilder urlBuilder = new StringBuilder();
        for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
            String paramKey = iterator.next();
            if (StringUtil.hasText(params.get(paramKey))) {
                urlBuilder.append(paramKey).append("=").append(params.get(paramKey));
                if (iterator.hasNext()) {
                    urlBuilder.append("&");
                }
            }
        }
        return "/" + urlBuilder.toString();
    }

    public HashMap<String, String> getInitParams() {
        return params;
    }

    public static boolean isAbsoluteUrl(String url) {
        return url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://");
    }
}
