
package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 *  담당자별 정기방문 처리 집계표
 * </pre>
 *
 * @author junggheejin
 * @since 2023.07.17
 */
public class WsnbPersonInChargeVisitAgrgDto {

    @ApiModel(value = "WsnbPersonInChargeVisitAgrgDto-SearchReq")
    public record SearchReq(
        String startDt, /* 처리실작일자 */
        String endDt, /* 처리종료일자 */
        String asnOjYm, /* 배정년월 */
        String mngrDvCd, /* 관리구분 */
        String dgr1LevlOgId, /* 총괄단 */
        String dgr2LevlOgId, /* 지역단 */
        String dgr3LevlOgId, /* 지점 */
        String prtnrNo, /* 파트너번호 */
        String ogId, /* 서비스센터 */
        String bldCd /* 빌딩코드 */
        //        String exceptWellsManagerYn /* 웰스매니저 미관리 제외 */  // 미사용
    ) {}

    @ApiModel(value = "WsnbPersonInChargeVisitAgrgDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String mngtPrtnrOgTpCd,
        String mngtPrtnrNo,
        String prtnrKnm,
        String pstnDvCd,
        String pstnDvCdNm,
        String rsbDvCd, /* 직책*/
        String bldMngtPrtnrNo, /* 빌딩별 자재실장 사번*/
        String bldMngtPrtnrKnm, /* 빌딩별 자재실장 성명*/
        String bldNm, /* 빌딩명 */
        String mngtYm,
        String mngtCstCnt, /*  관리 고객수 */
        String notMngtExcdCstCnt, /* 미관리제외 고객수 */
        String asnOjSumCnt, /* 배정 대상 계 */
        String vstCstCnt, /* 방문 고객수  */
        String vstStpCstCnt, /* 방문 중지 고객수 */
        String vstCanCstCnt, /* 방문 취소 고객수 */
        String vstAccChngCstCnt, /* 방문 계정 교체 고객수  */
        String vstAccChkCstCnt, /* 방문 계정 점검 고객수 */

        String vstAccSumCnt, /* 방문 계정 계 */
        String vstFshAccChngCstCnt, /*  방문 완료 교체 고객수 */
        String vstFshAccChkCstCnt, /*  방문 완료 점검 고객수 */

        String vstFshAccSumCnt, /* 방문 완료 계정 계 */
        String vstFshAccRt, /* 방문 완료 계정 방문율 */
        String wbVstCstCnt, /* 웰컴bs 방문 고객수  */
        String wbVstFshCstCnt, /* 웰컴bs 방문 완료 고객수 */
        String wbVstCanCstCnt, /* 웰컴bs 방문 취소 고객수 */
        String wbNotVstCstCnt, /* 웰컴bs 미방문 고객수 */
        String svCntcFshCstCnt, /* 서비스 컨택 완료 고객수 */
        String svCntcImpCstCnt, /* 서비스 미컨택 고객수 */
        String svCntcVstImpCstCnt, /* 서비스 방문 불가 고객수  */
        String svCntcStnbCstCnt, /* 매핑안됨 */
        String svCntcSumCnt, /* 서비스 컨택 계  */
        String svCntcRt /* 서비스 컨택 컨택율  */
    ) {}

    @ApiModel(value = "WsnbPersonInChargeVisitAgrgDto-FindBldRes")
    public record FindBldRes(
        String bldCd,
        String bldNm
    ) {}
}
