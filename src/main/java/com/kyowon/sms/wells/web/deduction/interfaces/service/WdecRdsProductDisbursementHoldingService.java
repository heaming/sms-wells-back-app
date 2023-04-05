package com.kyowon.sms.wells.web.deduction.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.SaveReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementHoldingDvo;
import com.kyowon.sms.wells.web.deduction.interfaces.mapper.WdecRdsProductDisbursementHoldingMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdecRdsProductDisbursementHoldingService {

    private final WdecRdsProductDisbursementHoldingMapper mapper;

    //보류금액상세 조회
    public FindRes getRdsProductDisbursementHoldings(FindReq dto) {
        return mapper.selectRdsProductDisbursementHoldings(dto);
    }

    //등록
    @Transactional
    public String[] createRdsProductDisbursementHoldings(SaveReq dto) {

        WdecRdsProductDisbursementHoldingDvo saveDvo = new WdecRdsProductDisbursementHoldingDvo();

        //리스트로 넘어온 데이터 처리
        for (int i = 0; i < dto.data().size(); i++) {
            saveDvo.setOgTpCdVal(dto.data().get(i).ogTpCdVal().toString()); //조직유형코드값
            saveDvo.setPrtnrNO(dto.data().get(i).prtnrNo().toString()); //파트너번호 셋팅
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
