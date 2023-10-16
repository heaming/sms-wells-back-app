package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbncCustomerCenterHistoryDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 고객센터 상담이력 조회 Find Result Dto
    @Builder
    @ApiModel(value = "WbncCustomerCenterHistoryDto-FindRes")
    public record FindRes(
        String rownum, /* 번호 */
        String cnslNo, /* 상담번호 */
        String cnslDt, /* 상담일자 */
        String cnslStDtTm, /* 상담시작일시 */
        String cstNo, /* 고객번호 */
        String jobCd, /* 업무구분 */
        String jobNm, /* 업무구분명 */
        String centerCd, /* 센터코드 */
        String centerNm, /* 센터명 */
        String sellTpCd, /* 판매유형코드 */
        String cntrDtlNo, /* 계약상세번호 */
        String cnslCn, /* 상담내용 */
        String cstNm, /* 고객명 */
        String telNo, /* 전화번호 */
        String cnslEdDt, /* 상담종료일자 */
        String cnslEdTm, /* 상담종료시간 */
        String callTpCd, /* 콜유형코드 */
        String regDt, /* 입력일자 */
        String regTm, /* 입력시간 */
        String regUserId, /* 사용자ID */
        String regUserNm, /* 사용자이름 */
        String modDt, /* 수정일자 */
        String modTm, /* 수정시간 */
        String modUserId, /* 수정사용자ID */
        String modUserNm /* 수정사용자이름 */
    ) {}
}
