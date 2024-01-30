package com.kyowon.sms.wells.web.competence.evaluate.converter;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionBaseMgtDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvDetailDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdTrgBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExcellentDivisionDeadlineDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdPdBaseDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WpsdExcellentDivisionBaseMgtConverter {
    WpsdPdBaseDvo pdMapToPdBaseDvo(PdSaveReq req);

    WpsdElvBaseDvo elvMapToElvBaseDvo(EvlSaveReq req);

    WpsdElvBaseDvo elvMapToElvBaseDvo(EvlDeleteReq req);

    WpsdElvDetailDvo elvMapToDetailDvo(EvlDetailSaveReq req);

    WpsdElvDetailDvo elvMapToDetailDvo(EvlDetailDeleteReq req);

    WpsdTrgBaseDvo mapToTrgBaseDvo(TrgSaveReq req);
    WpsdExcellentDivisionDeadlineDvo deadlineMapToDvo(DeadlineSaveReq req);
}
