package com.kyowon.sms.wells.web.closing.expense.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdRequestCleaningSuppliesDvo;

@Mapper
public interface WdcdRequestCleaningSuppliesMgtMapper {

    SearchRsbDvCdRes selectRsbDvCd(CodeReq req);

    List<CodeRes> selectBuilDingCd(CodeReq req);

    String selectClingCostAdjRcpNo(SaveReq req);

    int insertRequestCleaningSupplies(WdcdRequestCleaningSuppliesDvo dvo);

//    int updateRequestCleaningSupplies(WdcdRequestCleaningSuppliesDvo dvo);

    FindRes selectRequestCleaningSupplies(String clingCostAdjRcpNo);

    WdcdRequestCleaningSuppliesDvo selectRequestCleaningSuppliesDetail(String clingCostAdjRcpNo);
}
