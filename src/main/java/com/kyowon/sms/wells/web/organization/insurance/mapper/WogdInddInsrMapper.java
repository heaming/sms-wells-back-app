package com.kyowon.sms.wells.web.organization.insurance.mapper;


import com.kyowon.sms.wells.web.organization.insurance.dto.WogdInddInsrDto.SearchReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdInddInsrDto.SearchRes;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdInddInsrDto.SearchPrtnrReq;
import com.kyowon.sms.wells.web.organization.insurance.dvo.WogdInddInsrDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * 산재보험
 * </pre>
 * test
 *
 * @author 이한울
 * @since 2023-04-27
 */
@Mapper
public interface WogdInddInsrMapper {
    PagingResult<SearchRes> selectIndustrialDisasterInsurances(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectIndustrialDisasterInsurances(SearchReq dto);

    int updateIndustrialDisasterInsurances(WogdInddInsrDvo dvo);

    int selectCountIndustrialDisasterInsurances(SearchPrtnrReq dto);

    int updateIndustrialDisasterInsurancesForExcelupload(List<WogdInddInsrDvo> dvos);
}

