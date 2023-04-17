package com.kyowon.sms.wells.web.closing.expense.dvo;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WdcdRequestCleaningSuppliesDvo {

    private String clingCostAdjRcpNo;
    private String aplcDt;
    private String bldCd;
    private String cardPsrNm;
    private String claimNm;
    List<AttachFile> attachFiles;
    private String clingCostSignApnFileId;
    private String dtaDlYn;
    private String contact;
    private String bilAmt;
}
