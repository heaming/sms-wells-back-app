package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.SearchAllRes;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.SearchMainReq;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.SearchFltrSubMatRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.SearchPsReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto.SearchPsRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncCompanyIstStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncCompanyIstStateService {

    private final WsncCompanyIstStateMapper mapper;

    /**
     * 전체 조회
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<SearchAllRes> getCompanyIstStateAll(
        SearchMainReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyIstStateAll(dto, pageInfo);
    }

    /**
     * 엑셀 다운로드
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<SearchAllRes> getCompanyIstStateAll(
        SearchMainReq dto
    ) {
        return mapper.selectCompanyIstStateAll(dto);
    }

    /**
     * 현황 조회
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<SearchPsRes> getCompanyIstStatePs(
        SearchPsReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyIstStatePs(dto, pageInfo);
    }

    /**
     * 현황 엑셀 다운로드
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<SearchPsRes> getCompanyIstStatePs(
        SearchPsReq dto
    ) {
        return mapper.selectCompanyIstStatePs(dto);
    }

    /**
     * 필터 조회
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<SearchFltrSubMatRes> getCompanyIstStateFltr(
        SearchMainReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyIstStateFltr(dto, pageInfo);
    }

    /**
     * 필터 엑셀 다운로드
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<SearchFltrSubMatRes> getCompanyIstStateFltr(
        SearchMainReq dto
    ) {
        return mapper.selectCompanyIstStateFltr(dto);
    }

    /**
     * 부자재 조회
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<SearchFltrSubMatRes> getCompanyIstStateSubMat(
        SearchMainReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyIstStateSubMat(dto, pageInfo);
    }

    /**
     * 부자재 엑셀 다운로드
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public List<SearchFltrSubMatRes> getCompanyIstStateSubMat(
        SearchMainReq dto
    ) {
        return mapper.selectCompanyIstStateSubMat(dto);
    }
}
