package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskRgstDvo;

/**
 *
 * <pre>
 * W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.02
 */
@Mapper(componentModel = "spring")
public interface WsnaOutOfStorageAskRgstConverter {

    List<WsnaOutOfStorageAskRgstDto.SearchRes> mapDvoListToSearchResDtoList(List<WsnaOutOfStorageAskRgstDvo> dvo);

}
