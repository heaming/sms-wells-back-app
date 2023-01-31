package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchByCntrNoReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchInfoRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIcptSellChHistDvo;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIncompletenessSellIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcIncompletenessSalesMapper {

    String isValidCntrs(
        SearchByCntrNoReq dto
    );

    List<SearchInfoRes> selectIncompletenessSales(
        SearchByCntrNoReq dto
    );

    PagingResult<SearchInfoRes> selectIncompletenessSalePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchInfoRes> selectIncompletenessSalePages(
        SearchReq dto
    );

    int insertIncompletenessSales(WctcIncompletenessSellIzDvo dvo);

    int updateIncompletenessSales(WctcIncompletenessSellIzDvo dvo);

    int deleteIncompletenessSales(String icptSellId);

    int insertIncompletenessSalesHist(WctcIcptSellChHistDvo dvo);

    int updateIncompletenessSalesPrevHist(WctcIcptSellChHistDvo dvo);

}
