
package com.kyowon.sms.wells.web.product.manage.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.manage.converter.WpdcRoutineBsWorkMgtConverter;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcLifeCustomFilterBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkDetailDvo;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcRoutineBsWorkMgtMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 서비스 상품 - B/S 투입 관리 서비스 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Service
@RequiredArgsConstructor
public class WpdcRoutineBsWorkMgtService {

    private final WpdcRoutineBsWorkMgtConverter converter;
    private final WpdcRoutineBsWorkMgtMapper mapper;

    /**
     * 정기 B/S 투입 방문 작업 기준 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> getRoutineBsWorkStandards(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectRoutineBsWorkBases(dto);
    }

    /**
     * 정기 B/S 투입 방문 작업 기준 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    public PagingResult<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> getRoutineBsWorkStandardPages(WpdcRoutineBsWorkMgtDto.SearchStdBaseReq dto, PageInfo pageInfo) {
        return mapper.selectRoutineBsWorkBasePages(dto, pageInfo);
    }

    /**
     * 정기 B/S 투입 방문 작업 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkDetailRes> getRoutineBsWorkTasks(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectRoutineBsWorkDetails(dto);
    }

    /**
     * 정기 B/S 투입 방문 작업 페이징 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdcRoutineBsWorkMgtDto.SearchLifeCustomFiltersRes> getLifeCustomFilters(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectLifeCustomFilters(dto);
    }

    /**
     * 정기 B/S 투입 방문 작업 기준 수정
     * @param dto 수정내용 정보
     * @return 수정 건수
     * @throws Exception 미처리 시 Exception 처리
     */
    public int saveRoutineBsWorks(WpdcRoutineBsWorkMgtDto.EditReq dto) throws Exception {
        int cnt = 0;
        cnt += mapper.deleteRoutineBsWorkBase(dto.svPdCd(), dto.pdctPdCd());
        cnt += mapper.deleteRoutineBsWorkDetail(dto.svPdCd(), dto.pdctPdCd());
        if (CollectionUtils.isNotEmpty(dto.bases())) {
            // 작업기준 저장
            List<WpdcRoutineBsWorkBaseDvo> bases = converter.mapAllBsWorkBaseDtoToBsWorkBaseDvo(dto.bases());
            for (WpdcRoutineBsWorkBaseDvo base : bases) {
                cnt += mapper.isnertRoutineBsWorkBase(base);
            }
        }
        if (CollectionUtils.isNotEmpty(dto.details())) {
            // 작업상세 저장
            List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dto.details());
            for (WpdcRoutineBsWorkDetailDvo detail : details) {
                cnt += mapper.isnertRoutineBsWorkDetail(detail);
            }
        }
        return cnt;
    }

    /**
     * 정기 B/S 투입 상세 수정
     * @param dto 수정내용 정보
     * @return 수정 건수
     * @throws Exception 미처리 시 Exception 처리
     */
    public int saveRoutineBsWorkDetails(WpdcRoutineBsWorkMgtDto.EditDetailReq dto) throws Exception {
        int cnt = 0;
        List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dto.details());
        for (WpdcRoutineBsWorkDetailDvo detail : details) {
            cnt += mapper.updateRoutineBsWorkDetail(detail);
        }
        return cnt;
    }

    /**
     * 생활맞춤형필터 수정
     * @param dto 수정내용 정보
     * @return 처리결과
     * @throws Exception Exception 미처리 시 Exception 처리
     */
    public int saveLifeFilters(WpdcRoutineBsWorkMgtDto.EditLifeFilterReq dto) throws Exception {
        int cnt = 0;
        List<WpdcLifeCustomFilterBaseDvo> filters = converter.mapAllLifeFltBaseDtoToLifeFltBaseDvo(dto.bases());
        for (WpdcLifeCustomFilterBaseDvo filter : filters) {
            cnt += mapper.mergeLifeCustomFilterBase(filter);
        }
        mapper.updateRoutineBsWorkDetailFilterYn(dto.svPdCd(), dto.pdctPdCd(), dto.partPdCd());
        return cnt;
    }

    /**
     * 정기 B/S 투입 상세 삭제
     * @param dtos B/S 투입 삭제 정보
     * @return 삭제 건수
     */
    public int removeRoutineBsWorkDetails(List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> dtos) {
        int cnt = 0;
        List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dtos);
        for (WpdcRoutineBsWorkDetailDvo detail : details) {
            cnt += mapper.deleteRoutineBsWorkDetail(detail);
        }
        return cnt;
    }

    /**
     * 생활맞춤형필터 삭제
     * @param dtos 삭제 정보
     * @return 삭제 건수
     */
    public int removeLifeFilters(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos) {
        int cnt = 0;
        List<WpdcLifeCustomFilterBaseDvo> filters = converter.mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(dtos);
        String svPdCd = null;
        String pdctPdCd = null;
        String partPdCd = null;
        for (WpdcLifeCustomFilterBaseDvo filter : filters) {
            cnt += mapper.deleteLifeCustomFilterStd(filter);
            if (svPdCd == null) {
                // 서비스 상품 코드 값은 동일하기 때문에 첫번째 ROW에서 추출
                svPdCd = filter.getSvPdCd();
                pdctPdCd = filter.getPdctPdCd();
                partPdCd = filter.getPartPdCd();
            }
        }
        mapper.updateRoutineBsWorkDetailFilterYn(svPdCd, pdctPdCd, partPdCd);
        return cnt;
    }

    /**
     * 정기 B/S 투입 기준 삭제 (by 상품코드)
     * @param pdCd 상품코드
     */
    public void removeRoutineBsWorksPdCd(String pdCd) {
        mapper.deleteRoutineBsWorkBase(pdCd, null);
        mapper.deleteRoutineBsWorkDetail(pdCd, null);
        mapper.deleteLifeCustomFilterStdByPdCd(pdCd);
    }

    /**
     * 생활맞춤형필터 저장 중복 여부 확인
     * @param dtos 저장 대상
     * @return 중복결과
     */
    public String checkLifeFilterDuplication(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos) {
        List<WpdcLifeCustomFilterBaseDvo> bases = converter.mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(dtos);
        String duplicationKey = null;
        for (WpdcLifeCustomFilterBaseDvo base : bases) {
            duplicationKey = mapper.selectLifeFilterDuplication(base);
            if (StringUtil.isNotBlank(duplicationKey)) {
                // 중복 검사 첫번째 중복만 반환, 이후 중단
                break;
            }
        }
        return duplicationKey;
    }
}
