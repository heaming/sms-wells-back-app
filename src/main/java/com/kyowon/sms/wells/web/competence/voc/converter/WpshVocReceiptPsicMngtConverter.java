package com.kyowon.sms.wells.web.competence.voc.converter;

import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshVocReceiptPsicMngtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpshVocReceiptPsicMngtConverter {
    WpshVocReceiptPsicMngtDvo mapSaveReq(SaveReq dto);

    PagingResult<SearchRes> dvoToSearchRes(PagingResult<WpshVocReceiptPsicMngtDvo> dvo);
}
