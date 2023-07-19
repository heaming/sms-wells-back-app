package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyErrorCodeMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

/**
 *
 * <pre>
 * K-W-SV-U-0304M01 상품별 에러코드 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.03
 */
@Mapper
public interface WsnyErrorCodeMgtMapper {
    /**
     * 상품별 에러코드 관리 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    List<WsnyErrorCodeMgtDvo> selectErrorCodePages(WsnyErrorCodeMgtDto.SearchReq searchReq, PageInfo pageInfo);

    /**
     * 상품별 에러코드 관리 조회(엑셀 다운로드)
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnyErrorCodeMgtDvo> selectErrorCodePages(WsnyErrorCodeMgtDto.SearchReq searchReq);

    int deleteErrorCode(WsnyErrorCodeMgtDvo dvo);

    int insertErrorCode(WsnyErrorCodeMgtDvo dvo);

    int updateErrorCode(WsnyErrorCodeMgtDvo dvo);
}
