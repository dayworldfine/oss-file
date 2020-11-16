//package com.oss.tool.intercept;
//
//import com.oss.tool.util.ValidateUtil;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.OutputStream;
//
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        String token = httpServletRequest.getHeader("token");
//        //token验证
//        if (ValidateUtil.isNotEmpty(token)) {
//            String jsonToken = JwtHelper.validateLogin(token);
//            if (ValidateUtil.isNotEmpty(jsonToken)){
//                //进行失败业务处理
//                Identity identity = JsonUtil.toBean(jsonToken, Identity.class);
//                if (ValidateUtil.isEmpty(identity.getUserId()) || ValidateUtil.isEmpty(identity.getUserName())){
//                    //进行失败业务处理
//                    httpResponseError(httpServletResponse);
//                    return false;
//                }
//                httpServletRequest.setAttribute("Identity",JsonUtil.toBean(jsonToken, Identity.class));
//                return true;
//            } else {
//                //进行失败业务处理
//                httpResponseError(httpServletResponse);
//                return false;
//            }
//        }else {
//            //进行失败业务处理
//            httpResponseError(httpServletResponse);
//            return false;
//        }
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//
//
//    private void httpResponseError(HttpServletResponse httpServletResponse) throws Exception{
//        //验证错误,跳转到错误页面
////            httpServletResponse.sendError(400,"需要登录");
//        httpServletResponse.setStatus(200);
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setHeader("Content-Type", "application/json");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST");
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        OutputStream outputStream = httpServletResponse.getOutputStream();
//        httpServletResponse.getOutputStream();
//        byte[] dateByByte = JsonUtil.toJson(ResponseModel.error(ErrorCodes.NEED_LOGIN)).getBytes();
//        outputStream.write(dateByByte);
//    }
//}
