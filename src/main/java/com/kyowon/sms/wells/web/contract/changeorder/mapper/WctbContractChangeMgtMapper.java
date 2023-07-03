package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeDvo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbContractChangeMgtMapper {
    PagingResult<SearchContractChangeRes> selectContractChanges(SearchContractChangeReq dto, PageInfo pageInfo);

    WctbContractChangeMngtDto.FindPartnerRes selectPartnerByCntrNo(String cntrNo, String cntrSn);

    WctbContractChangeDvo selectDateTime();

    int insertSellPartnerToCntrChRcpBas(@Param("dvo")
    WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChRcchHist(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChRcpDtl(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChRcpDchHist(WctbContractChangeDvo inputDvo);

    int updateSellPartnerToCntrBas(WctbContractChangeDvo inputDvo);

    int updateExSellPartnerToCntrChHist(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrChHist(WctbContractChangeDvo inputDvo);

    int updateSellPartnerToCntrPrtnrRel(WctbContractChangeDvo inputDvo);

    int insertSellPartnerToCntrPrtnrRel(WctbContractChangeDvo inputDvo);
}
