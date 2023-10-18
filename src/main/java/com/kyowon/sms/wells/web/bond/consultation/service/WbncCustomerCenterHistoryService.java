package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerCenterHistoryDto.FindRes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.ivo.EAI_WSVO1013.request.CustomerReqIvo;
import com.kyowon.sms.wells.web.bond.consultation.ivo.EAI_WSVO1013.response.CustomerResIvo;
import com.sds.sflex.common.common.service.EaiInterfaceService;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 고객센터 컨택이력 조회
 * </pre>
 *
 * @author songmi.in
 * @since 2023-09-14
 */
@Service
@RequiredArgsConstructor
public class WbncCustomerCenterHistoryService {
    private final EaiInterfaceService interfaceService;
    private static final String CST_CENTER_URL = "/W/SV/EAI_WSVO1013/req";

    /**
     * [EAI_WSVO1013] 고객센터 컨택이력 조회
     * @param cstNo
     * @return
     */
    public List<FindRes> getCustomerCenterHistories(String cstNo) {
        CustomerReqIvo req = new CustomerReqIvo();
        req.setCstNo(cstNo);

        List<FindRes> res = new ArrayList<>();
        CustomerResIvo[] ivos = interfaceService.post(CST_CENTER_URL, req, CustomerResIvo[].class);
        for (CustomerResIvo ivo : ivos) {
            if (StringUtil.isNotEmpty(ivo.getRownum())) {
                res.add(
                    FindRes.builder()
                        .rownum(ivo.getRownum())
                        .cnslNo(ivo.getCnslNo())
                        .cnslDt(ivo.getCnslDt())
                        .cnslStDtTm(ivo.getCnslStDt() + ivo.getCnslStTm())
                        .cstNo(ivo.getCstNo())
                        .jobCd(ivo.getJobCd())
                        .jobNm(ivo.getJobNm())
                        .centerCd(ivo.getCenterCd())
                        .centerNm(ivo.getCenterNm())
                        .sellTpCd(ivo.getSellTpCd())
                        .cntrDtlNo(ivo.getCntrNo() + "-" + ivo.getCntrSn())
                        .cnslCn(ivo.getCnslCn())
                        .cstNm(ivo.getCstNm())
                        .telNo(ivo.getCstTnoa() + "-" + ivo.getCstTnob() + "-" + ivo.getCstTnoc())
                        .cnslEdDt(ivo.getCnslEdDt())
                        .cnslEdTm(ivo.getCnslEdTm())
                        .callTpCd(ivo.getCallTpCd())
                        .regDt(ivo.getRegDt())
                        .regTm(ivo.getRegTm())
                        .regUserId(ivo.getRegUserId())
                        .regUserNm(ivo.getRegUserNm())
                        .modDt(ivo.getModDt())
                        .modTm(ivo.getModTm())
                        .modUserId(ivo.getModUserId())
                        .modUserNm(ivo.getModUserNm())
                        .build()
                );
            }
        }

        return res;
    }
}
