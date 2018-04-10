package com.payAm.core.filter;//package com.payAm.core.filter;
//
//import akka.util.ByteString;
//import play.libs.streams.Accumulator;
//import play.mvc.EssentialAction;
//import play.mvc.EssentialFilter;
//import play.mvc.Result;
//
//import javax.inject.Inject;
//import java.util.concurrent.Executor;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Jan/07 - 08:46 PM
// */
//public class IntrusionDetectorFilter extends EssentialFilter {
//
//    private final Executor executor;
//
//    @Inject
//    public IntrusionDetectorFilter(Executor executor) {
//        super();
//        this.executor = executor;
//    }
//
//    @Override
//    public EssentialAction apply(EssentialAction next) {
//        return EssentialAction.of(request -> {
//            //long startTime = System.currentTimeMillis();
//            Accumulator<ByteString, Result> accumulator = next.apply(request);
//            return accumulator.map(result -> {
//                //long endTime = System.currentTimeMillis();
//                //long requestTime = endTime - startTime;
//                return result/*.withHeader("Request-Time", "" + requestTime)*/;
//            }, executor);
//        });
//    }
//}
