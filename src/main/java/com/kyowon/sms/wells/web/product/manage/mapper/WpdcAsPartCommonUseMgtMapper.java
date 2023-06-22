package com.kyowon.sms.wells.web.product.manage.mapper;

import static com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartCommonUseMgtDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpdcAsPartCommonUseMgtMapper {

    List<SearchPartRes> selectAsParts(SearchPartReq dto);

    List<SearchProductRes> selectProductsByPart(String partPdCd);
}
