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

    /* 집계표 */
    String baseYear;

    String grpNm;
    int newQr01; //신규QR등록계정
    int sumQr01; //누적QR등록계정
    int wellsAccount01; //Wells누적계정
    String accountRate01;
    int newQr02;
    int sumQr02;
    int wellsAccount02;
    String accountRate02;
    int newQr03;
    int sumQr03;
    int wellsAccount03;
    String accountRate03;
    int newQr04;
    int sumQr04;
    int wellsAccount04;
    String accountRate04;
    int newQr05;
    int sumQr05;
    int wellsAccount05;
    String accountRate05;
    int newQr06;
    int sumQr06;
    int wellsAccount06;
    String accountRate06;
    int newQr07;
    int sumQr07;
    int wellsAccount07;
    String accountRate07;
    int newQr08;
    int sumQr08;
    int wellsAccount08;
    String accountRate08;
    int newQr09;
    int sumQr09;
    int wellsAccount09;
    String accountRate09;
    int newQr10;
    int sumQr10;
    int wellsAccount10;
    String accountRate10;
    int newQr11;
    int sumQr11;
    int wellsAccount11;
    String accountRate11;
    int newQr12;
    int sumQr12;
    int wellsAccount12;
    String accountRate12;
}
