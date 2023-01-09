package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallLocationMgtConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.CreateReq;
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
 * @author yeonghwa.cheon 천영화
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
     * @param dto : { svcCd : 관리구분코드 , ogId : 서비스센터ID, engId : 엔지니어ID, rgsnYn : 퇴사여부, istDtTo : 설치일자 To,
     *            istDtFrom :설치일자 From, hgrPdGrop : 상위상품그룹코드, lorPdGrop : 하위상품그룹코드, cstDvCd : 고객구분코드,
     *            cstNm : 고객명, cstNo : 고객번호 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getInstallLocationPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectInstallLocationPages(dto, pageInfo);
    }

    /**
     * 설치 위치 상세 관리 - 조회(엑셀다운로드)
     * @param dto : { svcCd : 관리구분코드 , ogId : 서비스센터ID, engId : 엔지니어ID, rgsnYn : 퇴사여부, istDtTo : 설치일자 To,
     *            istDtFrom :설치일자 From, hgrPdGrop : 상위상품그룹코드, lorPdGrop : 하위상품그룹코드, cstDvCd : 고객구분코드,
     *            cstNm : 고객명, cstNo : 고객번호 }
     * @return 조회결과
     */
    public List<SearchRes> getInstallLocationPagesExcelDownload(SearchReq dto) {
        return mapper.selectInstallLocationPages(dto);
    }

    /**
     * 설치 위치 상세 관리 - 저장
     * @param dtos : [{ cntrNo : 계약번호, cntrSn : 계약일련번호, istLctDtlCn : 설치위치상세, wkPrtnrNo : 작업파트너번호 }]
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
     * @param dtos : [{ cntrNo : 계약번호, cntrSn : 계약일련번호, istLctDtlCn : 설치위치상세, wkPrtnrNo : 작업파트너번호 }]
     */
    public int createInitializeInstallLocations(List<CreateReq> dtos) {
        int processCount = 0;

        for (CreateReq dto : dtos) {
            int result = 0;
            WsnbInstallLocationDvo dvo = converter.mapCreateReqToWsnbInstallLocationDvo(dto);
            String dtlSn = mapper.selectSerialNumberByPk(dto.cntrNo());

            if (Integer.parseInt(dtlSn) >= 001) {
                dvo.setDtlSn(dtlSn);

                int cnLength = mapper.selectInstallLocationContentLength(dvo);
                if (cnLength > 0) {
                    result += mapper.insertInitializeInstallLocation(dvo);
                }
            }
            processCount += result;
        }
        return processCount;
    }

}
