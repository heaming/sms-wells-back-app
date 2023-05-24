package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0211P01 일자별수불현황(팝업)
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.05.23
 */
public class WsnaDateReceivingAndPayingDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 일자별수불현황 Search Request Dto
    @Builder
    @ApiModel("WsnaDateReceivingAndPayingDto-SearchReq")
    public record SearchReq(
        String itmPdCd,
        String wareNo,
        String strRgstFrom,
        String strRgstTo

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 일자별수불현황 Search Result Dto
    @ApiModel("WsnaDateReceivingAndPayingDto-SearchRes")
    public record SearchRes(
        String ymd,
        String basStocQty,
        String prchsStrQty,
        String nomStrQty,
        String qomAsnStrQty,
        String qomMmtStrQty,
        String rtngdStrInsdQty,
        String rtngdStrOtsdQty,
        String etcStrQty,
        String strCtrQty,
        String cnfmPitmStrGapQty,
        String nomOstrQty,
        String svcNomOstrQty,
        String sellNomOstrQty,
        String qomAsnOstrQty,
        String qomMmtOstrQty,
        String rtngdOstrInsdQty,
        String rtngdOstrOtsdQty,
        String useQty,
        String refrOstrQty,
        String sellOstrQty,
        String dsuOstrQty,
        String etcOstrQty,
        String ostrCtrQty,
        String cnfmPitmOstrGapQty,
        String mmtStocQty
    ) {}
}
