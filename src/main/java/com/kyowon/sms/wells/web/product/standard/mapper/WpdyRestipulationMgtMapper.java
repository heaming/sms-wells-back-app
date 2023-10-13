package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyRestipulationDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wpdy-restipulation-mgt.xml */
/**
 * <pre>
 * 상품 >> 재약정 기본정보 관리 Mapper
 * </pre>
 *
 * @author  junho.bae
 * @since 2023-07-01
 */
@Mapper
public interface WpdyRestipulationMgtMapper {

    /**
     * 재약정 목록 조회
     * @param dto 검색조건
     * @return 재약정 목록
     */
    PagingResult<SearchRes> selectRestipulationPages(SearchReq dto, PageInfo pageInfo);

    /**
     * 재약정그룹 목록 엑셀다운로드
     * @param dto 검색조건
     * @return 재약정 목록
     */
    List<SearchRes> selectRestipulationPages(SearchReq dto);

    /**
     * 재약정 목록 저장 - 추가
     * @param vo 재약정 정보
     * @return 성공여부
     */
    int insertRestipulation(WpdyRestipulationDvo vo);

    /**
     * 재약정 입력시 데이터 중복검사
     * @param vo 재약정
     * @return 중복여부 관련 정보
     */
    int selectDuplicationByPk(WpdyRestipulationDvo vo);

    /**
     * 재약정 목록 저장 -수정
     * @param vo 재약정 정보
     * @return 성공여부
     */
    int updateRestipulation(WpdyRestipulationDvo vo);

    /**
     * 재약정 삭제
     * @param vo 재약정 정보
     * @return 성공여부
     */
    int deleteRestipulation(WpdyRestipulationDvo vo);

}
