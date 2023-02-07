package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto.SearchRes;;

@Mapper
public interface WctbCancellationMtrSetMapper {
    List<SearchRes> selectContractBase(SearchReq seq);

    int updateContractDetail(SearchRes res);

    int insertContractDetailHist(SearchRes res);
}
