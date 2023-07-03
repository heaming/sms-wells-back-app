package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchCntrRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchContractReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccNormalAccountExceptDvo;

@Mapper
public interface WdccNormalAccountExceptMapper {
    List<SearchRes> selectProduct(
        SearchReq dto
    );

    List<SearchCntrRes> selectContract(
        SearchContractReq dto
    );

    int insertExceptMng(WdccNormalAccountExceptDvo dvo);

    int updateExceptMng(WdccNormalAccountExceptDvo dvo);

    int deleteExceptMng(String nomAccExcdIds);
}
