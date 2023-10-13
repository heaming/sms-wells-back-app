package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;
import java.util.Map;

import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0129M01 모종 출고가능수량 관리 유효성검증 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-27
 */

@Getter
@Setter
public class WsnaSeedingOstrQtyValidDvo {

    // 모종 출고가능수량 관리 excel dvo
    private WsnaSeedingOstrQtyExcelDvo dvo;

    // row 번호
    private int row;

    // 패키지구분코드 리스트
    private List<String> pkgDvCds;
    // 서비스업무대분류코드 리스트
    private List<String> svBizHclsfCds;
    // 엑셀 헤더 타이틀
    private Map<String, String> headerTitle;
    // 엑셀 업로드 오류 리스트
    List<ExcelUploadErrorDvo> errorDvos;
}
