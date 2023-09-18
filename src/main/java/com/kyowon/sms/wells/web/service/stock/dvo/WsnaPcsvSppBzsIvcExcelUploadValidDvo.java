package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;
import java.util.Map;

import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvSppBzsIvcExcelUploadValidDvo {

    private WsnaPcsvSppBzsIvcExcelUploadDvo dvo;

    private int row;

    private Map<String, String> headerTitle;

    List<ExcelUploadErrorDvo> errorDvos;
}
