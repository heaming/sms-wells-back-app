package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-I-0009 타시스템(kyowonwells, CubigCC, kmembers)에서 설치/AS/BS/홈케어 서비스 작업 오더 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.03
 */
@Setter
@Getter
public class WsnbServiceWorkInterfaceDvo {

    String asIstOjNo; // AS설치대상번호
    String inChnlDvCd; // 입력채널구분코드
    String svBizHclsfCd; // 서비스업무대분류코드
    String rcpdt; // 접수일자
    String cntrNo; // 계약번호

}
