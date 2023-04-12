package com.kyowon.sms.wells.web.deduction.interfaces.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.OrganizationTypes;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementHoldingInterfaceDvo;
import com.kyowon.sms.wells.web.deduction.interfaces.mapper.WdecRdsProductDisbursementHoldingInterfaceMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdecRdsProductDisbursementHoldingInterfaceService {

    private final WdecRdsProductDisbursementHoldingInterfaceMapper mapper;

    private final BatchCallService batchCallService;

    //보류금액상세 조회
    public FindRes getRdsProductDisbursementHoldings(FindReq dto) {
        return mapper.selectRdsProductDisbursementHoldings(dto);
    }

    //등록등록처리(트랜잭션)
    @Transactional
    public String[] createRdsProductDisbursementHoldings(SaveReq dto) throws Exception {

        WdecRdsProductDisbursementHoldingInterfaceDvo saveDvo = new WdecRdsProductDisbursementHoldingInterfaceDvo();

        //리스트로 넘어온 데이터 처리
        for (OrganizationTypes organizationTypes : dto.data()) {

            saveDvo.setOgTpCdVal(organizationTypes.ogTpCdVal()); //조직유형코드값
            saveDvo.setPrtnrNO(organizationTypes.prtnrNo()); //파트너번호 셋팅
            saveDvo.setRdsDsbDuedt(dto.rdsDsbDuedt()); //RDS지급예정일자 셋팅

            int resultCnt = mapper.insertRdsProductDisbursementHoldings(saveDvo); //계약변경접수기본

            //인서트쿼리가 실패하면, 처리
            if (resultCnt <= 0) {
                return new String[] {dto.rdsDsbDuedt(), "F", "데이터 등록이 실패하였습니다."}; //스트링배열로 리턴
            }
        }

        //배치 dvo 생성
        BatchCallReqDvo batchDvo = new BatchCallReqDvo();

        //배치 실행횟수
        String batchRunId = "";

        //현재날짜
        String wkPrtcDtmVal = DateUtil.getNowString();
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String tenantId = session.getTenantId();//테넌트Id

        Map<String, String> params = new HashMap<String, String>();

        batchDvo.setJobKey("SMS_DE_OZ0001");
        params.put("hdPrcsdt", dto.rdsDsbDuedt()); //보류처리일자(RDS지급예정일자로 셋팅)
        params.put("wkPrtcDtm", wkPrtcDtmVal); //작업실행일시
        params.put("tenantId", tenantId); //테넌트 아이디
        batchDvo.setParams(params); // Job 실행시 필요한 파라미터

        //배치호출(try-catch대신, throw사용)
        batchRunId = batchCallService.runJob(batchDvo); //배치실행횟수를 저장

        //모든 경우가 성공하면 처리
        return new String[] {dto.rdsDsbDuedt(), "S", "데이터 등록이 성공하였습니다."}; //스트링배열로 리턴

    }

}
