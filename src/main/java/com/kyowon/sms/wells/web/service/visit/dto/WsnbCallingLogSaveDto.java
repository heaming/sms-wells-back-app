package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 * <pre>
 * W-SV-S-0013 PR_KIWI_WRK_CREATE_V2M 호출 로그 저장
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.08
 */
public class WsnbCallingLogSaveDto {

    @ApiModel(value = "WsnbCallingLogSaveDto-CreateReq")
    public record CreateReq(
        /* TODO : 로그 저장 테이블 확정되면 정비 */
        @NotBlank
        String asIstOjNo, /* AS설치대상번호 */
        @NotBlank
        String histChDtm, /* 이력변경일시 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String cntrCst_no, /* 계약고객번호 */
        String inChnlDvCd, /* 입력채널구분코드 */
        String svBizHclsfCd, /* 서비스업무대분류코드 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String rcpSvBizDclsfCd, /* 접수서비스업무세분류코드 */
        String rcpdt, /* 접수일자 */
        String rcpHh, /* 접수시간 */
        @NotBlank
        String urgtYn, /* 긴급여부 */
        String vstRqdt, /* 방문요청일자 */
        String vstAkHh, /* 방문요청시간 */
        String cnslTpHclsfCd, /* 상담유형대분류코드 */
        String cnslTpMclsfCd, /* 상담유형중분류코드 */
        String cnslTpLclsfCd, /* 상담유형소분류코드 */
        String asRefriDvCd, /* AS유무상구분코드 */
        String bfsvcRefriDvCd, /* BS유무상구분코드 */
        @NotBlank
        String smsFwYn, /* SMS발송여부 */
        String dpDvCd, /* 입금구분코드 */
        String svEtAmt, /* 서비스예상금액 */
        String svCnrOgId, /* 서비스센터조직ID */
        String mrtStatCd, /* 자료상태코드 */
        String pdCd, /* 상품코드 */
        String pdGdCd, /* 상품등급코드 */
        String pdUswyCd, /* 상품용도코드 */
        String cstSvAsnNo, /* 고객서비스배정번호 */
        String cnslMoCn, /* 상담메모내용 */
        String cnslDtlpTpCd, /* 상담세부유형코드 */
        String asAkDvCd1, /* AS요청구분코드1 */
        String asAkDvCd2, /* AS요청구분코드2 */
        String istllKnm, /* 설치자한글명 */
        String adrDvCd, /* 주소구분코드 */
        String istAdr /* 설치주소 */
    ) {}

}
