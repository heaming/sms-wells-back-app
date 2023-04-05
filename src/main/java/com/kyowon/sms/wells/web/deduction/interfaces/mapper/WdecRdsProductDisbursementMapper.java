package com.kyowon.sms.wells.web.deduction.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementDto;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementDvo;

@Mapper
public interface WdecRdsProductDisbursementMapper {

    //조회카운트(selectRdsProductDisbursementBas 네임수정)
    WdecRdsProductDisbursementDto.FindRes selectRdsProductDisbursement(
        WdecRdsProductDisbursementDto.SaveReq saveDto
    );

    //기본테이블 등록
    int insertRdsProductDisbursementBas(@Param("item")
    WdecRdsProductDisbursementDvo saveDvo);

    //히스토리테이블 등록
    int insertRdsProductDisbursementHist(@Param("item")
    WdecRdsProductDisbursementDvo saveDvo);

    //채번조회
    String selectRdsProductDisbursementBasId(WdecRdsProductDisbursementDvo saveDvo);

}
