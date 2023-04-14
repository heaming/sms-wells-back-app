package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.*;
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
public interface WsnbRegularShippingChangeMapper {

    int insertRegularShippingChangeHist(SaveRegularShippingChangeHistReq req);

    int deleteRegularShippingChangeDtl(SaveRegularShippingChangeDtlReq req);

    int deleteRegularShippingChangeBase(SaveRegularShippingChangeBaseReq req);

    int selectRegularShippingChangeCount(SearchRegularShippingChangeBaseReq req);

    int selectRegularShippingChangeMaxSn(String cntrNo);

    int insertRegularShippingChangeBase(SaveRegularShippingChangeBaseReq req);
    int insertRegularShippingChangeDtl(SaveRegularShippingChangeDtlReq req);

    int updateRegularShippingChangeBase(SaveRegularShippingChangeBaseReq req);

}
