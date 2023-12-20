
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.standard.converter.WpdyWellsAllianceMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyWellsAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdyWellsAllianceMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 헬스 제휴 관리 서비스
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Service
@RequiredArgsConstructor
public class WpdyWellsAllianceMgtService {

    private final WpdyWellsAllianceMgtConverter converter;
    private final WpdyWellsAllianceMgtMapper mapper;

    /**
     * 헬스 제휴 관리 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    public List<WpdyWellsAllianceMgtDto.SearchRes> getWellsAlliances(WpdyWellsAllianceMgtDto.SearchReq dto) {
        return mapper.selectWellsAlliances(dto);
    }

    /**
     * 헬스 제휴 관리 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    public PagingResult<WpdyWellsAllianceMgtDto.SearchRes> getWellsAlliancePages(WpdyWellsAllianceMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectWellsAlliancePages(dto, pageInfo);
    }

    /**
     * 헬스 제휴 관리 수정
     * @param dto 수정내용 정보
     * @return 수정건수
     * @throws Exception 미처리 시 Exception 처리
     */
    public int saveWellsAlliances(WpdyWellsAllianceMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dto.bases());
        for (WpdyAllianceBaseDvo base : bases) {
            if (StringUtil.isEmpty(base.getPdAlncmpBaseId())) {
                // 웰스 제휴 관리 시퀀스 저장
                base.setPdAlncmpBaseId(mapper.selectWellsAllianceId());
            }
            cnt += mapper.mergeWellsAllianceBase(base);
        }
        return cnt;
    }

    /**
     * 헬스 제휴 관리 삭제
     * @param dtos 삭제 정보 목록
     * @return 삭제건수
     */
    public int removeWellsAlliances(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos) {
        int cnt = 0;
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        for (WpdyAllianceBaseDvo base : bases) {
            cnt += mapper.deleteWellsAllianceBase(base);
        }
        return cnt;
    }

    /**
     * 헬스 제휴 관리 중복체크
     * @param dtos 저장대상 정보 목록
     * @return 중복정보(중복키)
     */
    public String checkDuplication(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos) {
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        // 저장 ID 목록
        List<String> idList = bases.stream()
            .map(base -> base.getPdAlncmpBaseId())
            .filter(value -> StringUtil.isNotBlank(value))
            .collect(Collectors.toList());
        String duplicationKey = null;
        for (WpdyAllianceBaseDvo base : bases) {
            duplicationKey = mapper.selectWellsAllianceDuplication(base, idList);
            if (StringUtil.isNotBlank(duplicationKey)) {
                // 중복 검사 첫번째 중복만 반환, 이후 중단
                break;
            }
        }
        return duplicationKey;
    }

    /**
     *  헬스 제휴 관리 저장 검증
     * @param dtos 저장대상 정보 목록
     * @return 검증오류정보(중복키) - 값이 Null일시 정상
     */
    public String checkValidation(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos) {
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        String validationIssueKey = null;
        String validResult = null;
        for (WpdyAllianceBaseDvo base : bases) {
            validResult = mapper.selectWellsAllianceValidation(base);
            if (StringUtil.isBlank(validResult)) {
                // 중복 검사 첫번째 중복 정보만 반환, 이후 중단
                validationIssueKey = base.getPdCd() + PdProductConst.COMMA + StringUtil.nonNull(base.getSvPdCd()) + PdProductConst.COMMA + StringUtil.nonNull(base.getStplPrdCd());
                break;
            }
        }
        return validationIssueKey;
    }

}
