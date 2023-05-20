package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindCodeReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindCodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanerReqeustDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WdcdCleanerReqeustMgtMapper {

    List<FindCodeRes> selectBuilDingCd(FindCodeReq req);

    String selectClinrRgno(SaveReq req);

    int insertCleanerReqeust(WdcdCleanerReqeustDvo dvo);

    int updateCleanerReqeust(WdcdCleanerReqeustDvo dvo);

    WdcdCleanerReqeustDvo selectCleanerReqeust(String clinrRgno);

    Map<String, Object> selectFileId(String clinrRgno);
}
