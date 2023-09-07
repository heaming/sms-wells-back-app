package com.kyowon.sms.wells.web.closing.expense.dvo;

import java.util.List;

import com.sds.sflex.common.docs.dto.AttachFileDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdcdOperatingCostDvo {
    String opcsCardId;
    List<AttachFileDto.AttachFile> attachMscrWhtxCfdcApnFileId;
    String mscrWhtxCfdcApnFileId;
}
