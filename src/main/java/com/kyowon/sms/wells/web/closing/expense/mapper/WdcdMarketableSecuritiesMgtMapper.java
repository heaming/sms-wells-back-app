package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.AdjustObjectReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.AdjustObjectRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.WithholdingTaxAdjustReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.WithholdingTaxAdjustRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritiesDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdMarketableSecuritiesMgtMapper {

    List<AdjustObjectRes> selectAdjustObject(AdjustObjectReq req);

    List<WithholdingTaxAdjustRes> selectWithholdingTaxAdjust(WithholdingTaxAdjustReq req);

    int editWithholdingTaxAdjust(WdcdMarketableSecuritiesDvo req);
}
