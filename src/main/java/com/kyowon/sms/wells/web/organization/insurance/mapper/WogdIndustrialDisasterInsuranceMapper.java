package com.kyowon.sms.wells.web.organization.insurance.mapper;


import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchRes;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchPrtnrReq;
import com.kyowon.sms.wells.web.organization.insurance.dvo.WogdIndustrialDisasterInsuranceDvo;
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
public interface WogdIndustrialDisasterInsuranceMapper {
    PagingResult<SearchRes> selectIndustrialDisasterInsurances(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectIndustrialDisasterInsurances(SearchReq dto);

    int updateIndustrialDisasterInsurances(WogdIndustrialDisasterInsuranceDvo dvo);

    int selectCountIndustrialDisasterInsurances(SearchPrtnrReq dto);

    int updateIndustrialDisasterInsurancesForExcelupload(List<WogdIndustrialDisasterInsuranceDvo> dvos);
}

