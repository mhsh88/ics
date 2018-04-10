package com.payAm.core.filter;//package com.payAm.core.filter;
//
//import play.filters.cors.CORSFilter;
//import play.http.HttpFilters;
//import play.mvc.EssentialFilter;
//
//import javax.inject.Inject;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Jan/07 - 07:40 PM
// */
//public class Filters implements HttpFilters {
//
//    @Inject
//    private CORSFilter corsFilter;
//
//    /*@Inject
//    public Filters(GzipFilter gzipFilter, CORSFilter corsFilter, IntrusionDetectorFilter intrusionDetectorFilter,
//                   AllowedHostsFilter allowedHostsFilter, SecurityHeadersFilter securityHeadersFilter) {
//
//        super(gzipFilter, corsFilter, allowedHostsFilter, securityHeadersFilter, intrusionDetectorFilter);
//        this.corsFilter = corsFilter;
//    }*/
//
//    public EssentialFilter[] filters() {
//        return new EssentialFilter[] { corsFilter.asJava() };
//    }
//}
