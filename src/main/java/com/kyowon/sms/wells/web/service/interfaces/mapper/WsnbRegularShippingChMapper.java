package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChDto.*;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * W-SV-I-0016 정기배송 변경
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-04-13
 */
@Mapper
public interface WsnbRegularShippingChMapper {

    int insertSppChRcpHist(SaveSppChRcpHistReq req);

    int deleteSppChRcpDtl(SaveSppChRcpDtlReq req);

    int deleteSppChRcpBas(SaveSppChRcpBasReq req);

    int selectSppChRcpBasCnt(SearchSppChRcpBasReq req);

    int selectMaxSppChSn(String cntrNo);

    int insertSppChRcpBas(SaveSppChRcpBasReq req);
    int insertSppChRcpDtl(SaveSppChRcpDtlReq req);

    int updateSppChRcpBas(SaveSppChRcpBasReq req);

}
