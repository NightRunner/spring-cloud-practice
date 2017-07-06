package org.nr.tour.api.support;

public enum ErrorCode {

    BAD_REQUEST(400, 400), UNAUTHORIZED(401, 401), FORBIDDEN(403, 403), INTERNAL_SERVER_ERROR(500, 500),

    /**
     * 错误的登陆验证码
     */
    INVALID_LOGIN_VERIFY_CODE(10001, 400),

    COMMON(-1, 200);

    public int code;
    public int httpStatus;

    ErrorCode(int code, int httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
