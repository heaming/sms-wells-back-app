package com.kyowon.sms.wells.web.closing.expense.dvo;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
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
    private String locaraTno; // 지역번호
    @DBEncField
    @DBDecField
    private String exnoEncr; // 전화국별
    private String idvTno; // 개별전화번호
    private String ogTpCd;
    private String clingCostSrcpApnFileId;
}
