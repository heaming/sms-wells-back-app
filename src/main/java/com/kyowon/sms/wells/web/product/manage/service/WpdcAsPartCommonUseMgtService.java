package com.kyowon.sms.wells.web.product.manage.service;

import static com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartCommonUseMgtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.manage.mapper.WpdcAsPartCommonUseMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcAsPartCommonUseMgtService {

    private final WpdcAsPartCommonUseMgtMapper mapper;

    /**
     * AS부품 목록 조회
     * @param dto 조회조건
     * @return AS부품 목록
     */
    public List<SearchPartRes> getAsParts(SearchPartReq dto) {
        return mapper.selectAsParts(dto);
    }

    /**
     * AS부품 관련 제품 목록 조회
     * @param partPdCd 부품코드
     * @return AS부품 관련 제품 목록
     */
    public List<SearchProductRes> getProductsByPart(String partPdCd) {
        return mapper.selectProductsByPart(partPdCd);
    }

    /**
     * 제품 목록 조회
     * @param dto
     * @return 제품 목록
     */
    public List<SearchProductRes> selectProducts(SearchPartReq dto) {
        return mapper.selectProducts(dto);
    }

    /**
     * 제품 관련 AS부품 목록 조회
     * @param pdCd 제품코드
     * @return 제품 관련 AS부품 목록
     */
    public List<SearchPartRes> selectAsPartsByProduct(String pdCd) {
        return mapper.selectAsPartsByProduct(pdCd);
    }
}
