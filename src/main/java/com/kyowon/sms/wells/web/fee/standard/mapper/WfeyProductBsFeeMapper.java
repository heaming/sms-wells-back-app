package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyProductBsFeeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyProductBsFeeMapper {
    List<SearchProductBsFeeRes> selectProductBsFeeList(SearchProductBsFeeReq req);
    PagingResult<SearchProductBsFeeRes> selectProductBsFeeList(SearchProductBsFeeReq req, PageInfo pageInfo);
    int selectValidProductBsFee(WfeyProductBsFeeDvo req);
    int insertProductBsFee(WfeyProductBsFeeDvo req);
    int updateProductBsFee(WfeyProductBsFeeDvo req);
    int deleteProductBsFee(WfeyProductBsFeeDvo req);

}
