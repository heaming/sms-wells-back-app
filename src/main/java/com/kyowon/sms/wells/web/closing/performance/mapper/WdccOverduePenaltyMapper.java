package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccOverduePenaltyMapper {

    List<FindCodeRes> selectCode();

    List<SearchMainGridRes> selectMainGridAggregate(SearchReq req);

    List<SearchMainGridRes> selectMainGridDates(SearchReq req);

    List<SearchSubGridRes> selectSubGridOrder(SearchReq req);

    List<SearchThirdGridRes> selectThirdGridAggregate(SearchReq req);

    List<SearchFourthGridRes> selectFourthGridOrder(SearchReq req);
}
