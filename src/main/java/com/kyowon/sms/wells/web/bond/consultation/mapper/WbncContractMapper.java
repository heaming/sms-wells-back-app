package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncContractDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncContractDto.SearchRes;

/**
 * <pre>
 * 채권상담 계약리스트 맵퍼 
 * wbnc-contract.xml
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-02-10
 */
@Mapper
public interface WbncContractMapper {
    List<SearchRes> selectContracts(SearchReq dto);
}
