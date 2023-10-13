package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 새싹재배기 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-09-20
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleSproutDvo {

    // 고객서비스배정번호
    private String cstSvAsnNo;
    // 계약번호
    private String cntrNo;
    // 계약일련번호
    private int cntrSn;
    // 서비스업무대분류코드
    private String svBizMclsfCd;
    // 서비스업무세분류코드
    private String svBizDclsfCd;
    // 작업일련번호
    private int wkSn;
    // 방문예정일자
    private String vstDuedt;
    // 품목코드
    private String pdCd;
    // 서비스품목코드
    private String svPdCd;
    // 관리자구분코드
    private String mngrDvCd;
}
