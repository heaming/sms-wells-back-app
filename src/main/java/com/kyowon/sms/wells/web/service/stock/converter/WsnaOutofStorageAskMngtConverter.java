package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtSearchDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

/**
 * <pre>
 * W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.04.21
 */
@Mapper(componentModel = "spring", imports = {org.apache.commons.lang3.StringUtils.class})
public interface WsnaOutofStorageAskMngtConverter {

    WsnaOutOfStorageAskMngtDvo mapSaveReqToOutOfStorageAskMngtDvo(SaveReq dto);

    @Mapping(target = "svCnrLkTnoEncr", expression = "java(StringUtils.defaultString(dvo.getLocaraTno()) + StringUtils.defaultString(dvo.getExnoEncr()) + StringUtils.defaultString(dvo.getIdvTno()))")
    List<WsnaLogisticsOutStorageAskReqDvo> mapCreateOutOfStorageAsksDvo(List<WsnaOutOfStorageAskMngtDvo> logisticsDvo);

    WsnaOutOfStorageAskMngtDvo mapDeleteReqToOutOfStorageAskMngtDvo(RemoveReq dto);

    List<WsnaLogisticsOutStorageAskReqDvo> mapWsnaLogisticsOutStorageAskReqDvoToRemoveOutOfStorageAsks(
        List<WsnaOutOfStorageAskMngtDvo> logisticsRemoveDvo
    );

    WsnaOutOfStorageAskMngtSearchDvo mapAllSearchReqToOutOfStorageAskMngtDvo(SearchReq dto);

}
