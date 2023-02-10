package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WcteSalesLimitDto.SearchEntrpJLmOjReq;
import static com.kyowon.sms.wells.web.contract.risk.dto.WcteSalesLimitDto.SearchEntrpJLmOjRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dvo.WcteSellLmOjIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WcteSalesLimitMapper {

    PagingResult<SearchEntrpJLmOjRes> selectEntrepreneurJoinLmOjss(
        SearchEntrpJLmOjReq dto,
        PageInfo pageInfo
    );

    List<SearchEntrpJLmOjRes> selectEntrepreneurJoinLmOjss(
        SearchEntrpJLmOjReq dto
    );

    int insertEntrepreneurJoinLmOjss(WcteSellLmOjIzDvo dvo);

    int updateEntrepreneurJoinLmOjss(WcteSellLmOjIzDvo dvo);

    int deleteEntrepreneurJoinLmOjss(String sellLmId);

    String selectEntrepreneurJoinLmOjssCheck(String sellLmId);
}
