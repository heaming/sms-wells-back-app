package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTotalMngtCstAgrgConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTotalMngtCstAgrgDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTotalMngtCstAgrgMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * <pre>
 * W-SV-U-0228M01 총 관리고객 집계
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncTotalMngtCstAgrgService {

    private final WsncTotalMngtCstAgrgMapper mapper;
    private final WsncTotalMngtCstAgrgConverter converter;

    /**
     * 총 관리고객 집계
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<WsncTotalMngtCstAgrgDto.SearchRes> selectTotalMngtCstAgrgs(
        WsncTotalMngtCstAgrgDto.SearchReq dto
    ) {
        return converter.mapAllTotalMngtCstAgrgsDvoToRes(mapper.selectTotalMngtCstAgrgs(dto));
    }
}
