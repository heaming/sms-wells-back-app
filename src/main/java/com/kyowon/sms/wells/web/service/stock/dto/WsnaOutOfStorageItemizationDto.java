package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0141M01 출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.02
 */
public class WsnaOutOfStorageItemizationDto {

    @ApiModel(value = "WsnaOutOfStorageManagementDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @Valid
        String stOstrDt, /*시작출고일자*/
        @NotBlank
        @Valid
        String edOstrDt, /*종료출고일자*/
        String ostrTpCd, /*출고유형코드*/
        @NotBlank
        String ostrWareNo, /*출고창고번호*/
        String strWareDvCd, /*입고창고구분코드*/
        String strWareNoM, /* 입고창고상위 */
        String strWareNoD /* 입고창고개인 */

    ) {}

    @ApiModel(value = "WsnaOutOfStorageManagementDto-SearchRes")
    public record SearchRes(
        String ostrTpCd, /* 출고구분코드 */
        String ostrWareNo, /* 출고창고번호 */
        String strWareNo, /* 입고창고번호 */
        String itmOstrNo, /* 품목출고번호 */
        String itmStrNo, /* 품목입고번호 */
        String ostrAkNo, /* 출고요청번호 */
        String ostrAkSn, /* 출고요청순번 */
        String strHopDt, /* 입고희망일자 */
        String ostrDt, /* 출고일자 */
        String wareNm, /* 창고명 */
        String wareAdrId, /* 창고주소ID */
        String txtNote /* 비고 */

    ) {}
}
