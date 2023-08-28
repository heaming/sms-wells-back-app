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
}
