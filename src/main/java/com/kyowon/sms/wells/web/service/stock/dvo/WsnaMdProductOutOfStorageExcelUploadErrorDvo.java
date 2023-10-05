package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaMdProductOutOfStorageExcelUploadErrorDvo {

    int dataRow;
    List<String> errors;
}
