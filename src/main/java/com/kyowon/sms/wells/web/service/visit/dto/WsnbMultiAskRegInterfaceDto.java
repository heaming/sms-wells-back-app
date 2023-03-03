package com.kyowon.sms.wells.web.service.visit.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-S-0062 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author yeonghwa.cheon 천영화
 * @since 2023.03.03
 */
public class WsnbMultiAskRegInterfaceDto {

    @ApiModel(value = "WsnbMultiAskRegInterfaceDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String wrkTypDtl,
        @NotBlank
        String wrkGb,
        // String asIstOjNo, // AS설치대상번호
        List<AskingInfo> askingInfos
    ) {}

    @ApiModel(value = "WsnbMultiAskRegInterfaceDto-AskingInfo")
    public record AskingInfo(
        // TODO: 수정 필요
        String ugntYn,
        String smsYn,
        String accAmt,
        String accGb
    ) {}

    @ApiModel(value = "WsnbMultiAskRegInterfaceDto-SaveRes")
    public record SaveRes(
        String asIstOjNo, // AS설치대상번호
        String mtrStatCd, // 자료상태코드
        String svBizDclsfCd, // 서비스업무세분류코드
        String cntrNo, // 계약번호
        String vstRqdt, // 방문요청일자
        String vstAkHh, // 방문요청시간
        String regUserId, // 입력사용자ID
        String urgtYn, // 긴급여부
        String smsFwYn, // SMS발송여부
        String svEtAmt, // 서비스예상금액
        String dpDvCd, // 입금구분코드
        String cnslTpHclsfCd, // 상담유형대분류코드
        String cnslTpMclsfCd, // 상담유형중분류코드
        String cnslTpLclsfCd, // 상담유형소분류코드
        String cnslMoCn, // 상담메모내용
        String etcInf1, // 기타정보1
        String etcInf2, // 기타정보2
        String mtcmco, // 이동통신사
        String cphonIdvTno1, // 휴대폰개별전화번호1
        String cphonIdvTno2, // 휴대폰개별전화번호2
        String locaraTno, // 지역전화번호
        String idvTno1, // 개별전화번호1
        String idvTno2, // 개별전화번호2
        String istZip1, // 설치우편번호1
        String istZip2, // 설치우편번호2
        String istAdr, // 설치주소
        String istDtlAdr, // 설치상세주소
        String refAdr, // 참조주소
        String prchsMatList, // 구매자재리스트
        String dprNm // 입금자명
    ) {}

}
