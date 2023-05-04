package com.kyowon.sms.wells.web.product.standard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyRestipulationDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/* wpdy-restipulation-mgt.xml */
@Mapper
public interface WpdyRestipulationMgtMapper {

    /**
     * 재약정 목록 조회
     * @param dto
     * @return
     */
    PagingResult<SearchRes> selectRestipulationPages(SearchReq dto, PageInfo pageInfo);

    /**
     * 재약정그룹 목록 엑셀다운로드
     * @param dto
     * @return
     */
    List<SearchRes> selectRestipulationPages(SearchReq dto);

    /**
     * 재약정 목록 저장 - 추가
     * @param attribute
     * @return
     */
    int insertRestipulation(WpdyRestipulationDvo vo);

    /**
     * 재약정 입력시 데이터 중복검사
     * @param attribute
     * @return
     */
    int selectDuplicationByPk(WpdyRestipulationDvo vo);

    /**
     * 재약정 목록 저장 -수정
     * @param attribute
     * @return
     */
    int updateRestipulation(WpdyRestipulationDvo vo);

    /**
     * 재약정 삭제
     * @param vo
     * @return
     */
    int deleteRestipulation(WpdyRestipulationDvo vo);

}
