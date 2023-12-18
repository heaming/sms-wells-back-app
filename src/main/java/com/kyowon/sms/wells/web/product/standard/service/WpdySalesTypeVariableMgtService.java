
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

/**
 * <pre>
 * 판매유형별 변수 관리 서비스
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Service
@RequiredArgsConstructor
public class WpdySalesTypeVariableMgtService {

    private final WpdySalesTypeVariableMgtConverter converter;
    private final WpdySalesTypeVariableMgtMapper mapper;

    /**
     * 판매유형별 변수 관리 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdySalesTypeVariableMgtDto.SearchRes> getSalesTypeVariables(WpdySalesTypeVariableMgtDto.SearchReq dto) {
        return mapper.selectSalesTypeVariables(dto);
    }

    /**
     * 판매유형별 변수 관리 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    public PagingResult<WpdySalesTypeVariableMgtDto.SearchRes> getSalesTypeVariablePages(WpdySalesTypeVariableMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSalesTypeVariablePages(dto, pageInfo);
    }

    /**
     * 판매유형별 변수 관리 수정
     * @param dto 수정내용 정보
     * @return 수정건수
     * @throws Exception 미처리 시 Exception 처리
     */
    public int saveSalesTypeVariables(WpdySalesTypeVariableMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyTypeVariableBaseDvo> bases = converter.mapAllTypeVarBaseDtoToTypeVarBaseDvo(dto.bases());
        for (WpdyTypeVariableBaseDvo base : bases) {
            cnt += mapper.mergeSalesTypeVariableBase(base);
        }
        return cnt;
    }

    /**
     * 판매유형별 변수 관리 삭제
     * @param dtos 삭제 정보 목록
     * @return 삭제건수
     */
    public int removeSalesTypeVariables(List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos) {
        int cnt = 0;
        List<WpdyTypeVariableBaseDvo> bases = converter.mapAllTypeVarBaseDtoToTypeVarBaseDvo(dtos);
        for (WpdyTypeVariableBaseDvo base : bases) {
            cnt += mapper.deleteSalesTypeVariableBase(base);
        }
        return cnt;
    }

    /**
     * 판매유형별 변수 관리 중복체크
     * @param dtos 저장대상 정보 목록
     * @return 중복정보(중복키)
     */
    public String checkDuplication(List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos) {
        List<WpdyTypeVariableBaseDvo> bases = converter.mapAllTypeVarBaseDtoToTypeVarBaseDvo(dtos);
        String duplicationKey = null;
        for (WpdyTypeVariableBaseDvo base : bases) {
            duplicationKey = mapper.selectSalesTypeVariableDuplication(base);
            if (StringUtil.isNotBlank(duplicationKey)) {
                // 중복 검사 첫번째 중복만 반환, 이후 중단
                break;
            }
        }
        return duplicationKey;
    }

}
