package com.kyowon.sms.wells.web.competence.voc.mapper;

import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshVocReceiptPsicMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpshVocReceiptPsicMngtMapper {
    PagingResult<WpshVocReceiptPsicMngtDvo> selectVocReceiptPsicMngtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    int insertVocReceiptPsic(WpshVocReceiptPsicMngtDvo dvo);

    int updateVocReceiptPsic(WpshVocReceiptPsicMngtDvo dvo);

    int deleteVocReceiptPsic(WpshVocReceiptPsicMngtDvo dvo);
}
