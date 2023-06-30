
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.standard.converter.WpdyRentalLeasePenaltyMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRentalLeasePenaltyMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyCancelChargeBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdyRentalLeasePenaltyMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdyRentalLeasePenaltyMgtService {

    private final WpdyRentalLeasePenaltyMgtConverter converter;
    private final WpdyRentalLeasePenaltyMgtMapper mapper;

    public List<WpdyRentalLeasePenaltyMgtDto.SearchRes> getRentalLeasePenalties(WpdyRentalLeasePenaltyMgtDto.SearchReq dto) {
        return mapper.selectRentalLeasePenalties(dto);
    }

    public PagingResult<WpdyRentalLeasePenaltyMgtDto.SearchRes> getRentalLeasePenaltyPages(WpdyRentalLeasePenaltyMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRentalLeasePenaltyPages(dto, pageInfo);
    }

    public int saveRentalLeasePenalties(WpdyRentalLeasePenaltyMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyCancelChargeBaseDvo> bases = converter.mapAllCancelChargeBaseDtoToCancelChargeBaseDvo(dto.bases());
        String startDtm = DateUtil.getDate(new Date());
        for (WpdyCancelChargeBaseDvo base : bases) {
            if (StringUtil.isEmpty(base.getCcamId())) {
                base.setCcamId(mapper.selectRentalLeasePenaltyId());
                base.setHistStrtDtm(startDtm);
                base.setHistEndDtm(PdProductConst.END_DATE_STR);
            }
            cnt += mapper.mergeRentalLeasePenaltyBase(base);
        }
        return cnt;
    }

    public int removeRentalLeasePenalties(List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> dtos) {
        int cnt = 0;
        String endDtm = DateUtil.getDate(new Date());
        List<WpdyCancelChargeBaseDvo> bases = converter.mapAllCancelChargeBaseDtoToCancelChargeBaseDvo(dtos);
        for (WpdyCancelChargeBaseDvo base : bases) {
            base.setHistEndDtm(endDtm);
            cnt += mapper.deleteRentalLeasePenaltyBase(base);
        }
        return cnt;
    }

    public String checkDuplication(List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> dtos) {
        List<WpdyCancelChargeBaseDvo> bases = converter.mapAllCancelChargeBaseDtoToCancelChargeBaseDvo(dtos);
        List<String> idList = bases.stream()
            .map(base -> base.getCcamId())
            .filter(value -> StringUtil.isNotBlank(value))
            .collect(Collectors.toList());
        String duplicationKey = null;
        for (WpdyCancelChargeBaseDvo base : bases) {
            duplicationKey = mapper.selectRentalLeasePenaltyDuplication(base, idList);
            if (StringUtil.isNotBlank(duplicationKey)) {
                break;
            }
        }
        return duplicationKey;
    }

}
