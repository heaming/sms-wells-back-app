package com.kyowon.sms.wells.web.competence.business.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfActivityGoodsMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.*;

@Mapper(componentModel = "spring")
public interface WpsfActivityGoodsMgtConverter {

    WpsfActivityGoodsApplicationBaseDvo mapSaveBaseReq(WpsfActivityGoodsMgtDto.SaveReq dto);

    WpsfActivityGoodsBaseDvo mapSaveActivityReq(WpsfActivityGoodsMgtDto.EditReq dto);

    WpsfActivityGoodsBaseDvo mapSaveRemoveReq(WpsfActivityGoodsMgtDto.RemoveReq dto);

    WpsfActivityGoodsAplcIzDvo mapSaveApplicationReq(WpsfActivityGoodsMgtDto.SaveApplicationReq dto);

    WpsfActiGdsAplcStatIzDvo mapCencelApplicationReq(WpsfActivityGoodsMgtDto.EditApplicationReq dto);

    WpsfActivityGoodsDeductionItemizationDvo mapSaveDeductionItemizationReq(WpsfActivityGoodsMgtDto.EditDeductionReq dto);

    WpsfActivityGoodsDeductionItemizationDvo mapRemoveDeductionItemizationReq(WpsfActivityGoodsMgtDto.RemoveDeductionReq dto);

    WpsfActivityGoodsSizeDvo mapSaveSizeReq(WpsfActivityGoodsMgtDto.EditSizeReq dto);

    WpsfActivityGoodsSizeDvo mapRemoveSizeReq(WpsfActivityGoodsMgtDto.RemoveSizenReq dto);

    WpsfActivityGoodsSizeDetailDvo mapSaveSizeDetailReq(WpsfActivityGoodsMgtDto.EditSizeDetailReq dto);

    WpsfActivityGoodsSizeDetailDvo mapRemoveSizeDetailReq(WpsfActivityGoodsMgtDto.RemoveSizenDetailReq dto);
}
