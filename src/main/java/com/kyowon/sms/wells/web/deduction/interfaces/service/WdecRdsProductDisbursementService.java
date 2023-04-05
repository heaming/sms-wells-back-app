package com.kyowon.sms.wells.web.deduction.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementDto;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementDvo;
import com.kyowon.sms.wells.web.deduction.interfaces.mapper.WdecRdsProductDisbursementMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdecRdsProductDisbursementService {

    private final WdecRdsProductDisbursementMapper mapper;

    //등록처리(트랜잭션)
    @Transactional
    public String[] createRdsDisbursement(WdecRdsProductDisbursementDto.SaveReq dto) {

        WdecRdsProductDisbursementDvo saveDvo = new WdecRdsProductDisbursementDvo();

        //전달받은 값을 확인하여 select를 하여 카운트를 저장
        WdecRdsProductDisbursementDto.FindRes result = mapper.selectRdsProductDisbursement(dto);

        //조회를 통해 값이 있는 확인
        if (Integer.parseInt(result.count()) <= 0) { //값이없다면
            //처리 로직(등록처리)

            //파라미터 셋팅
            saveDvo.setCoCd(dto.coCd()); //대상회사코드 셋팅
            saveDvo.setRdsRvUseTpCd(dto.rdsRvUseTpCd());//RDS적립사용유형코드 셋팅
            saveDvo.setRdsRvUseDt(dto.rdsRvUseDt()); //적립사용일자 셋팅

            saveDvo.setOgTpCd(dto.ogTpCd());
            saveDvo.setRdsDsbRefId(dto.rdsDsbRefId());
            saveDvo.setAkOgTpCd(dto.akOgTpCd());
            saveDvo.setPrtnrNo(dto.prtnrNo());
            saveDvo.setBlngYm(dto.blngYm());
            saveDvo.setRdsAmt(dto.rdsAmt());
            saveDvo.setRdsEarnrDvCd(dto.rdsEarnrDvCd());
            saveDvo.setRdsSmryCd(dto.rdsSmryCd());
            saveDvo.setRdsSmryCn(dto.rdsSmryCn());

            //0. RDS 적립사용 기본 채번로직(기본, 히스토리에 모두 사용)
            String primaryKey = mapper.selectRdsProductDisbursementBasId(saveDvo);
            saveDvo.setRdsRvUseId(primaryKey); //RDS적립사용ID 셋팅(PK)

            //1. RDS 적립사용 기본에 등록
            int basResultCnt = mapper.insertRdsProductDisbursementBas(saveDvo); //RDS 적립사용 기본

            //인서트쿼리가 실패하면, 처리
            if (basResultCnt <= 0) {
                return new String[] {dto.rdsDsbRefId(), "F", "기본 테이블 등록이 실패하였습니다."}; //스트링배열로 리턴
            }

            //2. RDS 적립사용 히스토리에 등록
            int HistresultCnt = mapper.insertRdsProductDisbursementHist(saveDvo); //RDS 적립사용 히스토리

            //인서트쿼리가 실패하면, 처리
            if (HistresultCnt <= 0) {
                return new String[] {dto.rdsDsbRefId(), "F", "히스토리 등록이 실패하였습니다."}; //스트링배열로 리턴
            }

            //최종 리턴
            return new String[] {dto.rdsDsbRefId(), "S", "데이터 등록이 성공하였습니다."}; //스트링배열로 리턴

        } else { //값이 있다면
            return new String[] {dto.rdsDsbRefId(), "F", "기존자료가 존재합니다."}; //스트링배열로 리턴
        }

    }

}
