package com.kyowon.sms.wells.web.deduction.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.OrganizationTypes;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementHoldingInterfaceDvo;
import com.kyowon.sms.wells.web.deduction.interfaces.mapper.WdecRdsProductDisbursementHoldingInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdecRdsProductDisbursementHoldingInterfaceService {

    private final WdecRdsProductDisbursementHoldingInterfaceMapper mapper;

    //보류금액상세 조회
    public FindRes getRdsProductDisbursementHoldings(FindReq dto) {
        return mapper.selectRdsProductDisbursementHoldings(dto);
    }

    //등록등록처리(트랜잭션)
    @Transactional
    public String[] createRdsProductDisbursementHoldings(SaveReq dto) {

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

        //모든 경우가 성공하면 처리
        return new String[] {dto.rdsDsbDuedt(), "S", "데이터 등록이 성공하였습니다."}; //스트링배열로 리턴

    }

}
