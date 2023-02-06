package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-I-0009 타시스템(kyowonwells, CubigCC, kmembers)에서 설치/AS/BS/홈케어 서비스 작업 오더 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.03
 */
public class WsnbServiceWorkInterfaceDto {

    @ApiModel(value = "WsnbServiceWorkInterfaceDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String asIstOjNo, // AS설치대상번호
        @NotBlank
        String cntrNo // 계약번호
    ) {}

    @ApiModel(value = "WsnbServiceWorkInterfaceDto-CreateRes")
    public record CreateRes(
        String asIstOjNo, // AS설치대상번호
        String mtrStatCd, // 자료상태코드
        String svBizDclsfCd, // 서비스업무세분류코드
        String urgtYn, // 긴급여부
        String smsFwYn, // SMS발송여부
        String svEtAmt, // 서비스예상금액
        String dpDvCd, // 입금구분코드
        String cnslTpHclsfCd, // 상담유형대분류코드
        String cnslTpMclsfCd, // 상담유형중분류코드
        String cnslTpLclsfCd, // 상담유형소분류코드
        String cnslDtlpTpCd, // 상담세부유형코드
        String cnslMoCn, // 상담메모내용
        String refriYn, // 유무상여부
        String mnftCoCd, // 제조회사코드
        String cntrNo, // 계약번호
        String vstRqdt, // 방문요청일자
        String vstAkHh, // 방문요청시간
        String regUserId, // 입력사용자ID
        String modUserId // 수정사용자ID
    ) {}

}
