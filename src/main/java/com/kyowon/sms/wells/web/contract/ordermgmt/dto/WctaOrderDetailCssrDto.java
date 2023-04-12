package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDetailCssrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    // 기본정보 FindRes
    @Builder
    @ApiModel("WctaOrderDetailCssrDto-FindBaseRcpRes")
    public record FindBaseRcpRes(
        String cssrIsDvCd, /* 현금영수증발급구분코드 */
        String cssrIsNo, /* 현금영수증발급번호 */
        String chRsonCn /* 마지막 변경사유 */
    ) {}
    @Builder
    @ApiModel("WctaOrderDetailCssrDto-FindBaseRcpRes")
    public record SearchRcpRes(

        String rveDt, /* 수납일자 */
        String ocssrIsDvCd, /* 변경 전 현금영수증발급구분코드 */
        String orcssrIsNo, /* 변경 전 현금영수증발급번호 */
        String orcssrTrdAmt, /* 변경 전 금액 */
        String orcssrAprRsCd, /* 변경 전 승인결과 */
        String cssrIsNo, /* 현금영수증발급번호 */
        String chRsonCn, /*  변경사유 */
        String fnlMdfcDtm, /* 등록일 */
        String fnlMdfcUsrNm, /* 등록자 이름 */
        String fnlMdfcUsrId /* 등록자 사번*/
    ) {}
}
