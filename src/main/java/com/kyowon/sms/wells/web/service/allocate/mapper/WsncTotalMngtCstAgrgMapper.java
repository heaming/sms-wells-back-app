package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTotalMngtCstAgrgDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTotalMngtCstAgrgDvo;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface WsncTotalMngtCstAgrgMapper {

    /**
     * 총 관리고객 집계
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    List<WsncTotalMngtCstAgrgDvo> selectTotalMngtCstAgrgs(WsncTotalMngtCstAgrgDto.SearchReq dto);

}
