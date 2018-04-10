package com.payAm.core.client;

//import play.mvc.Http;

//import java.util.Optional;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2015/Nov/15 - 8:16 AM
 */

public class Cookie {

    private static String ISO = "ISO";
    private static String CURRENCY = "CURR";

//    public static String getIso(Http.Request request, Http.Response response) {
//        Optional<Http.Cookie> isoResponseCookie = response.cookie(ISO);
//        Http.Cookie isoCookie = isoResponseCookie.isPresent() ? isoResponseCookie.get() : request.cookie(ISO);
//        return isoCookie != null ? isoCookie.value() : "";
//    }
//
//    public static void setIso(Http.Response response, String iso) {
//        scala.Option<String> domain = play.api.mvc.Session.domain();
//        response.setCookie(ISO, iso, 10 * 365 * 24 * 60 * 60, play.api.mvc.Session.path(),
//                domain.isDefined() ? domain.get() : null);
//    }
//
//    public static String getCurrency(Http.Request request, Http.Response response) {
//        Optional<Http.Cookie> currencyResponseCookie = response.cookie(CURRENCY);
//        Http.Cookie currencyCookie = currencyResponseCookie.isPresent() ? currencyResponseCookie.get() : request.cookie(CURRENCY);
//        return currencyCookie != null ? currencyCookie.value() : "";
//    }
//
//    public static void setCurrency(Http.Response response, String currency) {
//        scala.Option<String> domain = play.api.mvc.Session.domain();
//        response.setCookie(CURRENCY, currency, 10 * 365 * 24 * 60 * 60, play.api.mvc.Session.path(),
//                domain.isDefined() ? domain.get() : null);
//    }
}