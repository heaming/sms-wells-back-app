package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SearchNewChannelBaseReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SearchNewChannelBaseRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyNewChannelBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyNewChannelBaseMapper {
    List<SearchNewChannelBaseRes> selectNewChannelBaseList(SearchNewChannelBaseReq req);
    PagingResult<SearchNewChannelBaseRes> selectNewChannelBaseList(SearchNewChannelBaseReq req, PageInfo pageInfo);
    int selectValidNewChannelBase(WfeyNewChannelBaseDvo req);
    int insertNewChannelBase(WfeyNewChannelBaseDvo req);
    int updateNewChannelBase(WfeyNewChannelBaseDvo req);
    int deleteNewChannelBase(WfeyNewChannelBaseDvo req);

}
