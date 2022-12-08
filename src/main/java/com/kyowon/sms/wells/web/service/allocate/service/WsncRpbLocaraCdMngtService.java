package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbLocaraCdMngtConverter;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbLocaraCdMngtMapper;
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
public class WsncRpbLocaraCdMngtService {

    private final WsncRpbLocaraCdMngtMapper mapper;
    private final WsncRpbLocaraCdMngtConverter converter;

    /**
     * 책임지역 지역코드 관리
     * @param dto : { zipForm : 우편번호 From, zipTo : 우편번호 To, fr2pLgldCd : 법정동코드 앞 2자리, ctpvNm : 시도명, ctctyNm : 시군구명,
     *           ogId :서비스센터 ID, wrkGrpCd : 작업그룹코드, applyDate : 적용일자, locaraCdFrom : 지역코드 From, locaraCdTo : 지역코드 To }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getAfterServiceCodeMngtPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectRpbLocaraCdMngtPages(dto, pageInfo);
    }

    public List<SearchRes> getAfterServiceCodeMngtExcelDownload(SearchReq dto) {
        return mapper.selectRpbLocaraCdMngtPages(dto);
    }
}
