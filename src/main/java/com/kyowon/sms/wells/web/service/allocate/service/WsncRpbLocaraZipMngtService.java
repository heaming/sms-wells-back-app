package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraZipMngtDvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbLocaraZipMngtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbLocaraZipMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncRpbLocaraZipMngtService {

    private final WsncRpbLocaraZipMngtMapper mapper;

    private final WsncRpbLocaraZipMngtConverter converter;

    /**
     * 책임지역 우편번호 관리 - 조회 (페이징)
     *
     * @param dto      : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @param pageInfo
     * @return
     */
    public PagingResult<WsncRpbLocaraZipMngtDto.SearchRes> getRpbLocaraZipMngtPages(
        WsncRpbLocaraZipMngtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            this.converter.mapCreateResToListDvo(this.mapper.selectRpbLocaraZips(dto, pageInfo)), pageInfo
        );
    }

    /**
     * 책임지역 우편번호 관리 - 엑셀 다운로드
     *
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @return
     */
    public List<WsncRpbLocaraZipMngtDto.SearchRes> getRpbLocaraZipsForExcelDownload(
        WsncRpbLocaraZipMngtDto.SearchReq dto
    ) {
        return this.converter.mapCreateResToListDvo(this.mapper.selectRpbLocaraZips(dto));
    }

    /**
     * 책임지역 우편번호 관리 - 저장
     *
     * @param dtos : [{ newAdrZip: 신주소우편번호, emdSn: 읍면동일련번호, fr2pLgldCd: 앞2자리법정동코드, lawcEmdNm: 법정읍면동명, amtdNm: 행정동명, kynorLocaraYn: 경북지역여부, dtaDlYn: 데이터삭제여부, ctpvNm: 시도명, ctctyNm: 시군구명, ildYn: 섬여부, pdlvNo: 출고지번호 }]
     * @return
     * @throws Exception
     */
    @Transactional
    public int createRpbLocaraZipMngt(@Valid
    List<WsncRpbLocaraZipMngtDto.CreateReq> dtos) throws Exception {
        int processCount = 0;

        for (WsncRpbLocaraZipMngtDto.CreateReq dto : dtos) {
            WsncRpbLocaraZipMngtDvo rpbLocaraZip = this.converter.mapCreateReqToRpbLocaraZipDvo(dto);
            processCount += this.mapper.insertRpbLocaraZip(rpbLocaraZip);
        }

        return processCount;
    }
}
