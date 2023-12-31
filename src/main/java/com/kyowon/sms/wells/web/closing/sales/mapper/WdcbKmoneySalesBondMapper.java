package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchBondRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchCancelRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbKmoneySalesBondDto.SearchDepositRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WdcbKmoneySalesBondMapper {

    PagingResult<SearchBondRes> selectSalesBondPages(String baseYr, PageInfo pageInfo);

    List<SearchBondRes> selectSalesBondPages(String baseYr);

    List<SearchDepositRes> selectDepositDetails(@Param("baseYm")
    String baseYm, @Param("thisMonthYn")
    String thisMonthYn);

    List<SearchCancelRes> selectCancelDetails(String baseYm);
}
