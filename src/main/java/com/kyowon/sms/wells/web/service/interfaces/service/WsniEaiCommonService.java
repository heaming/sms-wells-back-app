package com.kyowon.sms.wells.web.service.interfaces.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.fee.standard.context.ApplicationContextHolder;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniEaiCommonService {

    /**
     * Wells Batch 에서 EAI 를 통해 Wells 업무로직을 재사용할 경우, 공통적으로 사용하기 위한 Controller & Service
     * param 중 className과 methodName 로 Reflection 을 사용한 Runtime Method Invoke를 수행한다.
     *
     * @param param 기본적으로 Map Class 를 기준으로 하며, 추가적인 Type의 parameter가 필요한 경우, method override 를 이용한 함수 추가를 추천한다.
     *              - SERVICE_NAME  : 수행할 Class Name.
     *                                (package url을 전부 써줘야 한다. bean name이 package url을 포함하여 생성되기 때문이다.)
     *                                (ex. com.kyowon.sms.wells.web.service.interfaces.service.WsniEaiTestService)
     *              - METHOD_NAME   : 수행할 Method Name
     *              - PARAM1 ~ PARAM50 : 호출된 SERVICE_NAME 의 parameter 인 Map 에서 추출하여 사용
     * @return - RESULT_CODE
     *         - RESULT_MSG
     *         - RETURN_OBJECT
     * @throws Exception
     *
     * @TODO 2023.05.19 현재는 기본 틀만 생성하였으며, 추후 로직 추가 등의 작업 진행 예정
     */
    public Map<String, Object> doit(Map<String, Object> param) throws Exception {

        log.info("[WsniEaiCommonService.doit()] process start!!!");

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String className = valueOfEmptyStr(param.get("SERVICE_NAME"));
            String methodName = valueOfEmptyStr(param.get("METHOD_NAME"));

            log.info("[WsniEaiCommonService.doit] className ::: " + className);
            log.info("[WsniEaiCommonService.doit] methodName ::: " + methodName);

            Class clazz = Class.forName(className);
            Object bean = ApplicationContextHolder.getBean(clazz);
            Method m = bean.getClass().getDeclaredMethod(methodName, Map.class);
//            m.setAccessible(true);    // 기본적으로 public 으로 method 를 생성하여 Encapsulation 을 해치는 소스를 굳이 넣지 않길 바란다.
//            Object retVal = m.invoke(bean, param);
            m.invoke(bean, param);

            map.put("RESULT_CODE", "S");
            map.put("RESULT_MSG", "Success");
//            map.put("RETURN_OBJECT", retVal);

        } catch (ClassNotFoundException e) {
            log.error("[WsniEaiCommonService.doit] ClassNotFoundException ::: Method invoke error!");
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", "ClassNotFoundException");
            map.put("RETURN_OBJECT", null);
            e.printStackTrace();
//            throw e;
        } catch (NoSuchMethodException e) {
            log.error("[WsniEaiCommonService.doit] NoSuchMethodException ::: Method invoke error!");
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", "NoSuchMethodException");
            map.put("RETURN_OBJECT", null);
            e.printStackTrace();
//            throw e;
        } catch (IllegalAccessException e) {
            log.error("[WsniEaiCommonService.doit] IllegalAccessException ::: Method invoke error!");
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", "IllegalAccessException");
            map.put("RETURN_OBJECT", null);
            e.printStackTrace();
//            throw e;
        } catch (InvocationTargetException e){
            log.error("[WsniEaiCommonService.doit] InvocationTargetException ::: Method invoke error!");
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", "InvocationTargetException");
            map.put("RETURN_OBJECT", null);
            e.printStackTrace();
//            throw e;
        } catch (BizException e) {
            log.error("[WsniEaiCommonService.doit] BizException ::: Method invoke error!");
            e.printStackTrace();
        } catch (Exception e) {
            log.error("[WsniEaiCommonService.doit] Method invoke error!");
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", e.getMessage());
            map.put("RETURN_OBJECT", null);
            e.printStackTrace();
//            throw e;
        }
        return map;
    }

    /*
     * String.valueOf() - Null일 경우 Empty String return
     */
    public String valueOfEmptyStr(Object obj){
        if(obj == null){
            return "";
        }
        return String.valueOf(obj);
    }
}
