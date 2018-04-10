package com.payAm.core.util;//package com.payAm.core.util;
//
//
//import play.mvc.Http;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Jan/10 - 02:16 PM
// */
//public class UserAgentUtil {
//
//    public static final String IE = "IE";
//    public static final String FIREFOX = "Firefox";
//    public static final String CHROME = "Chrome";
//    public static final String SAFARI = "safari";
//    public static final String OPERA = "opera";
//
//    public static final String WINDOWS = "Windows";
//    public static final String MAC_OS = "MacOS";
//    public static final String LINUX = "Linux";
//    public static final String UNIX = "Unix";
//    public static final String UNKNOWN_OS = "UnknownOS";
//
//    private static String browserName = getBrowserName();
//    private static int browserMajorVersion = getBrowserMajorVersion();
//
//    public static String getBrowserName() {
//        return Http.Context.current().request().getHeader("User-Agent");
//    }
//
//    public static String getBrowserFullVersion() {
//        return Http.Context.current().request().getHeader("User-Agent");
//    }
//
//    public static int getBrowserMajorVersion() {
//        return 0;
//    }
//
//    public static String getBrowserOperatingSystem() {
//        return Http.Context.current().request().getHeader("User-Agent");
//    }
//
//    public static boolean isIE() {
//        return IE.equals(browserName);
//    }
//
//    public static boolean isFirefox() {
//        return FIREFOX.equals(browserName);
//    }
//
//    public static boolean isChrome() {
//        return CHROME.equals(browserName);
//    }
//
//    public static boolean isSafari() {
//        return SAFARI.equals(browserName);
//    }
//
//    public static boolean isOpera() {
//        return OPERA.equals(browserName);
//    }
//
//    public static boolean checkBrowserValidity(Integer minIEVersion, Integer minFirefoxVersion, Integer minChromeVersion) {
//        boolean isValid = false;
//        if (isIE()) {
//            if (browserMajorVersion >= minIEVersion) {
//                isValid = true;
//            } else {
//                return false;
//            }
//        } else if (isFirefox()) {
//            if (browserMajorVersion >= minFirefoxVersion) {
//                isValid = true;
//            } else {
//                return false;
//            }
//        } else if (isChrome()) {
//            if (browserMajorVersion >= minChromeVersion) {
//                isValid = true;
//            } else {
//                return false;
//            }
//        }
//        return isValid;
//    }
//}
