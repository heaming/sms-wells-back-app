package com.kyowon.sms.wells.web.deduction.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementInterfaceDto;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementInterfaceDvo;
import com.kyowon.sms.wells.web.deduction.interfaces.mapper.WdecRdsProductDisbursementInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdecRdsProductDisbursementInterfaceService {

    private final WdecRdsProductDisbursementInterfaceMapper mapper;

    //등록처리(트랜잭션)
    @Transactional
    public String[] createRdsDisbursement(WdecRdsProductDisbursementInterfaceDto.SaveReq dto) {

        WdecRdsProductDisbursementInterfaceDvo saveDvo = new WdecRdsProductDisbursementInterfaceDvo();

        //검색용 saveDvo
        saveDvo.setOgTpCd(dto.ogTpCd()); /*조직유형코드*/
        saveDvo.setCoCd(dto.coCd()); /*대상회사코드 셋팅*/
        saveDvo.setRdsDsbRefId(dto.rdsDsbRefId());/*RDS지급참조ID*/
        saveDvo.setAkOgTpCd(dto.akOgTpCd()); /*요청조직유형코드*/
        saveDvo.setPrtnrNo(dto.prtnrNo()); /*파트너번호*/
        saveDvo.setBlngYm(dto.blngYm()); /*귀속년월*/

        //전달받은 값을 확인하여 select를 하여 카운트를 저장
        WdecRdsProductDisbursementInterfaceDvo result = mapper.selectRdsProductDisbursement(saveDvo);

        //조회를 통해 값이 있는 확인
        if (Integer.parseInt(result.getCount()) <= 0) { //값이없다면
            //처리 로직(등록처리)

            //저장용, 추가파라미터 추가 saveDvo
            saveDvo.setRdsRvUseTpCd(dto.rdsRvUseTpCd()); /*RDS적립사용유형코드 셋팅*/
            saveDvo.setRdsRvUseDt(dto.rdsRvUseDt()); /*적립사용일자 셋팅*/
            saveDvo.setRdsAmt(dto.rdsAmt()); /*RDS금액*/
            saveDvo.setRdsEarnrDvCd(dto.rdsEarnrDvCd()); /*RDS소득자구분코드*/
            saveDvo.setRdsSmryCd(dto.rdsSmryCd()); /*RDS적요코드*/
            saveDvo.setRdsSmryCn(dto.rdsSmryCn()); /*RDS적요내용*/

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
            int historyResultCount = mapper.insertRdsProductDisbursementHist(saveDvo); //RDS 적립사용 히스토리

            //인서트쿼리가 실패하면, 처리
            if (historyResultCount <= 0) {
                return new String[] {dto.rdsDsbRefId(), "F", "히스토리 등록이 실패하였습니다."}; //스트링배열로 리턴
            }

            //최종 리턴
            return new String[] {dto.rdsDsbRefId(), "S", "데이터 등록이 성공하였습니다."}; //스트링배열로 리턴

        } else { //값이 있다면
            return new String[] {dto.rdsDsbRefId(), "F", "기존자료가 존재합니다."}; //스트링배열로 리턴
        }

    }

}
