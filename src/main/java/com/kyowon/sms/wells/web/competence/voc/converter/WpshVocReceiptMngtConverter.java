package com.kyowon.sms.wells.web.competence.voc.converter;


import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptMngtDto.*;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshVocReceiptMngtDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpshVocReceiptMngtConverter {
    WpshVocReceiptMngtDvo mapSearchDtlReq(SearchDtlReq dto);

    WpshVocReceiptMngtDvo mapSaveReq(SaveReq dto);

    WpshVocReceiptMngtDvo mapSaveEvlReq(SaveEvlReq dto);

    WpshVocReceiptMngtDvo mapSaveProcsReq(SaveProcsReq dto);

    WpshVocReceiptMngtDvo mapSaveRlyReq(SaveRlyReq dto);

    WpshVocReceiptMngtDvo mapSaveRcpReq(SaveRcpReq dto);
}
