package com.hafiz.www.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:鍏ㄥ眬寮傚父澶勭悊鍣�
 * Created by hafiz.zhang on 2016/8/27.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        //handler灏辨槸澶勭悊鍣ㄩ�傞厤鍣ㄨ鎵ц鐨勫鐞嗗櫒锛堝彧鏈塵ethod鏂规硶锛�

        //1.瑙ｆ瀽鍑哄紓甯哥被鍨�
        CustomException exception = null;
        //濡傛灉璇ュ紓甯哥被鍨嬫槸绯荤粺鑷畾涔夌殑寮傚父锛岀洿鎺ュ彇鍑哄紓甯镐俊鎭紝鍦ㄩ敊璇〉闈㈠睍绀�
        if(ex instanceof CustomException){
            exception = (CustomException)ex;
        }
        else{
            //濡傛灉璇ュ紓甯哥被鍨嬩笉鏄郴缁熻嚜瀹氫箟鐨勫紓甯革紝鏋勯�犱竴涓嚜瀹氫箟鐨勫紓甯哥被鍨嬶紙淇℃伅涓衡�滄湭鐭ラ敊璇�濓級
            exception = new CustomException("鏈煡閿欒,璇蜂簬绠＄悊鍛樿仈绯�");
        }

        ModelAndView modelAndView = new ModelAndView();

        //灏嗛敊璇俊鎭紶鍒伴〉闈�
        modelAndView.addObject("message", exception.getMessage());

        //鎸囧畾閿欒椤甸潰
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
