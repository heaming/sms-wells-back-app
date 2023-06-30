package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0296P01 산출 제외품목 등록 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-23
 */

@Getter
@Setter
public class WsnaComputationExcludeItemDvo {

    // 관리년월
    private String mngtYm;
    // 품목상품코드
    private String itmPdCd;
    // 산출제외일련번호
    private Integer cmptExcdSn;
    // 품목종류코드
    private String itmKndCd;
    // 비고내용
    private String rmkCn;
    // 데이터삭제여부
    private String dtaDlYn;

}
