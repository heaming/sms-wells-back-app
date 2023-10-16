package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallLocationMgtConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.FindProductRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallLocationDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallLocationMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0035M01 설치 위치 상세 관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.01.02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbInstallLocationMgtService {

    private final WsnbInstallLocationMgtMapper mapper;
    private final WsnbInstallLocationMgtConverter converter;

    /**
     * 설치 위치 상세 관리 - 조회(페이징)
     */
    public PagingResult<SearchRes> getInstallLocationPages(
        SearchReq dto, PageInfo pageInfo
    ) {

        PagingResult<SearchRes> pagingResult = converter.mapWsnbInstallLocationDvoToSearchRes(
            mapper.selectInstallLocationPages(dto, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 설치 위치 상세 관리 - 조회(엑셀다운로드)
     */
    public List<SearchRes> getInstallLocationPagesExcelDownload(SearchReq dto) {

        return converter.mapWsnbInstallLocationDvoToSearchRes(mapper.selectInstallLocationPages(dto));
    }

    /**
     * 설치 위치 상세 관리 - 저장
     */
    public int createInstallLocations(List<CreateReq> dtos) {
        int processCount = 0;
        for (CreateReq dto : dtos) {
            WsnbInstallLocationDvo dvo = converter.mapCreateReqToWsnbInstallLocationDvo(dto);

            int result = mapper.insertInstallLocation(dvo);
            processCount += result;
        }
        return processCount;
    }

    /**
     * 설치 위치 상세 관리 - 저장 프로시저
     */
    public int createInitializeInstallLocations(List<CreateReq> dtos) {
        int processCount = 0;

        for (CreateReq dto : dtos) {
            int result = 0;
            WsnbInstallLocationDvo dvo = converter.mapCreateReqToWsnbInstallLocationDvo(dto);
            String dtlSn = mapper.selectSerialNumberByPk(dto.cntrNo());

            if (Integer.parseInt(dtlSn) >= 001) { // 계약제품설치위치 상세일련번호 1보다 크거나 같으면,
                dvo.setDtlSn(dtlSn);

                int cnLength = mapper.selectInstallLocationContentLength(dvo);
                if (cnLength > 0) { // 설치위치상세내용이 존재하면,
                    result += mapper.insertInitializeInstallLocation(dvo);
                }
            }
            processCount += result;
        }
        return processCount;
    }

    /**
     * 검색조건 용 상품내역 조회
     */
    public List<FindProductRes> getProducts() {
        return mapper.selectProducts();
    }

}
