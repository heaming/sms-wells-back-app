package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchExcelRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelZipNoDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.06
 */
@Mapper
public interface WsndRegionLevelZipMgtMapper {

    /**
     * 급지 우편번호 관리 - 조회 (페이징)
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, ogId:서비스센터 }
     * @param pageInfo
     * @return
     */
    PagingResult<WsndRegionLevelZipNoDvo> selectZipNos(SearchReq dto, PageInfo pageInfo);

    /**
     * 급지 우편번호 관리 - 엑셀 다운로드
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, ogId:서비스센터 }
     * @return
     */
    List<SearchExcelRes> selectZipNosForExcelDownload(SearchReq dto);

    /**
     * 급지 우편번호 관리 - 저장
     * @param dvo : { newAdrZip: 우편번호, emdSn: 읍면동일련번호, pdlvNo: 출고지번호 }
     * @return
     */
    int updateZipNo(WsndRegionLevelZipNoDvo dvo);

}
