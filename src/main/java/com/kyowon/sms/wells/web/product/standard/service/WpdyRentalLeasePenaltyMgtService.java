
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

/**
 * <pre>
 * 렌탈/리스 위약금 관리 서비스
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Service
@RequiredArgsConstructor
public class WpdyRentalLeasePenaltyMgtService {

    private final WpdyRentalLeasePenaltyMgtConverter converter;
    private final WpdyRentalLeasePenaltyMgtMapper mapper;

    /**
     * 렌탈/리스 위약금 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdyRentalLeasePenaltyMgtDto.SearchRes> getRentalLeasePenalties(WpdyRentalLeasePenaltyMgtDto.SearchReq dto) {
        return mapper.selectRentalLeasePenalties(dto);
    }

    /**
     * 렌탈/리스 위약금 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    public PagingResult<WpdyRentalLeasePenaltyMgtDto.SearchRes> getRentalLeasePenaltyPages(WpdyRentalLeasePenaltyMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRentalLeasePenaltyPages(dto, pageInfo);
    }

    /**
     * 렌탈/리스 위약금 수정
     * @param dto 수정내용 정보
     * @return 수정건수
     * @throws Exception 미처리 시 Exception 처리
     */
    public int saveRentalLeasePenalties(WpdyRentalLeasePenaltyMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyCancelChargeBaseDvo> bases = converter.mapAllCancelChargeBaseDtoToCancelChargeBaseDvo(dto.bases());
        String startDtm = DateUtil.getDate(new Date());
        for (WpdyCancelChargeBaseDvo base : bases) {
            if (StringUtil.isEmpty(base.getCcamId())) {
                // 렌탈리스 테이블 시퀀스 키 입력
                base.setCcamId(mapper.selectRentalLeasePenaltyId());
                base.setHistStrtDtm(startDtm);
                base.setHistEndDtm(PdProductConst.END_DATE_STR);
            }
            cnt += mapper.mergeRentalLeasePenaltyBase(base);
        }
        return cnt;
    }

    /**
     * 렌탈/리스 위약금 삭제
     * @param dtos 삭제 정보 목록
     * @return 삭제건수
     */
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

    /**
     * 렌탈/리스 위약금 중복체크
     * @param dtos 저장대상 정보 목록
     * @return 중복정보(중복키)
     */
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
                // 중복 검사 첫번째 중복만 반환, 이후 중단
                break;
            }
        }
        return duplicationKey;
    }

}
