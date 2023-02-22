package com.kyowon.sms.wells.web.bond.transfer.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaBondPartTransferConverter;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondPartTransferDvo;
import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaBondPartTransferMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 파트이관 조회,생성,저장 관련 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-02-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WbnaBondPartTransferService {

    private final WbnaBondPartTransferMapper mapper;
    private final WbnaBondPartTransferConverter converter;

    /**
     * 파트이관 집계 정보 조회
     * @param dto 검색 조건
     * @return 파트이관 집계 조회 결과
     */
    public List<SearchRes> getPartTransferAggregation(SearchReq dto) {
        return mapper.selectPartTransferAggregation(dto);
    }

    /**
     * 파트이관 집계 상세 정보 조회
     * @param dto 검색 조건
     * @param pageInfo 페이징 처리 정보
     * @return PagingResult 페이지, 파트이관 집계 상세 목록
     */
    public PagingResult<SearchDetailRes> getPartTransferDetails(
        SearchDetailReq dto, PageInfo pageInfo
    ) {
        return mapper.selectPartTransferDetailPages(dto, pageInfo);
    }

    /**
     * 파트이관 집계 상세 정보 조회 엑셀다운로드 기준(페이징 없음)
     * @param dto 검색 조건
     * @return 파트이관 집계 상세 목록
     */
    public List<SearchDetailRes> getExcelDownload(
        SearchDetailReq dto
    ) {
        return mapper.selectPartTransferDetailPages(dto);
    }

    /**
     * 파트이관 정보 생성
     * @param dto 생성 요청 정보(기준년월, 사업부구분코드, 채권구분코드)
     * @return 갱신건수
     */
    @Transactional
    public int createPartTransfer(
        CreateReq dto
    ) {

        // TODO 테이블 및 쿼리문 정의가 완료 되지 않아 mapper 호출은 하지 않은 상태 DB완료 후 작업 예정
        int processCount = 1;
        WbnaBondPartTransferDvo dvo = converter.mapCreateReqToEbnaBondPartTransferDvo(dto);
        log.debug("dvo: " + dvo);
        // 1. createPartTransfers - TB_CBBO_BND_ASN_IZ 수행(파트이관 생성 조건 참조)
        // 2. createPartTransferItemization - TB_CBBO_BND_TF_ASN_EXCN_IZ 수행
        // 3. createBondContractHistories - TB_CBBO_BND_CNTR_HIST 테이블에 이력 추가
        // 4. 이력 저장 후 updateBondContractBase - TB_CBBO_BND_CNTR_BAS 정보 수정
        return processCount;
    }

    /**
     * 채권 집금구분 정보 갱신
     * @param dtos 갱신 요청 정보(기준년월, 사업부구분코드, 계약번호, 계약일련번호, 고객번호, 집금구분코드)
     * @return 갱신건수
     */
    @Transactional
    public int editPartTransferDetails(
        List<EditReq> dtos
    ) {
        int processCount = 0;
        for (EditReq dto : dtos) {

            // TODO 테이블 및 쿼리문 정의가 완료 되지 않아 mapper 호출은 하지 않은 상태 DB완료 후 작업 예정
            log.debug(dto.cntrNo());
            WbnaBondPartTransferDvo dvo = converter.mapEditReqToEbnaBondPartTransferDvo(dto);
            log.debug("dvo display information on service page: " + dvo);
            processCount += 1;
            // 1. updateBondContractBase - TB_CBBO_BND_CNTR_BAS 정보 수정
            // 2. updateBondAssignItemization - TB_CBBO_BND_ASN_IZ 정보 수정
        }
        return processCount;
    }

    /**
     * 채권 배정 이력 확인(해당 년월 기준 배정 내역 존재 여부 확인)
     * @param baseYm 기준년월
     * @return 조회 결과 true:배정내역존재, false:배정내역미존재
     * @throws BizException 정상 조회 결과 아닌 경우 Exception 처리
     */
    public boolean hasPartTransfer(
        String baseYm
    ) throws BizException {

        //조회된 정보가 true, false에 해당하지 않는 경우 exception발생을 위해 switch 처리
        return switch (mapper.selectHasPartTransfer(baseYm)) {
            case 1 -> true;
            case 0 -> false;
            default -> throw new BizException("MSG_ALT_ERR_CONTACT_ADMIN");
        };
    }

    /**
     * 파트이관 생성전 기존 생성 이력 확인
     * @param dto 검색 조건(기준년월,사업부구분)
     * @return 조회 결과 true:생성이력존재, false:생성이력미존재
     * @throws BizException 정상 조회 결과 아닌 경우 Exception 처리
     */
    public boolean hasPartTransferDetail(
        SearchDetailReq dto
    ) throws BizException {

        //조회된 정보가 true, false에 해당하지 않는 경우 exception발생을 위해 switch 처리
        return switch (mapper.selectHasPartTransferDetail(dto)) {
            case 1 -> true;
            case 0 -> false;
            default -> throw new BizException("MSG_ALT_ERR_CONTACT_ADMIN");
        };
    }

    // TODO: 데이터 없는 경우 화면 테스트가 어려워 임시 데이터 생성용 메소드 추후 삭제 예정
    public List<HashMap<String, String>> getDumyData() {
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        String tempJson = "[";
        tempJson += "{ bzHdqDvCd: '01', clctamDvCd: '01', totCstCt: '40', totCntrCt: '10', woCstCt: '40', woCntrCt: '10', woObjAmt: '30', woDlqAmt: '7', woThmChramAmt: '18,160,000', woDlqAddAmt: '3,367,000', woRsgBorAmt: '770,000', rentalCstCt: '40', rentalCntrCt: '10', rentalObjAmt: '30', rentalDlqAmt: '7', rentalThmChramAmt: '18,160,000', rentalDlqAddAmt: '3,367,000', rentalRsgBorAmt: '770,000', leaseCstCt: '40', leaseCntrCt: '10', leaseObjAmt: '30', leaseDlqAmt: '7', leaseThmChramAmt: '18,160,000', leaseDlqAddAmt: '3,367,000', leaseRsgBorAmt: '770,000', mshCstCt: '40', mshCntrCt: '10', mshObjAmt: '30', mshDlqAmt: '7', mshThmChramAmt: '18,160,000', mshDlqAddAmt: '3,367,000', mshRsgBorAmt: '770,000', rglrSppCstCt: '40', rglrSppCntrCt: '10', rglrSppObjAmt: '30', rglrSppDlqAmt: '7', rglrSppThmChramAmt: '18,160,000', rglrSppDlqAddAmt: '3,367,000', rglrSppRsgBorAmt: '770,000', hcrCstCt: '40', hcrCntrCt: '10', hcrObjAmt: '30', hcrDlqAmt: '7', hcrThmChramAmt: '18,160,000', hcrDlqAddAmt: '3,367,000', hcrRsgBorAmt: '770,000', spayCstCt: '40', spayCntrCt: '10', spayObjAmt: '30', spayDlqAmt: '7', spayThmChramAmt: '18,160,000', spayDlqAddAmt: '3,367,000', spayRsgBorAmt: '770,000'  },";
        tempJson += "{ bzHdqDvCd: '01', clctamDvCd: '01', totCstCt: '40', totCntrCt: '10', woCstCt: '40', woCntrCt: '10', woObjAmt: '30', woDlqAmt: '7', woThmChramAmt: '18,160,000', woDlqAddAmt: '3,367,000', woRsgBorAmt: '770,000', rentalCstCt: '40', rentalCntrCt: '10', rentalObjAmt: '30', rentalDlqAmt: '7', rentalThmChramAmt: '18,160,000', rentalDlqAddAmt: '3,367,000', rentalRsgBorAmt: '770,000', leaseCstCt: '40', leaseCntrCt: '10', leaseObjAmt: '30', leaseDlqAmt: '7', leaseThmChramAmt: '18,160,000', leaseDlqAddAmt: '3,367,000', leaseRsgBorAmt: '770,000', mshCstCt: '40', mshCntrCt: '10', mshObjAmt: '30', mshDlqAmt: '7', mshThmChramAmt: '18,160,000', mshDlqAddAmt: '3,367,000', mshRsgBorAmt: '770,000', rglrSppCstCt: '40', rglrSppCntrCt: '10', rglrSppObjAmt: '30', rglrSppDlqAmt: '7', rglrSppThmChramAmt: '18,160,000', rglrSppDlqAddAmt: '3,367,000', rglrSppRsgBorAmt: '770,000', hcrCstCt: '40', hcrCntrCt: '10', hcrObjAmt: '30', hcrDlqAmt: '7', hcrThmChramAmt: '18,160,000', hcrDlqAddAmt: '3,367,000', hcrRsgBorAmt: '770,000', spayCstCt: '40', spayCntrCt: '10', spayObjAmt: '30', spayDlqAmt: '7', spayThmChramAmt: '18,160,000', spayDlqAddAmt: '3,367,000', spayRsgBorAmt: '770,000'  },";
        tempJson += "{ bzHdqDvCd: '01', clctamDvCd: '01', totCstCt: '40', totCntrCt: '10', woCstCt: '40', woCntrCt: '10', woObjAmt: '30', woDlqAmt: '7', woThmChramAmt: '18,160,000', woDlqAddAmt: '3,367,000', woRsgBorAmt: '770,000', rentalCstCt: '40', rentalCntrCt: '10', rentalObjAmt: '30', rentalDlqAmt: '7', rentalThmChramAmt: '18,160,000', rentalDlqAddAmt: '3,367,000', rentalRsgBorAmt: '770,000', leaseCstCt: '40', leaseCntrCt: '10', leaseObjAmt: '30', leaseDlqAmt: '7', leaseThmChramAmt: '18,160,000', leaseDlqAddAmt: '3,367,000', leaseRsgBorAmt: '770,000', mshCstCt: '40', mshCntrCt: '10', mshObjAmt: '30', mshDlqAmt: '7', mshThmChramAmt: '18,160,000', mshDlqAddAmt: '3,367,000', mshRsgBorAmt: '770,000', rglrSppCstCt: '40', rglrSppCntrCt: '10', rglrSppObjAmt: '30', rglrSppDlqAmt: '7', rglrSppThmChramAmt: '18,160,000', rglrSppDlqAddAmt: '3,367,000', rglrSppRsgBorAmt: '770,000', hcrCstCt: '40', hcrCntrCt: '10', hcrObjAmt: '30', hcrDlqAmt: '7', hcrThmChramAmt: '18,160,000', hcrDlqAddAmt: '3,367,000', hcrRsgBorAmt: '770,000', spayCstCt: '40', spayCntrCt: '10', spayObjAmt: '30', spayDlqAmt: '7', spayThmChramAmt: '18,160,000', spayDlqAddAmt: '3,367,000', spayRsgBorAmt: '770,000'  },";
        tempJson += "{ bzHdqDvCd: '01', clctamDvCd: '01', totCstCt: '40', totCntrCt: '10', woCstCt: '40', woCntrCt: '10', woObjAmt: '30', woDlqAmt: '7', woThmChramAmt: '18,160,000', woDlqAddAmt: '3,367,000', woRsgBorAmt: '770,000', rentalCstCt: '40', rentalCntrCt: '10', rentalObjAmt: '30', rentalDlqAmt: '7', rentalThmChramAmt: '18,160,000', rentalDlqAddAmt: '3,367,000', rentalRsgBorAmt: '770,000', leaseCstCt: '40', leaseCntrCt: '10', leaseObjAmt: '30', leaseDlqAmt: '7', leaseThmChramAmt: '18,160,000', leaseDlqAddAmt: '3,367,000', leaseRsgBorAmt: '770,000', mshCstCt: '40', mshCntrCt: '10', mshObjAmt: '30', mshDlqAmt: '7', mshThmChramAmt: '18,160,000', mshDlqAddAmt: '3,367,000', mshRsgBorAmt: '770,000', rglrSppCstCt: '40', rglrSppCntrCt: '10', rglrSppObjAmt: '30', rglrSppDlqAmt: '7', rglrSppThmChramAmt: '18,160,000', rglrSppDlqAddAmt: '3,367,000', rglrSppRsgBorAmt: '770,000', hcrCstCt: '40', hcrCntrCt: '10', hcrObjAmt: '30', hcrDlqAmt: '7', hcrThmChramAmt: '18,160,000', hcrDlqAddAmt: '3,367,000', hcrRsgBorAmt: '770,000', spayCstCt: '40', spayCntrCt: '10', spayObjAmt: '30', spayDlqAmt: '7', spayThmChramAmt: '18,160,000', spayDlqAddAmt: '3,367,000', spayRsgBorAmt: '770,000'  }";
        tempJson += "]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();
        return gson.fromJson(tempJson, type);
    }

    // TODO: 데이터 없는 경우 화면 테스트가 어려워 임시 데이터 생성용 메소드 추후 삭제 예정
    public PagingResult<HashMap<String, String>> getDumyData2() {
        String tempJson = "[";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'111', clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'222',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'333',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'444',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'555',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'666',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'777',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'888',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'999',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' },";
        tempJson += "{ baseYm:'202302', bzHdqDvCd:'01', cntrNo:'101',clctamDvCd: '01', cntrSn: '2022-1234567-01', prtnrKnm: '김교원', cstNo: '123456789', pdDvCd: '전집', dlqMcn: '1', objAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddDpAmt: '34,500', rsgBorAmt: '17,250', lwmTpCd: '속성값 필요', lwmDtlTpCd: '속성값 필요', lwmDt:'20230213', dumy1:'20230213', dumy2:'을지로입구' }";
        tempJson += "]";
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>() {}.getType();
        ArrayList<HashMap<String, String>> dumyDatas = gson.fromJson(tempJson, type);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalCount(18L); // 페이징 테스트용 설정
        return new PagingResult<>(dumyDatas, pageInfo);
    }
}
