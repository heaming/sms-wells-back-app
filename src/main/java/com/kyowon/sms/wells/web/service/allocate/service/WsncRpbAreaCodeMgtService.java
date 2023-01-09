package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbAreaCodeMgtConverter;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaCodeDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbAreaCodeMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.11.22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncRpbAreaCodeMgtService {

    private final WsncRpbAreaCodeMgtMapper mapper;
    private final WsncRpbAreaCodeMgtConverter converter;

    /**
     * 책임지역 지역코드 관리 - 조회 (페이징)
     * @param dto : { zipForm : 우편번호 From, zipTo : 우편번호 To, fr2pLgldCd : 법정동코드 앞 2자리, ctpvNm : 시도명, ctctyNm : 시군구명,
     *           ogId :서비스센터 ID, wrkGrpCd : 작업그룹코드, applyDate : 적용일자, locaraCdFrom : 지역코드 From, locaraCdTo : 지역코드 To }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getAreaCodePages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAreaCodePages(dto, pageInfo);
    }

    /**
     * 책임지역 지역코드 관리 - 조회 (엑셀다운로드)
     * @param dto : { zipForm : 우편번호 From, zipTo : 우편번호 To, fr2pLgldCd : 법정동코드 앞 2자리, ctpvNm : 시도명, ctctyNm : 시군구명,
     *           ogId :서비스센터 ID, wrkGrpCd : 작업그룹코드, applyDate : 적용일자, locaraCdFrom : 지역코드 From, locaraCdTo : 지역코드 To }
     * @return 조회결과
     */
    public List<SearchRes> getAreaCodePagesExcelDownload(SearchReq dto) {
        return mapper.selectAreaCodePages(dto);
    }

    /**
     * 책임지역 지역코드 관리 - 저장
     * @param dtos : [{ chLocaraCd : 책임지역코드, fr2pLgldCd : 법정동코드 앞 2자리, ctpvNm : 시도명, ctctyNm : 시군구명,
     *           lawcEmdNm :법정읍면동명, amtdNm : 행정동명, apyStrtdt : 적용시작일자, apyEnddt : 적용종료일자 }]
     * @return
     */
    @Transactional
    public int createAreaCodes(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WsncRpbAreaCodeDvo dvo = converter.mapSaveReqToWsncRpbAreaCodeDvo(dto);

            int result = mapper.insertAreaCode(dvo);
            int psicCount = mapper.selectCountAreaCodePsic(dvo);
            if (psicCount == 0) {
                mapper.insertAreaCodePsic(dvo);
            }
            processCount += result;
        }
        return processCount;
    }

}
