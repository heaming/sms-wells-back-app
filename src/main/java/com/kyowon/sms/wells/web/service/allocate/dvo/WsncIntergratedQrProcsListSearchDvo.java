package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * K-W-SV-U-0239M01 통합QR 처리현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.25
 */
@Setter
@Getter
public class WsncIntergratedQrProcsListSearchDvo {

    String mngrDvCd;
    String baseDateFrom;
    String baseDateTo;
    String dgr1LevlOgId;
    String dgr2LevlOgId;
    String svOgId;

    /* 조직별 집계 */
    String dgr1LevlOgNm;
    String dgr2LevlOgNm;
    String dgr2LevlDgPrtnrNm;
    String bmNm;

    /* 조직 상세 집계 */
    String ogNm;
    String prtnrNo;
    String prtnrNm;
    String bldNm;

    /* 공통변수 */
    int bsCmpltd;
    int itgQrTrgt;
    int itgQrCmpltd;
    int nrmQrTrgt;
    int nrmQrCmpltd;
    int qrTrgt;
    int qrCmpltd;
    String attachRate;
}
