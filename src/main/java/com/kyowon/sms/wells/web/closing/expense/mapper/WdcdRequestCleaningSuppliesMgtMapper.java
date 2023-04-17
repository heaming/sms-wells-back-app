package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdRequestCleaningSuppliesDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdRequestCleaningSuppliesMgtMapper {

    List<CodeRes> selectBuilDingCd();

    String selectClingCostAdjRcpNo(SaveReq req);

    int insertRequestCleaningSupplies(WdcdRequestCleaningSuppliesDvo dvo);

    FindRes selectRequestCleaningSupplies(FindReq req);
}
