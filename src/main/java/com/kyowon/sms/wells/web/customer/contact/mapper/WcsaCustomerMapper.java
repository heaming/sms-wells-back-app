package com.kyowon.sms.wells.web.customer.contact.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoByEccDvo;
import com.kyowon.sms.common.web.customer.contact.dvo.ZcsaCstCtplcBasDvo;

@Mapper
public interface WcsaCustomerMapper {

    int updateIndvCstBasEai(ZcsaCustomerInfoByEccDvo dvo);

    int updateItgCstBasEai(ZcsaCustomerInfoByEccDvo dvo);

    int updateCrpCstBasEai(ZcsaCustomerInfoByEccDvo dvo);

    //3. 고객상세등록 - 등록 8. 핸드폰 END DAY 업데이트
    int updateLastCrpCstMpnoInfo(String cstNo, String endDate, String dtaDlYn, String rgstMdfcUsrId);

    //2. 고객등록 - 등록 7.개인고객 휴대전화번호 정보 생성
    int insertCrpCstMpnoInfo(@Param("item")
    ZcsaCstCtplcBasDvo dvo);

}
