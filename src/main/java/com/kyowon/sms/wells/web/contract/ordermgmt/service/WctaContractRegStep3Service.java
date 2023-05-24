package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sms.common.web.closing.mileage.dvo.ZdceMileageRestAmountDvo;
import com.kyowon.sms.common.web.closing.mileage.service.ZdceMileageService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep3Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep3Service {
    private final WctaContractRegStep3Mapper mapper;

    private final WctaContractRegService regService;
    private final ZdceMileageService mileageService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep3Dvo step3Dvo = new WctaContractRegStep3Dvo();

        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        List<WctaContractDtlDvo> dtls = regService.selectContractDtl(cntrNo);
        WctaContractDtlDvo firstDtl = dtls.get(0);
        WctaContractAdrpcBasDvo adrpc = regService.selectContractAdrpcBas(cntrNo);
        List<ZdceMileageRestAmountDvo> tmlgs = mileageService.getMileageRestAmounts(bas.getCntrCstNo()).stream()
            .filter((mlg) -> StringUtils.containsAny(mlg.getMlgTpCd(), "0702", "0802", "0803")).toList();

        // FIXME 마일리지 테스트용 코드 START
        List<ZdceMileageRestAmountDvo> mlgs = Lists.newArrayList();
        mlgs.addAll(tmlgs);
        ZdceMileageRestAmountDvo tmpMlgForTest = new ZdceMileageRestAmountDvo();
        tmpMlgForTest.setMlgTpCd("0802");
        tmpMlgForTest.setMlgResAmt(new BigDecimal(80200));
        mlgs.add(tmpMlgForTest);
        ZdceMileageRestAmountDvo tmpMlgForTest2 = new ZdceMileageRestAmountDvo();
        tmpMlgForTest2.setMlgTpCd("0803");
        tmpMlgForTest2.setMlgResAmt(new BigDecimal(80300));
        mlgs.add(tmpMlgForTest2);
        // FIXME 마일리지 테스트용 코드 END

        // 마일리지 중 0원인 경우 삭제
        for (int i = mlgs.size() - 1; i >= 0; i--) {
            ZdceMileageRestAmountDvo mlg = mlgs.get(i);
            if (BigDecimal.ZERO.compareTo(mlg.getMlgResAmt()) == 0)
                mlgs.remove(mlg);
        }

        if (ObjectUtils.isNotEmpty(adrpc) && StringUtils.isNotEmpty(adrpc.getCntrAdrpcId())) {
            // 기존 데이터 조회인 경우(주소지기본으로 확인)
            step3Dvo.setRcgvpKnm(adrpc.getRcgvpKnm());
            step3Dvo.setCralLocaraTno(adrpc.getCralLocaraTno());
            step3Dvo.setMexnoEncr(adrpc.getMexnoEncr());
            step3Dvo.setCralIdvTno(adrpc.getCralIdvTno());
            step3Dvo.setAdrDvCd(adrpc.getAdrDvCd());
            step3Dvo.setSppDuedt(firstDtl.getSppDuedt());
            step3Dvo.setCntrtRelCd(adrpc.getCntrtRelCd());
            step3Dvo.setAdrId(adrpc.getAdrId());
            step3Dvo.setAdr(adrpc.getAdr());
            step3Dvo.setAdrDtl(adrpc.getAdrDtl());
            step3Dvo.setZip(adrpc.getZip());
            step3Dvo.setStlmTpCd(firstDtl.getStlmTpCd());
            // 결제관계 전체 리스트에서 수납코드구분 01일 때 0101이나 0201이 하나라도 존재하는 경우 해당 데이터 선택
            List<WctaContractStlmRelDvo> stlmRels = Lists.newArrayList();
            Set<Long> istmMcns = Sets.newHashSet();
            dtls.forEach(
                (dtl) -> {
                    // 금액: 계약결제관계 세팅
                    List<WctaContractStlmRelDvo> stlms = regService
                        .selectContractStlmRel(dtl.getCntrNo(), dtl.getCntrSn());
                    stlmRels
                        .addAll(stlms.stream().filter((stlm) -> "01".equals(stlm.getRveDvCd())).toList());
                    // 상세 하위의 결제관계에서 마일리지를 사용하는 데이터가 있다면
                    boolean useMlg = stlms.stream()
                        .anyMatch((stlm) -> StringUtils.containsAny(stlm.getDpTpCd(), "0702", "0802", "0803"));
                    // 계약금 / 일시불 (무조건 존재)
                    dtl.setCntram(
                        stlms.stream().filter((stlm) -> StringUtils.containsAny(stlm.getDpTpCd(), "0201", "0101"))
                            .findFirst().get().getStlmAmt()
                    );
                    // 할부금 (없는 경우 0)
                    dtl.setMmIstmAmt(
                        stlms.stream().filter((stlm) -> StringUtils.containsAny(stlm.getDpTpCd(), "0203")).findFirst()
                            .orElse(
                                WctaContractStlmRelDvo.builder()
                                    .stlmAmt(0l)
                                    .build()
                            ).getStlmAmt()
                    );
                    // stlmRels에는 마일리지 데이터만 저장
                    dtl.setStlmRels(
                        stlms.stream()
                            .filter((stlm) -> StringUtils.containsAny(stlm.getDpTpCd(), "0702", "0802", "0803"))
                            .collect(Collectors.toList())
                    );
                    // mlgYn Y, 없다면 N (화면 표시용)
                    dtl.setMlgYn(useMlg ? "Y" : "N");
                    // 할부개월 일치여부 확인
                    istmMcns.add(dtl.getIstmMcn());
                }
            );
            // 계약금/일시금 결제방법 카드/가상계좌 중 하나 찾으면 해당 값 세팅(무조건 통일이므로)
            step3Dvo.setCntramDpTpCd(
                stlmRels.stream().filter((stlm) -> StringUtils.containsAny(stlm.getDpTpCd(), "0201", "0101"))
                    .findFirst().get().getDpTpCd()
            );
            // 잔금 결제방법은 카드자동이체 고정
            step3Dvo.setIntamDpTpCd("0203");
            step3Dvo.setIstmMcn(firstDtl.getIstmMcn());
            // 할부개월 세부설정(set의 size가 1개라면 모두 일치하므로 N, 아닌 경우 할부개월이 다른 경우이므로 Y)
            step3Dvo.setIdvIstmYn(istmMcns.size() == 1 ? "N" : "Y");
        } else {
            // 신규인 경우
            step3Dvo = mapper.selectAdrInfoByCntrCstNo(bas.getCntrCstNo());
            step3Dvo.setSppDuedt(LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            step3Dvo.setCntrtRelCd("01");
            step3Dvo.setStlmTpCd("10");
            step3Dvo.setCntramDpTpCd("0201");
            step3Dvo.setIntamDpTpCd("0203");
            step3Dvo.setIstmMcn(12l); // TODO 할부개월 설정 로직 추가할 것
            step3Dvo.setIdvIstmYn("N");
            // 금액: 마일리지 적용
            List<WctaContractStlmRelDvo> stlmRels = Lists.newArrayList();
            mlgs.forEach((mlg) -> {
                stlmRels.add(
                    WctaContractStlmRelDvo.builder()
                        .dpTpCd(mlg.getMlgTpCd())
                        .build()
                );
            });
            dtls.forEach((dtl) -> {
                dtl.setStlmRels(stlmRels);
                dtl.setMlgYn("N");
                dtl.setIstmMcn(12l); // FIXME 할부개월 default값
            });
        }
        // 주소관계 전체 리스트에서 전집이 존재하는 경우(상세 판매구분 코드 1), 해당 데이터의 주소관계의 배송방식유형코드 조회
        Optional<WctaContractDtlDvo> cocns = dtls.stream()
            .filter((dtl) -> "1".equals(dtl.getSellTpDtlCd())).findFirst();
        step3Dvo.setIncludeCocn(cocns.isPresent());
        step3Dvo.setSppMthdTpCd(cocns.isPresent() ? cocns.get().getAdrRels().getSppMthdTpCd() : "1");

        // XXX 계약결제관계 화면에서 마일리지 보여주기 위해 dummy 추가
        for (WctaContractDtlDvo dtl : dtls) {
            List<WctaContractStlmRelDvo> stlmRels = dtl.getStlmRels();
            for (ZdceMileageRestAmountDvo mlg : mlgs) {
                Optional<WctaContractStlmRelDvo> rml = stlmRels.stream()
                    .filter((stlm) -> stlm.getDpTpCd().equals(mlg.getMlgTpCd())).findFirst();
                if (rml.isEmpty()) {
                    stlmRels.add(
                        WctaContractStlmRelDvo.builder()
                            .dpTpCd(mlg.getMlgTpCd())
                            .build()
                    );
                }
            }
        }

        step3Dvo.setMlgs(mlgs);
        step3Dvo.setCntrNo(cntrNo);
        step3Dvo.setBas(bas);
        step3Dvo.setDtls(dtls);
        return WctaContractRegDvo.builder()
            .step3(step3Dvo)
            .build();
    }

    @Transactional
    public String saveContractStep3(WctaContractRegStep3Dvo dvo) {
        String now = DateUtil.todayNnow();
        String cntrNo = dvo.getBas().getCntrNo();

        // step3, 4 정보 삭제
        regService.removeStep3Data(cntrNo);
        regService.removeStep4Data(cntrNo);

        // 0. 계약기본
        regService.updateCntrPrgsStatCd(dvo.getCntrNo(), "14");
        mapper.deleteStlmRelsStep3(cntrNo);
        mapper.deleteAdrpcBasStep3(cntrNo);
        mapper.deleteAdrRelsStep3(cntrNo);

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );

        // 1. 계약주소지기본(EDU는 한 건 적재)
        WctaContractAdrpcBasDvo adrpcBasDvo = WctaContractAdrpcBasDvo.builder()
            .cntrNo(dvo.getCntrNo())
            .cntrCstNo(dvo.getBas().getCntrCstNo())
            .cntrtRelCd(dvo.getCntrtRelCd())
            .rcgvpKnm(dvo.getRcgvpKnm())
            .copnDvCd(dvo.getCopnDvCd())
            .natCd("KR")
            .adrDvCd(dvo.getAdrDvCd())
            .adrId(dvo.getAdrId())
            .cralLocaraTno(dvo.getCralLocaraTno())
            .mexnoEncr(dvo.getMexnoEncr())
            .cralIdvTno(dvo.getCralIdvTno())
            .cnrSppYn("N")
            .build();
        mapper.insertCntrAdrpcBasStep3(adrpcBasDvo);

        for (WctaContractDtlDvo dtl : dvo.getDtls()) {
            // 2-1. 계약상세
            dtl.setIstmIntAmt(0l);
            dtl.setRveCrpCd("J0"); // TODO 전집멤버십 20?
            dtl.setStlmTpCd(dvo.getStlmTpCd());
            dtl.setCrncyDvCd("KRW");
            dtl.setSppDuedt(dvo.getSppDuedt());
            mapper.updateCntrDtlStep3(dtl);
            // 2-2. 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(dtl.getCntrNo())
                    .cntrSn(dtl.getCntrSn())
                    .histStrtDtm(now)
                    .build()
            );

            // 3. 계약결제관계
            if ("10".equals(dvo.getStlmTpCd())) {
                // 3-1. 결제유형 일시불
            } else if ("20".equals(dvo.getStlmTpCd())) {
                // 3-2. 결제유형 할부
                for (WctaContractStlmRelDvo stlmRel : dtl.getStlmRels()) {
                    // stlmRel은 모두 마일리지, 계약금과 할부금은 dtl에 저장해서 내려옴
                    if (ObjectUtils.isNotEmpty(stlmRel.getStlmAmt())) {
                        stlmRel.setVlStrtDtm(now);
                        stlmRel.setVlEndDtm(CtContractConst.END_DTM);
                        stlmRel.setCntrUnitTpCd("020");
                        stlmRel.setDtlCntrNo(dtl.getCntrNo());
                        stlmRel.setDtlCntrSn(dtl.getCntrSn());
                        stlmRel.setRveDvCd("01"); // 마일리지는 계약금에만 사용
                        stlmRel.setIslndIncmdcTpCd(""); // TODO 도서지역소득공제유형코드
                        mapper.insertCntrStlmRelStep3(stlmRel);
                    }
                }
                // TODO 계약금 0원일 때 저장여부 확인
                if (0 != dtl.getCntram().intValue()) {
                    mapper.insertCntrStlmRelStep3(
                        WctaContractStlmRelDvo.builder()
                            .vlStrtDtm(now)
                            .vlEndDtm(CtContractConst.END_DTM)
                            .cntrUnitTpCd("020")
                            .dtlCntrNo(dtl.getCntrNo())
                            .dtlCntrSn(dtl.getCntrSn())
                            .rveDvCd("01")
                            .dpTpCd(dvo.getCntramDpTpCd())
                            .islndIncmdcTpCd("")
                            .stlmAmt(dtl.getCntram())
                            .build()
                    );
                }
                // TODO 할부금 0원일 때 저장여부 확인
                if (0 != dtl.getMmIstmAmt().intValue()) {
                    mapper.insertCntrStlmRelStep3(
                        WctaContractStlmRelDvo.builder()
                            .vlStrtDtm(now)
                            .vlEndDtm(CtContractConst.END_DTM)
                            .cntrUnitTpCd("020")
                            .dtlCntrNo(dtl.getCntrNo())
                            .dtlCntrSn(dtl.getCntrSn())
                            .rveDvCd("03")
                            .dpTpCd(dvo.getIntamDpTpCd())
                            .islndIncmdcTpCd("")
                            .stlmAmt(dtl.getMmIstmAmt())
                            .build()
                    );
                }
            }

            // 4. 계약주소관계
            mapper.insertCntrAdrRelStep3(
                WctaContractAdrRelDvo.builder()
                    .vlStrtDtm(now)
                    .vlEndDtm(CtContractConst.END_DTM)
                    .adrpcTpCd("2")
                    .cntrUnitTpCd("020")
                    .dtlCntrNo(dtl.getCntrNo())
                    .dtlCntrSn(dtl.getCntrSn())
                    .cntrAdrpcId(adrpcBasDvo.getCntrAdrpcId())
                    // 전집의 경우 입력받은 값, 학습지는 무조건 택배
                    .sppMthdTpCd("1".equals(dtl.getSellTpCd()) ? dvo.getSppMthdTpCd() : "2")
                    // TODO 배송담당자 조회 서비스 사용, 배송담당사무소코드와 배송담당사용자ID 세팅
                    .build()
            );
        }
        return dvo.getCntrNo();
    }
}
