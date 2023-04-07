package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesExcdMgtDto.AdjustObjectReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesExcdMgtDto.AdjustObjectRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesExcdMgtDto.WithholdingTaxAdjustReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesExcdMgtDto.WithholdingTaxAdjustRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesExcdDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdMarketableSecuritiesExcdMgtMapper {

    List<AdjustObjectRes> selectAdjustObject(AdjustObjectReq req);

    List<WithholdingTaxAdjustRes> selectWithholdingTaxAdjust(WithholdingTaxAdjustReq req);

    int editWithholdingTaxAdjust(WdcdMarketableSecuritiesExcdDvo req);
}
