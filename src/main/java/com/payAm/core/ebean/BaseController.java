package com.payAm.core.ebean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import play.Configuration;
//import play.Environment;
//import play.i18n.Messages;
//import play.i18n.MessagesApi;
//import play.mvc.Controller;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/08 - 07:22 PM
 */
@RestController
@RequestMapping("/u")
public abstract class BaseController /*extends Controller */ {

//    @Inject Environment environment;
//    @Inject Configuration configuration;
//    @Inject MessagesApi messagesApi;
//    Messages messages;
//
//    public String getIso() {
//        return Cookie.getIso(request(), response());
//    }
//
//    protected String getClientIso() {
//        String countryIso = getIso();
//        if (countryIso == null || countryIso.equals("")) {
//            String ip = request().remoteAddress();
//            System.out.println("REMOTE ADDRESS : " + ip);
//            countryIso = PropertiesConstants.IR;
//            Cookie.setIso(response(), countryIso);
//        }
//        return countryIso;
//    }
//
//    public String getClientDirection() {
//
//        return configuration.getString(PropertiesConstants.DIRECTION + StringUtil.DOT + lang().code());
//    }
//
//    public String getClientCalendar() {
//        String calendar = configuration.getString(PropertiesConstants.CALENDAR + StringUtil.DOT + lang().code());
//        return calendar != null ? calendar :
//                configuration.getString(PropertiesConstants.CALENDAR + StringUtil.DOT + PropertiesConstants.OTHER);
//    }
//
//    public Configuration getConfiguration() {
//        return configuration;
//    }
//
//    public Environment getEnvironment() {
//        return environment;
//    }
//
//    public Messages getMessages() {
//        if (this.messages == null) {
//            this.messages = messagesApi.preferred(request());
//        }
//        return this.messages;
//    }

}
