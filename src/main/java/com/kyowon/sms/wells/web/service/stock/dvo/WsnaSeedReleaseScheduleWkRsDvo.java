package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 작업 결과 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-05
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleWkRsDvo {

    // 고객서비스배정번호
    private String cstSvAsnNo;
    // 계약번호
    private String cntrNo;
    // 계약일련번호
    private int cntrSn;
    // 제품코드
    private String pdctPdCd;
    // 서비스업무대분류코드
    private String svBizHclsfCd;
    // 서비스업무세분류코드
    private String svBizDclsfCd;
    // 유무상구분코드
    private String refriDvCd;
    // AS위치코드
    private String acLctCd;
    // AS현상코드
    private String asPhnCd;
    // AS원인코드
    private String asCausCd;
    // 불량구분코드
    private String badDvCd;
    // 비고
    private String svProcsCn;

    // 휴대지역전화번호
    private String cstCralLocaraTno;
    // 휴대전화국번호암호화
    @DBEncField
    private String cstMexnoEncr;
    // 휴대개별전화번호
    private String cstCralIdvTno;
}
