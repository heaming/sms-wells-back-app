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

    private WsnaSeedingOstrQtyExcelDvo dvo;

    private int row;

    private List<String> pkgDvCds;

    private List<String> svBizHclsfCds;

    private Map<String, String> headerTitle;

    List<ExcelUploadErrorDvo> errorDvos;
}
