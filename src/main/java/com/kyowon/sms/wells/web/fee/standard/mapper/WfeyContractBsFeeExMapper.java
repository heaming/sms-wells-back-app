package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SearchContractBsFeeExRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyContractBsFeeExDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeyContractBsFeeExMapper {
    List<SearchContractBsFeeExRes> selectContractBsFeeExList(SearchContractBsFeeExReq req);
    PagingResult<SearchContractBsFeeExRes> selectContractBsFeeExList(SearchContractBsFeeExReq req, PageInfo pageInfo);

    int selectMaxBaseChTcnt(String cntrNo, int cntrSn);
    int selecDuplicateContractBsFeeEx(WfeyContractBsFeeExDvo req);
    int insertContractBsFeeEx(WfeyContractBsFeeExDvo req);
    int updateContractBsFeeEx(WfeyContractBsFeeExDvo req);
    int deleteContractBsFeeEx(WfeyContractBsFeeExDvo req);

}
