
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.standard.converter.WpdySeedlingPriceMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdySeedlingPriceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdySeedlingPriceBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdySeedlingPriceMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
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
            base = getSeedlingPrice(base, true);
            if (StringUtil.isEmpty(base.getRglrSppSdingPrcId())) {
                cnt += mapper.insertSeedlingPriceBase(base);
            } else {
                cnt += mapper.updateSeedlingPriceBase(base);
            }
            mapper.insertSeedlingPriceHistory(base);
            mapper.updateSeedlingPricePrevHistory(base);
        }
        return cnt;
    }

    public int removeSeedlingPrices(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos) {
        int cnt = 0;
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dtos);
        for (WpdySeedlingPriceBaseDvo base : bases) {
            base = getSeedlingPrice(base, false);
            cnt += mapper.deleteSeedlingPriceBase(base);
            mapper.insertSeedlingPriceHistory(base);
            mapper.updateSeedlingPricePrevHistory(base);
        }
        return cnt;
    }

    private WpdySeedlingPriceBaseDvo getSeedlingPrice(WpdySeedlingPriceBaseDvo base, boolean isSave) {
        String histStrtDtm = DateUtil.getDate(new Date());
        String histEndDtm = PdProductConst.END_DATE_STR;
        base.setHistStrtDtm(histStrtDtm);
        base.setHistEndDtm(histEndDtm);
        if (isSave) {
            if (StringUtil.isEmpty(base.getRglrSppSdingPrcId())) {
                base.setPdPrcTcnt((long)1);
            } else {
                if (base.getPdPrcTcnt() == null) {
                    base.setPdPrcTcnt((long)2);
                } else {
                    base.setPdPrcTcnt(base.getPdPrcTcnt() + 1);
                }
            }
        }
        return base;
    }

    public String checkDuplication(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos) {
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dtos);
        List<String> idList = bases.stream()
            .map(base -> base.getRglrSppSdingPrcId())
            .filter(value -> StringUtil.isNotBlank(value))
            .collect(Collectors.toList());
        String duplicationKey = null;
        for (WpdySeedlingPriceBaseDvo base : bases) {
            duplicationKey = mapper.selectSeedlingPriceDuplication(base, idList);
            if (StringUtil.isNotBlank(duplicationKey)) {
                break;
            }
        }
        return duplicationKey;
    }
}
