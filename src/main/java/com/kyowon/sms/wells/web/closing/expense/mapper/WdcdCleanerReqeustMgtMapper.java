package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanerReqeustDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCleanerReqeustMgtMapper {

    List<CodeRes> selectBuilDingCd();

    String selectClinrRgno(SaveReq req);

    int insertCleanerReqeust(WdcdCleanerReqeustDvo dvo);

    int updateCleanerReqeust(WdcdCleanerReqeustDvo dvo);

    FindRes selectCleanerReqeust(String clinrRgno);
}
