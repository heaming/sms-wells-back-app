package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0080 설치, A/S 해피콜 발송(리뉴얼)
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.01.26
 */
@Getter
@Setter
public class WsnbInstallationHpcallDvo {
    String cntrNo; /* 계약번호 */
    String bcNo; /* 고객 QR 코드 */
    String prdtCd; /* 물품코드?? */
    String prdtNm; /* 물풀명 (get_item_nm 사용) */
    String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    String mexnoEncr; /* 휴대전화국번호암복호화 */
    String cralIdvTno; /* 휴대개별전화번호 */
    String wkExcnDt; /* 작업수행일자 */
    String sellTpCd; /* 판매유형코드 */
    String wkPrtnrNo; /* 작업파트너번호 */
    String prtnrKnm; /* 파트너한글명 */
    String ogId; /* 조직ID */
    String ogNm; /* 조직명 */
    String rcgvpKnm; /* 고객명 */
    String svBizHclsfcd; /* 서비스업무대분류코드- 고객서비스배정번호로 합쳐졌는데 별도로 존재함. SV_BIZ_HCLSF_CD */
    String cstSvAsnNo; /* 고객서비스배정번호 */
    String svBizDclsfCd; /* 서비스업무세분류코드 */
    String alncBzsCd; /* 제휴업체코드? */
    String wkCntrNo; /* 작업결과내역 - 계약번호 */
    String wkCntrSn; /* 작업결과내역 - 계약일련번호 */
    String wkCstSvAsnNo; /* 작업결과내역 - 고객서비스배정번호 */
    String wkPntnrNo; /* 작업결과내역 - 작업파트너번호 */
    String qrGb; /*QR구분코드?? (테이블미정) */
    String mexnoEncrEnc; /* 휴대전화국번호암호화 */

    String chkDt; /* 현재날짜 */
    String chkTm; /* 현재시간 */
    String percent; /* 조회 건수 퍼센트 */
}
