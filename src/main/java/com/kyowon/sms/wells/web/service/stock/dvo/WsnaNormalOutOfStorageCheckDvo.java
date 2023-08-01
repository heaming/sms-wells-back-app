package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0179P01 정상출고등록 체크 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-01
 */

@Getter
@Setter
public class WsnaNormalOutOfStorageCheckDvo {

    private String ostrAkNo;
    private int ostrAkSn;

}
