package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraZipMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

/**
 *
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.17
 */
@Mapper
public interface WsncRpbLocaraZipMngtMapper {

    /**
     * 책임지역 우편번호 관리 - 조회 (페이징)
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @param pageInfo
     * @return
     */
    List<WsncRpbLocaraZipMngtDvo> selectRpbLocaraZips(WsncRpbLocaraZipMngtDto.SearchReq dto, PageInfo pageInfo);

    /**
     * 책임지역 우편번호 관리 - 엑셀 다운로드
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @return
     */
    List<WsncRpbLocaraZipMngtDvo> selectRpbLocaraZips(WsncRpbLocaraZipMngtDto.SearchReq dto);

    /**
     * 책임지역 우편번호 관리 - 저장
     * @param rpbLocaraZip : { newAdrZip: 신주소우편번호, emdSn: 읍면동일련번호, fr2pLgldCd: 앞2자리법정동코드, lawcEmdNm: 법정읍면동명, amtdNm: 행정동명, kynorLocaraYn: 경북지역여부, dtaDlYn: 데이터삭제여부, ctpvNm: 시도명, ctctyNm: 시군구명, ildYn: 섬여부, pdlvNo: 출고지번호 }
     * @return
     */
    int insertRpbLocaraZip(WsncRpbLocaraZipMngtDvo rpbLocaraZip);
}
