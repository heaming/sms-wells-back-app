package com.kyowon.sms.wells.web.competence.interfaces.mapper;

import com.kyowon.sms.wells.web.competence.interfaces.dvo.WpskPinatMetgCheckDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpskPinatMetgCheckMapper {

    int updateMeetingCheck(WpskPinatMetgCheckDvo reqDvo);

    String selectPrtnrOgTpCd(WpskPinatMetgCheckDvo dvo);
}
