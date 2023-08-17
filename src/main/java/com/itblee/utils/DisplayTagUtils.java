package com.itblee.utils;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

public final class DisplayTagUtils {

    //private static final Logger log = Logger.getLogger(DisplayTagUtils.class);

    public static Integer pageOf(String tableId, HttpServletRequest request) {
        String parameter = request.getParameter(new ParamEncoder(tableId)
                .encodeParameterName(TableTagParameters.PARAMETER_PAGE));
        return CastUtils.cast(parameter, Integer.class).orElse(1);
    }

}
