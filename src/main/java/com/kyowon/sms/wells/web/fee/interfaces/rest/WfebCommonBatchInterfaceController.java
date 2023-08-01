package com.kyowon.sms.wells.web.fee.interfaces.rest;

import com.kyowon.sms.common.web.fee.standard.context.ApplicationContextHolder;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@InterfaceController
@Api(tags = "[WFEB] EAI_WCOI1002 Wells 배치 API 호출 ")
@RequestMapping(value = CtFeeConst.INTERFACE_URL_V1 + "/batch-call-services")
@RequiredArgsConstructor
@Validated
public class WfebCommonBatchInterfaceController {


    @ApiOperation(value = "[EAI_WCOI1002] Wells 배치 API 호출 처리", notes = "배치서버에서 Wells 수수료 API 호출을 처리한다.")
    @PostMapping
    public <T> EaiWrapper processEaiCommonInterface(@RequestBody EaiWrapper<Map<String, Object>> reqWrapper) {
        EaiWrapper<Map<String, Object>> resWrapper = reqWrapper.newResInstance();
        Map<String, Object> param = reqWrapper.getBody();
        Map<String, Object> map = new HashMap<>();

        try {
            String className = StringUtil.nvl(param.get("SERVICE_NAME"));
            String methodName = StringUtil.nvl(param.get("METHOD_NAME"));

            Class<T> clazz = (Class<T>) Class.forName(className);
            T bean = ApplicationContextHolder.getBean(clazz);
            Method m = Arrays.stream(clazz.getMethods()).filter(item -> {
                boolean response = true;
                if (methodName.equals(item.getName()) && param.keySet().size() == (item.getParameters().length + 2)) {
                    for (Parameter p : item.getParameters()) {
                        if (!param.containsKey(p.getName())) {
                            response = false;
                        }
                    }
                } else {
                    response = false;
                }
                return response;
            }).findFirst().orElse(null);

            if (m != null) {
                List<Object> args = new ArrayList<>();
                boolean checkParam = true;
                for (Parameter item : m.getParameters()) {
                    if (param.containsKey(item.getName())) {
                        args.add(param.get(item.getName()));
                    } else {
                        map.put("RESULT_CODE", "E");
                        map.put("RESULT_MSG", "There is no Parameter. : " + item.getName());
                        map.put("RETURN_OBJECT", null);
                        checkParam = false;
                        break;
                    }
                }

                if (checkParam) {
                    m.invoke(bean, args.toArray());
                    map.put("RESULT_CODE", "S");
                    map.put("RESULT_MSG", "Success");
                }
            } else {
                map.put("RESULT_CODE", "E");
                map.put("RESULT_MSG", "There is no Method. : " + methodName);
            }
        } catch (ClassNotFoundException e) {
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", "ClassNotFoundException");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", "IllegalAccessException");
            e.printStackTrace();
        } catch (Exception e) {
            map.put("RESULT_CODE", "E");
            map.put("RESULT_MSG", e.getMessage());
            e.printStackTrace();
        }

        resWrapper.setBody(map);


        return resWrapper;
    }

}
