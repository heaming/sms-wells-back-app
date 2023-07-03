package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbContractChangeMngtMapper {
    PagingResult<SearchContractChangeRes> selectContractChanges(SearchContractChangeReq dto, PageInfo pageInfo);

    WctbContractChangeMngtDto.FindPartnerRes selectPartnerByCntrNo(String cntrNo, String cntrSn);

    WctbContractChangeMngtDvo selectDateTime();

    int insertSellPartnerToCntrChRcpBas(@Param("dvo")
    WctbContractChangeMngtDvo inputDvo);

    int insertSellPartnerToCntrChRcchHist(WctbContractChangeMngtDvo inputDvo);

    int insertSellPartnerToCntrChRcpDtl(WctbContractChangeMngtDvo inputDvo);

    int insertSellPartnerToCntrChRcpDchHist(WctbContractChangeMngtDvo inputDvo);

    int updateSellPartnerToCntrBas(WctbContractChangeMngtDvo inputDvo);

    int updateExSellPartnerToCntrChHist(WctbContractChangeMngtDvo inputDvo);

    int insertSellPartnerToCntrChHist(WctbContractChangeMngtDvo inputDvo);

    int updateSellPartnerToCntrPrtnrRel(WctbContractChangeMngtDvo inputDvo);

    int insertSellPartnerToCntrPrtnrRel(WctbContractChangeMngtDvo inputDvo);
}
