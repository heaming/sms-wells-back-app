package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0129M01 모종 출고가능수량 관리 엑셀 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-27
 */

@Getter
@Setter
public class WsnaSeedingOstrQtyExcelDvo {

    private List<ExcelUploadErrorDvo> errorDvos;

    private List<WsnaSeedingOstrQtyDvo> seedingDvos;

}
