package com.kyowon.sms.wells.web.service.allocate.converter;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncIntergratedQrProcsListSearchDto.*;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncIntergratedQrProcsListSearchDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0239M01 통합QR 처리현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.25
 */
@Mapper(componentModel = "spring")
public interface WsncIntergratedQrProcsListSearchConverter {
    List<SearchByOgRes> mapSearchByOgResDvo(
        List<WsncIntergratedQrProcsListSearchDvo> dvos
    );

    List<SearchOgDetailRes> mapSearchOgDetailResDvo(
        List<WsncIntergratedQrProcsListSearchDvo> dvos
    );

    List<SearchAggrRes> mapSearchAggrResDvo(
        List<WsncIntergratedQrProcsListSearchDvo> dvos
    );
}
