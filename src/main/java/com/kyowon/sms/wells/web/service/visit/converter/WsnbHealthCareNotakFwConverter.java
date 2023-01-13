package com.kyowon.sms.wells.web.service.visit.converter;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbHealthCareNotakFwDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbHealthCareNotakFwDvo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WsnbHealthCareNotakFwConverter {
    List<SearchRes> mapAllListDvoToListRes(List<WsnbHealthCareNotakFwDvo> dvos);

    SearchRes mapDvoToLRes(WsnbHealthCareNotakFwDvo dvo);

    FindRes mapDvoToFindRes(WsnbHealthCareNotakFwDvo dvo);

    CreateReq mapAllSaveReqToCreateReq(SaveReq req);

    EditReq mapAllSaveReqToEditReq(SaveReq req);

    RemoveReq mapAllSaveReqToRemoveReq(SaveReq req);
}
