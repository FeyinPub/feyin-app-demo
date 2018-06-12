package net.feyin.openapi.util;

import net.feyin.openapi.enumeration.FeyinErrorCode;
import net.feyin.openapi.exception.ResponseException;
import net.feyin.openapi.model.ErrorResponse;
import net.feyin.openapi.util.JsonUtil;

public class FeyinRequestErrorHandel {

    public static void errorHandel(String resp) {
        try {
            ErrorResponse errorResponse = JsonUtil.parse(resp, ErrorResponse.class);
            String errorMsg = FeyinErrorCode.getCodeMsg(errorResponse.getErrcode());
            throw new ResponseException(errorMsg);
        } catch (Exception e) {
            throw new ResponseException("http request error, " + resp);
        }
    }

    public static ErrorResponse checkResponseError(String resp) {
        try {
            return JsonUtil.parse(resp, ErrorResponse.class);
        } catch (Exception e) {
            throw new ResponseException("http request error, " + resp);
        }
    }
}
