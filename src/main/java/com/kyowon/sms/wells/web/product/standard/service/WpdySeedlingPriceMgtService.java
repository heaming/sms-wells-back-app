
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.standard.converter.WpdySeedlingPriceMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdySeedlingPriceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdySeedlingPriceBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdySeedlingPriceMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdySeedlingPriceMgtService {

    private final WpdySeedlingPriceMgtConverter converter;
    private final WpdySeedlingPriceMgtMapper mapper;

    public List<WpdySeedlingPriceMgtDto.SearchRes> getSeedlingPrices(WpdySeedlingPriceMgtDto.SearchReq dto) {
        return mapper.selectSeedlingPrices(dto);
    }

    public PagingResult<WpdySeedlingPriceMgtDto.SearchRes> getSeedlingPricePages(WpdySeedlingPriceMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSeedlingPricePages(dto, pageInfo);
    }

    public int saveSeedlingPrices(WpdySeedlingPriceMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dto.bases());
        for (WpdySeedlingPriceBaseDvo base : bases) {
            if (StringUtil.isEmpty(base.getRglrSppSdingPrcId())) {
                cnt += mapper.insertSeedlingPriceBase(base);
            } else {
                cnt += mapper.updateSeedlingPriceBase(base);
            }

        }
        return cnt;
    }

    public int removeSeedlingPrices(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos) {
        int cnt = 0;
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dtos);
        for (WpdySeedlingPriceBaseDvo base : bases) {
            cnt += mapper.deleteSeedlingPriceBase(base);
        }
        return cnt;
    }

}
