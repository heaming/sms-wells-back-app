package com.kyowon.sms.wells.web.closing.expense.dvo;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WdcdOperatingCostDvo {
    String opcsCardId;
    List<AttachFileDto.AttachFile> attachOpcsWhtxCfdcApnFileId;
    String opcsWhtxCfdcApnFileId;
}
