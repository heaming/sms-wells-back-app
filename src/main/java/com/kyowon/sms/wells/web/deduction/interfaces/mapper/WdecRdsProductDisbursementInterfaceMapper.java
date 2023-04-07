package com.kyowon.sms.wells.web.deduction.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementInterfaceDvo;

@Mapper
public interface WdecRdsProductDisbursementInterfaceMapper {

    //기본테이블 카운트 조회
    WdecRdsProductDisbursementInterfaceDvo selectRdsProductDisbursement(
        WdecRdsProductDisbursementInterfaceDvo saveDvo
    );

    //기본테이블 등록
    int insertRdsProductDisbursementBas(@Param("item")
    WdecRdsProductDisbursementInterfaceDvo saveDvo);

    //히스토리테이블 등록
    int insertRdsProductDisbursementHist(@Param("item")
    WdecRdsProductDisbursementInterfaceDvo saveDvo);

    //채번조회
    String selectRdsProductDisbursementBasId(WdecRdsProductDisbursementInterfaceDvo saveDvo);

}
