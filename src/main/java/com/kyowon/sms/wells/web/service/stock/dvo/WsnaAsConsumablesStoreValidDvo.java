package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * W-SV-U-0013M01 AS소모품 입고관리(엑셀업로드) 유효성검증 dvo
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.08.25
 */
@Getter
@Setter
public class WsnaAsConsumablesStoreValidDvo {
    private WsnaAsConsumablesStoreDvo dvo;

    private int row;

    private Map<String, String> headerTitle;

    List<ExcelUploadErrorDvo> errorDvos;
}
