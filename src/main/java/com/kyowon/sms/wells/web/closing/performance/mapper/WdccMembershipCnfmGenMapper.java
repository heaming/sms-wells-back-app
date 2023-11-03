package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.MembershipCnfmGenDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-CL-U-0047M01	멤버십 확정 생성
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-08-02
 */
@Mapper
public interface WdccMembershipCnfmGenMapper {

    /**
     * 멤버십 확정 생성 페이징 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    PagingResult<SearchRes> selectMembershipConfirmGens(SearchReq dto, PageInfo pageInfo);

    /**
     * 멤버십 확정 생성 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    List<SearchRes> selectMembershipConfirmGens(SearchReq dto);

    /**
     * 멤버십 확정 생성 입력받은 파라미터값(가입일자)을 TB_SSCT_CNTR_DTL(계약상세) 테이블의 CNTR_PD_STRTDT(계약상품시작일자) 컬럼을 update
     * @param dvo
     * @return int
     */
    int updateMembershipCnfmGenJoinDate(MembershipCnfmGenDvo dvo);

    /**
     * 멤버십 확정 생성 입력받은 파라미터값(확정일자)을 TB_SSCT_CNTR_BAS(계약기본) 테이블의 CNTR_CNFM_DTM(계약확정일시) 컬럼을 update
     * @param dvo
     * @return int
     */
    int updateMembershipCnfmGenConfirmDate(MembershipCnfmGenDvo dvo);

}
