package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;

public class WbncServiceDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 서비스 상세내역 조회 Find Result Dto
    @ApiModel(value = "WbncServiceDto-FindRes")
    public record FindRes(
        String cstSvAsnNo, /* 고객서비스배정번호 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrNoSn, /* 계약상세번호 */
        String sepIstCsDtlCd, /* 분리설치비용상세코드 */
        String sepIstCsDtlNm, /* 분리설치비용상세명 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String svBizDclsfNm, /* 서비스업무세분류명 */
        String svBizHclsfCd, /* 서비스업무대분류코드 */
        String svBizHclsfNm, /* 서비스업무대분류명 */
        String vstFshDt, /* 방문완료일자 */
        String vstFshHh, /* 방문완료시간 */
        String vstFshDtHh, /* 방문완료일시 */
        String asCausCd, /* AS원인코드 */
        String asCausNm, /* AS원인명 */
        String svProcsCn, /* 서비스처리내용 */
        String bilOjAmt, /* 청구대상금액 */
        String dpSumAmt, /* 입금합계금액 */
        String blam, /* 잔액 */
        String gubun /* 작업상태구분 */
    ) {}
}
