package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchRes;

/**
 * <pre>
 * 채권상담 고객리스트 맵퍼 
 * wbnc-customer.xml
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-02-10
 */
@Mapper
public interface WbncCustomerMapper {
    List<SearchRes> selectCustomers(SearchReq dto);

    Optional<FindRes> selectCustomerDetail(FindReq dto);

    List<FindCustomerDetailRes> selectCustomerDetails(FindCustomerDetailReq dto);

    List<FindCounselHistoryRes> selectCounselHistorys(FindCounselHistoryReq dto);
}
