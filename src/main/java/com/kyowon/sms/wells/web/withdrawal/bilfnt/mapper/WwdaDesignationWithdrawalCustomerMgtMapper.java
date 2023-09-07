package com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.CheckBillingFundTransferAsk;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SearchAutoFntDsnWdrwCstReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SearchAutoFntDsnWdrwCstRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaAutomaticFntOjYnConfDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaDesignationWithdrawalCustomerMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaDesignationWithdrawalDeleteDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * 자동이체 지정 출금 고객 관리 매퍼
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-01-30
 */
@Mapper
public interface WwdaDesignationWithdrawalCustomerMgtMapper {

    /**
     * 자동이체 지정 출금 고객 조회
     *
     * @param pageInfo
     * @param SearchAutoFntDsnWdrwCstReq
     * @return PagingResult<SearchAutoFntDsnWdrwCstRes>
     */
    PagingResult<SearchAutoFntDsnWdrwCstRes> selectAftnDsnWdrwCstInqrPages(
        SearchAutoFntDsnWdrwCstReq req, PageInfo pageInfo
    );

    /**
     * 자동이체 지정 출금 고객 엑셀다운로드
     *
     * @param req
     * @return
     */
    List<SearchAutoFntDsnWdrwCstRes> selectAftnDsnWdrwCstInqrPages(SearchAutoFntDsnWdrwCstReq req);

    //    SearchContractDetailInfRes selectContractDetailInf(WwdaDesignationWithdrawalCustomerMgtDvo dvo); 왜필요한지 모르겠음

    /**
     * 자동이체 대상 여부 확인
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return WwdaAutomaticFntOjYnConfDvo
     */
    WwdaAutomaticFntOjYnConfDvo selectAutomaticFntOjYnConf(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 통합청구 등록 고객 확인
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    int selectItgWdrwRgstCstCk(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌 이체 지정 출금 기본 건수 조회
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    int selectAcFntDsnWdrwBasCt(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌이체지정출금기본 저장
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    int insertAutoFntDsnWdrwCstBas(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌이체지정출금이력 저장
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    int insertAutoFntDsnWdrwCstHist(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 청구이체요청상세 , 청구이체요청기본 데이터 존재 여부
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    CheckBillingFundTransferAsk selectBilFntAkCt(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌이체지정출금관계 저장
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    int insertAutoFntDsnWdrwRel(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌이체지정출금기본 삭제
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return int
     */
    int deleteAutoFntDsnWdrwCst(WwdaDesignationWithdrawalDeleteDvo dvo);

    /**
     * 계좌이체지정출금관계 삭제
     *
     * @param dvo
     * @return
     */
    int deleteAutoFntDsnWdrwRel(WwdaDesignationWithdrawalDeleteDvo dvo);

    /**
     * 청구이체요청상세 조회
     *
     * @param WwdaDesignationWithdrawalCustomerMgtDvo
     * @return WwdaAutomaticFntOjYnConfDvo
     */
    WwdaAutomaticFntOjYnConfDvo selectBilFntAkDtl(WwdaDesignationWithdrawalDeleteDvo dvo);

    /**
     * 기존 데이터가 삭제된 것인지 조회
     *
     * @param dvo
     * @return
     */
    int selectAcFntDsnWdrwBasByPk(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 삭제된 데이터 'N'으로 변경 후 처리
     *
     * @param dvo
     * @return
     */
    int updateAcFntDsnWdrwBasByPk(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌이체지정출금기본 수정
     *
     * @param dvo
     * @return
     */
    int updateAutoFntDsnWdrwCst(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 계좌이체지정출금관계 Y로 변환
     *
     * @param dvo
     * @return
     */
    int updateAutoFntDsnWdrwRelByPk(WwdaDesignationWithdrawalCustomerMgtDvo dvo);

    /**
     * 삭제 이력 생성
     *
     * @param dvo
     * @return
     */
    int deleteAutoFntDsnWdrwCstHist(WwdaDesignationWithdrawalDeleteDvo dvo);
}
