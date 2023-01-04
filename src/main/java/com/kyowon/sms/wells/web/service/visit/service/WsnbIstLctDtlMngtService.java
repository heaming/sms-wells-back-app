package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbIstLctDtlMngtConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.FindReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIstLctDtlDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIstLctDtlMngtMapper;
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
public class WsnbIstLctDtlMngtService {

    private final WsnbIstLctDtlMngtMapper mapper;
    private final WsnbIstLctDtlMngtConverter converter;

    /**
     * 설치 위치 상세 관리 - 조회(페이징)
     * @param dto : { svcCd : 관리구분코드 , ogId : 서비스센터ID, engId : 엔지니어ID, rgsnYn : 퇴사여부, istDtTo : 설치일자 To,
     *            istDtFrom :설치일자 From, hgrPdGrop : 상위상품그룹코드, lorPdGrop : 하위상품그룹코드, cstDvCd : 고객구분코드,
     *            cstNm : 고객명, cstNo : 고객번호 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getIstLocationDetailPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectIstLocationDetailPages(dto, pageInfo);
    }

    /**
     * 설치 위치 상세 관리 - 조회(엑셀다운로드)
     * @param dto : { svcCd : 관리구분코드 , ogId : 서비스센터ID, engId : 엔지니어ID, rgsnYn : 퇴사여부, istDtTo : 설치일자 To,
     *            istDtFrom :설치일자 From, hgrPdGrop : 상위상품그룹코드, lorPdGrop : 하위상품그룹코드, cstDvCd : 고객구분코드,
     *            cstNm : 고객명, cstNo : 고객번호 }
     * @return 조회결과
     */
    public List<SearchRes> getIstLocationDetailExcelDownload(SearchReq dto) {
        return mapper.selectIstLocationDetailPages(dto);
    }

    /**
     * 설치 위치 상세 관리 - 저장
     * @param dtos : [{ cntrNo : 계약번호, cntrSn : 계약일련번호, istLctDtlCn : 설치위치상세, wkPrtnrNo : 작업파트너번호 }]
     */
    public int createIstLocationDetails(List<WsnbIstLctDtlMngtDto.SaveReq> dtos) {
        int processCount = 0;
        for (WsnbIstLctDtlMngtDto.SaveReq dto : dtos) {
            WsnbIstLctDtlDvo dvo = converter.mapSaveReqToWsnbIstLctDtlDvo(dto);

            int result = mapper.insertIstLocationDetail(dvo);
            processCount += result;
        }
        return processCount;
    }

    /**
     * 설치 위치 상세 관리 - 저장 프로시저
     * @param dtos : [{ cntrNo : 계약번호, cntrSn : 계약일련번호, istLctDtlCn : 설치위치상세, wkPrtnrNo : 작업파트너번호 }]
     */
    public int createInitializeIstLocationDetails(List<FindReq> dtos) {
        int processCount = 0;

        for (FindReq dto : dtos) {
            int result = 0;
            WsnbIstLctDtlDvo dvo = converter.mapFindReqToWsnbIstLctDtlDvo(dto);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cntrNo", dto.cntrNo());

            String dtlSn = mapper.findDtlSn(dto);
            map.put("dtlSn", dtlSn);
            dvo.setDtlSn(dtlSn);
            if (Integer.parseInt(dtlSn) >= 001) {
                int dtlsnLength = mapper.findIstLctDtlSnLength(map);
                if (dtlsnLength > 0) {
                    result += mapper.insertInitializeIstLocationDetail(dvo);
                }
            }
            processCount += result;
        }
        return processCount;
    }

}
