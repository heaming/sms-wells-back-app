package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0239M01 통합QR 처리현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.25
 */
public class WsncIntergratedQrProcsListSearchDto {

    @ApiModel(value = "WsncIntergratedQrProcsListSearchDto-SearchReq")
    public record SearchReq(
        String mngrDvCd, //조회구분
        String baseDateFrom, //처리일자from
        String baseDateTo, //처리일자to
        String dgr1LevlOgId, //총괄단
        String dgr2LevlOgId, //지역단
        String svOgId //서비스센터
    ) {}

    @ApiModel(value = "WsncIntergratedQrProcsListSearchDto-SearchByOgRes")
    public record SearchByOgRes(
        String dgr1LevlOgNm, //총괄단
        String dgr2LevlOgNm, //지역단
        String dgr2LevlDgPrtnrNm, //지역단장
        String bmNm, //BM명
        int bsCmpltd, //BS완료
        int itgQrTrgt, //통합QR대상
        int itgQrCmpltd, //통합QR완료
        int nrmQrTrgt, //일반QR대상
        int nrmQrCmpltd, //일반QR완료
        int qrTrgt, //QR대상
        int qrCmpltd, //QR완료
        String attachRate //부착율
    ) {}

    @ApiModel(value = "WsncIntergratedQrProcsListSearchDto-SearchOgDetailRes")
    public record SearchOgDetailRes(
        String ogNm, //소속
        String prtnrNo, //담당자사번
        String prtnrNm, //담당자명
        String bldNm, //빌딩명
        int bsCmpltd, //BS완료
        int itgQrTrgt, //통합QR대상
        int itgQrCmpltd, //통합QR완료
        int nrmQrTrgt, //일반QR대상
        int nrmQrCmpltd, //일반QR완료
        int qrTrgt, //QR대상
        int qrCmpltd, //QR완료
        String attachRate //부착율
    ) {}

    @ApiModel(value = "WsncIntergratedQrProcsListSearchDto-SearchAggrReq")
    public record SearchAggrReq(
        String baseYear //기준년도
    ) {}

    @ApiModel(value = "WsncIntergratedQrProcsListSearchDto-SearchAggrRes")
    public record SearchAggrRes(
        String grpNm, //상품그룹
        int newQr01, //신규QR등록계정
        int sumQr01, //누적QR등록계정
        int wellsAccount01, //Wells누적계정
        String accountRate01,
        int newQr02,
        int sumQr02,
        int wellsAccount02,
        String accountRate02,
        int newQr03,
        int sumQr03,
        int wellsAccount03,
        String accountRate03,
        int newQr04,
        int sumQr04,
        int wellsAccount04,
        String accountRate04,
        int newQr05,
        int sumQr05,
        int wellsAccount05,
        String accountRate05,
        int newQr06,
        int sumQr06,
        int wellsAccount06,
        String accountRate06,
        int newQr07,
        int sumQr07,
        int wellsAccount07,
        String accountRate07,
        int newQr08,
        int sumQr08,
        int wellsAccount08,
        String accountRate08,
        int newQr09,
        int sumQr09,
        int wellsAccount09,
        String accountRate09,
        int newQr10,
        int sumQr10,
        int wellsAccount10,
        String accountRate10,
        int newQr11,
        int sumQr11,
        int wellsAccount11,
        String accountRate11,
        int newQr12,
        int sumQr12,
        int wellsAccount12,
        String accountRate12
    ) {}
}
