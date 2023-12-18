package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SearchSapMapNmRes;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSapSalesCompulsionCreateDvo;

@Mapper
public interface WdcbSapSalesCompulsionCreateMapper {
    SearchSapMapNmRes selectSapMapNm(String zsmtrl);

    List<SearchRes> selectSapSales(String baseYm);

    String selectByPK(WdcbSapSalesCompulsionCreateDvo dvo);

    int insertIfinSlInfAtcIz(WdcbSapSalesCompulsionCreateDvo dvo);

    int insertIfinSlInfHdrBas(WdcbSapSalesCompulsionCreateDvo dvo);

    int updateIfinSlInfAtcIz(WdcbSapSalesCompulsionCreateDvo dvo);

    int updateIfinSlInfHdrBas(WdcbSapSalesCompulsionCreateDvo dvo);

    int deleteIfinSlInfAtcIz(WdcbSapSalesCompulsionCreateDvo dvo);

    int deleteIfinSlInfHdrBas(WdcbSapSalesCompulsionCreateDvo dvo);

    int selectIfinSlInfAtcIzCount(WdcbSapSalesCompulsionCreateDvo dvo);
}
