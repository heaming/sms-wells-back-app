package com.kyowon.sms.wells.web.customer.contact.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoByEccDvo;
import com.kyowon.sms.common.web.customer.contact.dvo.ZcsaCstCtplcBasDvo;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaCustomerAgreementDvo;

/**
 * <pre>
 * 고객 인터페이스 관리 Wells Dto
 * </pre>
 * @author Jaeyeol.Lee
 * @since 2023-05-04
 */
@Mapper
public interface WcsaCustomerMapper {

    /**
    * 고객정보변경
    * @param dvo 고객정보
    * @return 변경결과
    */
    int updateIndvCstBasEai(ZcsaCustomerInfoByEccDvo dvo);

    /**
    * 통합고객정보변경
    * @param dvo 통합고객정보
    * @return 변경결과
    */
    int updateItgCstBasEai(ZcsaCustomerInfoByEccDvo dvo);

    /**
    * 법인고객관계자상세 연락처변경
    * @param dvo 휴대폰번호 등
    * @return 변경결과
    */
    int updateCrpCstBasEai(ZcsaCustomerInfoByEccDvo dvo);

    /**
    * 법인고객관계자상세등록 - 휴대전화번호 END DAY 업데이트
    * @param cstNo endDate dtaDlYn rgstMdfcUsrId
    * @return 변경결과
    */
    int updateLastCrpCstMpnoInfo(String cstNo, String endDate, String dtaDlYn, String rgstMdfcUsrId);

    /**
    * 연락처-휴대폰-법인- 고객 직장전화번호 등록
    * @param dvo 고객정보
    * @return 등록결과
    */
    int insertCrpCstMpnoInfo(@Param("item")
    ZcsaCstCtplcBasDvo dvo);

    String selectCustomerExistYn(String cstNo);

    String selectCustomerRecentAgreement(String cstNo);

    /**
    * 고객약관동의내역
    * @param agreeDvo 약관동의내역
    * @return 등록결과
    */
    int insertCustomerAgreement(@Param("item")
    WcsaCustomerAgreementDvo agreeDvo);

    int insertCustomerAgreementDetail(WcsaCustomerAgreementDvo agreeDvo);

    int updateCustomerAgreementExpiration(String preCstAgId);

    int updateCustomerAgreementDetailExpiration(String preCstAgId);
}
