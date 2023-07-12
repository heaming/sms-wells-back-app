package com.kyowon.sms.wells.web.bond.consultation.mapper;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncBondContactExcludeObjectIzDvo;

@Mapper
public interface WbncUnpaidGuideUrgentExcludeMapper {
    List<SearchRes> selectUnpaidGuideUrgentExcludes(SearchReq dto);

    int updateUnpaidGuideUrgentExclude(WbncBondContactExcludeObjectIzDvo dvo);

    int updateUnpaidGuideUrgentExcludeExcelUpload(WbncBondContactExcludeObjectIzDvo dvo);
}
