package com.kyowon.sms.wells.web.competence.voc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import org.eclipse.jetty.util.StringUtil;

public class WpshVocReceiptPsicMngtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수담당자 관리 Search Request Dto
    @Builder
    @ApiModel("WpshVocReceiptPsicMngtDto-SearchReq")
    public record SearchReq(
        String vocBizTpCd,
        String ipvmProsTpCd,
        String mattVocDvTpCd,
        String ichrDeptId
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // VOC 접수담당자 관리 Search Result Dto
    @ApiModel("WpshVocReceiptPsicMngtDto-SearchRes")
    public record SearchRes(
        String vocBizAsnId,
        String vocBizTpDtlCd,
        String dgYn,
        String ogTpCd,
        String prtnrNo,
        String prtnrKnm,
        String ogNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno
    ) {
        public SearchRes {
            if (StringUtil.isNotBlank(cralLocaraTno) && StringUtil.isNotBlank(mexnoEncr)
                && StringUtil.isNotBlank(cralIdvTno)) {
                mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
            }
        }
    }

    // *********************************************************
    // Save Request Dto
    // *********************************************************
    // VOC 접수담당자 관리 Save Request Dto
    @ApiModel("WpshVocReceiptPsicMngtDto-SaveReq")
    public record SaveReq(
        String vocBizAsnId,
        String ogTpCd,
        String dgYn,
        String prtnrNo,
        String vocBizTpDtlCd,
        String rowState
    ) {
    }
}
