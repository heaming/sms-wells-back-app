package com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchAutoFntDsnWdrwCstReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchAutoFntDsnWdrwCstRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SearchWwdaBilFntAkDtlRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaAutoFntDsnWdrwMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaAutomaticFntOjYnConfDvo;
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
public interface WwdaAutoFntDsnWdrwMgtMapper {

    /** 자동이체 지정 출금 고객 조회
     * 
     * @param SearchAutoFntDsnWdrwCstReq
     * @return PagingResult<SearchAutoFntDsnWdrwCstRes>
     */
    PagingResult<SearchAutoFntDsnWdrwCstRes> selectAftnDsnWdrwCstInqrPages(
        SearchAutoFntDsnWdrwCstReq req
    );

    //    SearchContractDetailInfRes selectContractDetailInf(WwdaAutoFntDsnWdrwMgtDvo dvo); 왜필요한지 모르겠음

    /** 자동이체 대상 여부 확인
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return WwdaAutomaticFntOjYnConfDvo
     */
    WwdaAutomaticFntOjYnConfDvo selectAutomaticFntOjYnConf(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 통합청구 등록 고객 확인
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int selectItgWdrwRgstCstCk(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌 이체 지정 출금 기본 건수 조회
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int selectAcFntDsnWdrwBasCt(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌이체지정출금기본 저장
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int insertAutoFntDsnWdrwCstBas(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌이체지정출금이력 저장
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int insertAutoFntDsnWdrwCstHist(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 청구이체요청상세 , 청구이체요청기본 데이터 존재 여부
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int selectBilFntAkCt(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌이체지정출금관계 저장
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int insertAutoFntDsnWdrwRel(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌이체지정출금기본 삭제
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return int
     */
    int deleteAutoFntDsnWdrwCst(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌이체지정출금관계 삭제
     * 
     * @param dvo
     * @return
     */
    int deleteAutoFntDsnWdrwRel(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 청구이체요청상세 조회
     * 
     * @param WwdaAutoFntDsnWdrwMgtDvo
     * @return SearchWwdaBilFntAkDtlRes
     */
    SearchWwdaBilFntAkDtlRes selectBilFntAkDtl(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 기존 데이터가 삭제된 것인지 조회
     * 
     * @param dvo
     * @return
     */
    int selectAcFntDsnWdrwBasByPk(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 삭제된 데이터 'N'으로 변경 후 처리
     * 
     * @param dvo
     * @return
     */
    int updateAcFntDsnWdrwBasByPk(WwdaAutoFntDsnWdrwMgtDvo dvo);

    /** 계좌이체지정출금기본 수정
     * 
     * @param dvo
     * @return
     */
    int updateAutoFntDsnWdrwCst(WwdaAutoFntDsnWdrwMgtDvo dvo);

}
