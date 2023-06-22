
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.standard.converter.WpdySalesTypeVariableMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdySalesTypeVariableMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyTypeVariableBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdySalesTypeVariableMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdySalesTypeVariableMgtService {

    private final WpdySalesTypeVariableMgtConverter converter;
    private final WpdySalesTypeVariableMgtMapper mapper;

    public List<WpdySalesTypeVariableMgtDto.SearchRes> getSalesTypeVariables(WpdySalesTypeVariableMgtDto.SearchReq dto) {
        return mapper.selectSalesTypeVariables(dto);
    }

    public PagingResult<WpdySalesTypeVariableMgtDto.SearchRes> getSalesTypeVariablePages(WpdySalesTypeVariableMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSalesTypeVariablePages(dto, pageInfo);
    }

    public int saveSalesTypeVariables(WpdySalesTypeVariableMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyTypeVariableBaseDvo> bases = converter.mapAllTypeVarBaseDtoToTypeVarBaseDvo(dto.bases());
        for (WpdyTypeVariableBaseDvo base : bases) {
            cnt += mapper.mergeSalesTypeVariableBase(base);
        }
        return cnt;
    }

    public int removeSalesTypeVariables(List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos) {
        int cnt = 0;
        List<WpdyTypeVariableBaseDvo> bases = converter.mapAllTypeVarBaseDtoToTypeVarBaseDvo(dtos);
        for (WpdyTypeVariableBaseDvo base : bases) {
            cnt += mapper.deleteSalesTypeVariableBase(base);
        }
        return cnt;
    }

    public String checkDuplication(List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos) {
        List<WpdyTypeVariableBaseDvo> bases = converter.mapAllTypeVarBaseDtoToTypeVarBaseDvo(dtos);
        String duplicationKey = null;
        for (WpdyTypeVariableBaseDvo base : bases) {
            duplicationKey = mapper.selectSalesTypeVariableDuplication(base);
            if (StringUtil.isNotBlank(duplicationKey)) {
                break;
            }
        }
        return duplicationKey;
    }

}
