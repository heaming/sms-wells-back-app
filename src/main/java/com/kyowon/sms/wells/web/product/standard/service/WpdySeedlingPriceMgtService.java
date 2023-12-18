
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

/**
 * <pre>
 * 모종제품가격 관리 서비스
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Service
@RequiredArgsConstructor
public class WpdySeedlingPriceMgtService {

    private final WpdySeedlingPriceMgtConverter converter;
    private final WpdySeedlingPriceMgtMapper mapper;

    /**
     * 모종제품가격 관리 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdySeedlingPriceMgtDto.SearchRes> getSeedlingPrices(WpdySeedlingPriceMgtDto.SearchReq dto) {
        return mapper.selectSeedlingPrices(dto);
    }

    /**
     * 모종제품가격 관리 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    public PagingResult<WpdySeedlingPriceMgtDto.SearchRes> getSeedlingPricePages(WpdySeedlingPriceMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSeedlingPricePages(dto, pageInfo);
    }

    /**
     * 모종제품가격 관리 수정
     * @param dto 수정내용 정보
     * @return 수정건수
     * @throws Exception 미처리 시 Exception 처리
     */
    public int saveSeedlingPrices(WpdySeedlingPriceMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dto.bases());
        for (WpdySeedlingPriceBaseDvo base : bases) {
            // 모종제품가격 정보 저장 - 저장전 기본정보
            setSeedlingPrice(base, true);
            if (StringUtil.isEmpty(base.getRglrSppSdingPrcId())) {
                // 최초 저장
                cnt += mapper.insertSeedlingPriceBase(base);
            } else {
                // 수정
                cnt += mapper.updateSeedlingPriceBase(base);
            }
            mapper.insertSeedlingPriceHistory(base);
            mapper.updateSeedlingPricePrevHistory(base);
        }
        return cnt;
    }

    /**
     * 모종제품가격 관리 삭제
     * @param dtos 삭제 정보 목록
     * @return 삭제건수
     */
    public int removeSeedlingPrices(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos) {
        int cnt = 0;
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dtos);
        for (WpdySeedlingPriceBaseDvo base : bases) {
            setSeedlingPrice(base, false);
            cnt += mapper.deleteSeedlingPriceBase(base);
            mapper.insertSeedlingPriceHistory(base);
            mapper.updateSeedlingPricePrevHistory(base);
        }
        return cnt;
    }

    /**
     * 모종제품가격 정보 저장 - 저장전 기본정보
     * @param base 모종제품 기본정보
     * @param isSave 수정(true), 생성(false)
     */
    private void setSeedlingPrice(WpdySeedlingPriceBaseDvo base, boolean isSave) {
        String histStrtDtm = DateUtil.getDate(new Date());
        String histEndDtm = PdProductConst.END_DATE_STR;
        base.setHistStrtDtm(histStrtDtm);
        base.setHistEndDtm(histEndDtm);
        if (isSave) {
            if (StringUtil.isEmpty(base.getRglrSppSdingPrcId())) {
                // 생성 초기값 = 1
                base.setPdPrcTcnt((long)1);
            } else {
                if (base.getPdPrcTcnt() == null) {
                    // 수정, 값이 없으면 2
                    base.setPdPrcTcnt((long)2);
                } else {
                    // 기존 차수 증가
                    base.setPdPrcTcnt(base.getPdPrcTcnt() + 1);
                }
            }
        }
    }

    /**
     * 모종제품 중복체크
     * @param dtos 저장대상 정보 목록
     * @return 중복정보(중복키)
     */
    public String checkDuplication(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos) {
        List<WpdySeedlingPriceBaseDvo> bases = converter.mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(dtos);
        // 저장 ID 목록
        List<String> idList = bases.stream()
            .map(base -> base.getRglrSppSdingPrcId())
            .filter(value -> StringUtil.isNotBlank(value))
            .collect(Collectors.toList());
        String duplicationKey = null;
        for (WpdySeedlingPriceBaseDvo base : bases) {
            duplicationKey = mapper.selectSeedlingPriceDuplication(base, idList);
            if (StringUtil.isNotBlank(duplicationKey)) {
                // 중복 검사 첫번째 중복만 반환, 이후 중단
                break;
            }
        }
        return duplicationKey;
    }
}
