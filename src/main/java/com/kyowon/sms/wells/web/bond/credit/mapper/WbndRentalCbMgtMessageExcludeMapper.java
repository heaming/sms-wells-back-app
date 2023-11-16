package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtMessageExcludeDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-BN-U-0067M01	렌탈CB 알림톡 발송 제외 자료 등록
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-03-28
 */
@Mapper
public interface WbndRentalCbMgtMessageExcludeMapper {

    /**
     * 렌탈CB 알림톡 발송 제외 자료 등록 페이징 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    PagingResult<SearchRes> selectRentalCbMessageExcludePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    /**
     * 렌탈CB 알림톡 발송 제외 자료 등록 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    List<SearchRes> selectRentalCbMessageExcludePages(
        SearchReq dto
    );

    /**
     * 렌탈CB 알림톡 발송 제외 자료 등록 insert
     * @param dvo
     * @return int
     */
    int insertRentalCbMessageExclude(
        WbndBondContactExcludeIzDvo dvo
    );

    /**
     * 렌탈CB 알림톡 발송 제외 자료 등록 update
     * @param dvo
     * @return int
     */
    int updateRentalCbMessageExclude(
        WbndBondContactExcludeIzDvo dvo
    );

    /**
     * 렌탈CB 알림톡 발송 제외 자료 등록 delete
     * @param dvo
     * @return int
     */
    int deleteRentalCbMessageExclude(
        String bndCntcExcdOjId
    );
}
