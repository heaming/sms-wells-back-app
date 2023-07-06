package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.SearchAllRes;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.SearchMainReq;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.SearchFltrSubMatRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.SearchPsReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.SearchPsRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncCompanyInstallationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncCompanyInstallationService {

    private final WsncCompanyInstallationMapper mapper;

    /**
     * 전체 조회
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     * @programId : K-W-SV-U-0270M01
     */
    public PagingResult<SearchAllRes> getCompanyInstallationAll(
        SearchMainReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyInstallationAll(dto, pageInfo);
    }

    /**
     * 엑셀 다운로드
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     * @programId : K-W-SV-U-0270M01
     */
    public List<SearchAllRes> getCompanyInstallationAll(
        SearchMainReq dto
    ) {
        return mapper.selectCompanyInstallationAll(dto);
    }

    /**
     * 현황 조회
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     * @programId : K-W-SV-U-0270M04
     */
    public PagingResult<SearchPsRes> getCompanyInstallationPs(
        SearchPsReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyInstallationPs(dto, pageInfo);
    }

    /**
     * 현황 엑셀 다운로드
     *
     * @param dto : 조회파라메터
     * @return 조회결과
     * @programId : K-W-SV-U-0270M04
     */
    public List<SearchPsRes> getCompanyInstallationPs(
        SearchPsReq dto
    ) {
        return mapper.selectCompanyInstallationPs(dto);
    }

    /**
     * 필터/부자재 조회
     *
     * @programId : K-W-SV-U-0270M03 / K-W-SV-U-0270M02
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<SearchFltrSubMatRes> getCompanyInstallationFltrOrSubMat(
        SearchMainReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyInstallationFltrOrSubMat(dto, pageInfo);
    }

    /**
     * 필터/부자재 조회
     *
     * @programId : K-W-SV-U-0270M03 / K-W-SV-U-0270M02
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<SearchFltrSubMatRes> getCompanyInstallationFltrOrSubMat(
        SearchMainReq dto
    ) {
        return mapper.selectCompanyInstallationFltrOrSubMat(dto);
    }
}
