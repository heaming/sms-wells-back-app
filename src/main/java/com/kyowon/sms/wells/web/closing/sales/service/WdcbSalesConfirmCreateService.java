package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.sales.converter.WdcbSalesConfirmCreateConverter;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesConfirmCreateDto.CreateReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmCreateDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesConfirmCreateMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 매출확정생성 서비스(W-CL-S-0009)
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-04-13
 */
@Service
@RequiredArgsConstructor
public class WdcbSalesConfirmCreateService {

    private final WdcbSalesConfirmCreateMapper mapper;
    private final WdcbSalesConfirmCreateConverter conveter;

    /**
     * TODO: 1. 판매유형/판매유형상세/상품코드로 웹매출유형, sap매출유형코드 매핑
     * TODO: 2. sap-, co- , wbs- 등의 코드  매핑
     */

    /**
     * 매출확정생성 서비스
     * @param cntrNo   계약번호
     * @param cntrSn    계약일련번호
     * @param slDt  매출일자
     * @param sapPlntCd SAP플랜트코드
     * @param sapSaveLctVal SAP저장위치값
     * @param slYm  매출년월
     * @param sapOgInfCd    SAP조직정보코드
     * @param webSlTpCd 웹매출유형코드
     * @param ctrlOrdTpCd   CO주문유형코드
     * @param sapMatCd  SAP자재코드
     * @param slQty 매출수량
     * @param slAmt 매출금액
     * @param vat   부가가치세
     * @param cscnCd    코스트센터코드
     * @param wbsCd WBS코드
     * @param sapPdctSclsrtStrcVal  SAP제품계층구조값
     * @param sapPurpMatCd  SAP목적자재코드
     * @param sapTxnDtfrCd  SAP과세면세코드
     * @param sapTxinvPblBaseCd SAP세금계산서발행기준코드
     * @param ichrPrtnrNo   담당파트너번호
     * @param rvpyYn    수불여부
     * @param saveGdsYn 저장물품여부
     * @param iostDt    입출고일자
     * @param kwGrpCoCd 교원그룹회사코드
     * @param ogTpCd    조직유형코드
     * @param sellChnlCd    판매채널코드
     * @param sellTpCd  판매유형코드
     * @param sellTpDtlCd   판매유형상세코드
     * @param pdCd  상품코드
     * @param cstNo 고객번호
     * @param prtnrNo   파트너번호
     * @param sapSlTpCd SAP매출유형코드
     * @param sapSlDvCd SAP매출구분코드
     * @param slRgstPrgId   매출등록프로그램ID
     * @param slpAkNo   매출전표요청번호
     * @param slpAkSn   매출전표요청일련번호
     * @param sapTrsDt  SAP전송일자
     * @param sapRfdt   SAP반영일자
     * @param sapSlpno  SAP전표번호
     * @param sapSlipMsg    SAP전표메시지
     * @param slipSlDt  전표매출일자
     * @param svBizHclsfCd  서비스업무대분류코드
     * @param cstSvAsnNo    고객서비스배정번호
     * @param cstSvExcnSn   고객서비스수행일련번호
     * @param slipPblPrdCd  전표발행주기코드
     * @param onscrSchdOpDt 화상일정개설일자
     * @param onscrFstLsnDt 화상최초수업일자
     * @param lrnnSappCtfDt 학습앱인증일자
     * @param lrnnStpDt 학습중지일자
     * @param lrnnDc    학습일수
     * @param lrnnSvUprcAmt 학습서비스단가금액
     * @param pvdaOjPcam    현재가치할인차금대상원금
     * @param pvdaAmt   현재가치할인차금금액
     * @param woSuscDc  전체구독일수
     * @param onscrResTms   화상잔여횟수
     * @param lrnnStrtdt    학습시작일자
     * @param lrnnEnddt 학습종료일자
     * @param thmLrnnStrtD  당월학습시작일
     * @param thmLrnnEndD   당월학습종료일
     * @param thmLrnnFshYn  당월학습완료여부
     * @param cntrDt    계약일자
     * @param cntrAmt   계약금액
     * @param frisuMlgAmt   무상마일리지금액
     * @param istmPcamAmt   할부원금금액
     * @param istmFeeLvyAmt 할부수수료부과금액
     * @param istmAmt   할부금액
     * @param istmMcn   할부개월수
     * @param woSuscMcntN   전체구독개월수
     * @param mmMpyAmt  월납부금액
     * @param lbryMslrDUprc 라이브러리화상학습일단가
     * @param lbryMslrDscDUprc  라이브러리화상학습할인일단가
     * @param thmLrnnDc 당월학습일수
     * @param cntrTotAmt    계약총금액
     * @param mmIntamDscAmt 월할부금할인금액
     * @param cntramThmDscAmt   계약금당월할인금액
     * @param combiDscAmt   결합할인금액
     * @param dscSlAmt  할인매출금액
     * @param combiDscSlAmt 결합할인매출금액
     * @param slCanAmt  매출취소금액
     * @param slChAmt   매출변경금액
     * @param dfaAmt    대손금액
     * @param dfaPrcsdt 대손처리일자
     * @param slCtrAmt  매출조정금액
     * @param slExCd    매출예외코드
     * @param slExCn    매출예외내용
     * @param nkPdChDpAmt   누리키즈상품변경입금금액
     * @param ostrAmt   출고금액
     * @param rtngdAmt  반품금액
     * @param ostrDscAmt    출고할인금액
     * @param rtngdDscAmt   반품할인금액
     * @param nkPdChEotBndAmt   누리키즈상품변경기말채권금액
     * @param sellQty   판매수량
     * @param sellSplAmt    판매공급금액
     * @param cntrTam   계약총액
     * @param fnnLeasePcamTam   금융리스원금총액
     * @param fnnLeaseIntTam    금융리스이자총액
     * @param subscAmt  청약금액
     * @param tkAmt 인수금액
     * @param rentalRgstCost    렌탈등록비
     * @param rentalRgstCostVat 렌탈등록비부가가치세
     * @param rentalAmt 렌탈금액
     * @param rentalPtrm    렌탈기간
     * @param rentalDscAmt  렌탈할인금액
     * @param rentalDc  렌탈일수
     * @param thmOcFeeAmt   당월발생수수료금액
     * @param dscAmt    할인금액
     * @param cntramDpAmt   계약금입금금액
     * @param bilDscAmt 청구할인금액
     * @param ovrCtrDpAmt   초과조정입금금액
     * @param prpdSlAmt 선수매출금액
     * @param sellFee   판매수수료
     * @param prmStrtY  선납시작년도
     * @param prmStrtMm 선납시작월
     * @param prmEndY   선납종료년도
     * @param prmEndMm  선납종료월
     * @param prmMcn    선납개월수
     * @param prmDscr   선납할인율
     * @param prmDscAmt 선납할인금액
     * @param prmDpAmt  선납입금금액
     * @param prmRfndAmt    선납환불금액
     * @param prmRplcAmt    선납대체금액
     * @param prmSlAmt  선납매출금액
     * @param nomSlAmt  정상매출금액
     * @param spmtSlAmt 추가매출금액
     * @param nomDscAmt 정상할인금액
     * @param spmtDscAmt    추가할인금액
     * @param leaseSlCtrAmt 리스매출조정금액
     * @param leaseSlCanAmt 리스매출취소금액
     * @param leaseSlCtrTotAmt  리스매출조정총금액
     * @param nomIntAmt 정상이자금액
     * @param spmtIntAmt    추가이자금액
     * @param intNomDscAmt  이자정상할인금액
     * @param intSpmtDscAmt 이자추가할인금액
     * @param intCtrAmt 이자조정금액
     * @param intSumAmt 이자합계금액
     * @param intVat    이자부가가치세
     * @param thmOcSvSlAmt  당월발생서비스매출금액
     * @param thmOcSvSlVat  당월발생서비스매출부가가치세
     * @param rtngdYn   반품여부
     * @param frisuYn   무상여부
     * @param fgptYn    사은품여부
     * @param ostrDtm   출고일시
     * @param sppDtm    배송일시
     * @param istDtm    설치일시
     * @param reqdDtm   철거일시
     * @param svDt  서비스일자
     * @param dtaDlYn   데이터삭제여부
     * @return 처리완료 여부(Y), 오류난 경우(N) 
     * @throws BizException 조회 결과가 없는 경우 Exception 처리
     */
    @Transactional
    public String createSalesConfirm(CreateReq dto) throws BizException {
        WdcbSalesConfirmCreateDvo inputDvo = conveter.mapCreateReqToWdcbSalesConfirmCreateDvo(dto);

        int result = mapper.insertSalesConfirm(inputDvo);

        return result >= 1 ? "Y" : "N";
    }
}
