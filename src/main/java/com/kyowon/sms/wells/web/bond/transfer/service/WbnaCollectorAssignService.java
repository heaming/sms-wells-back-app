package com.kyowon.sms.wells.web.bond.transfer.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaCollectorAssignMapper;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WbnaCollectorAssignService {

    private final WbnaCollectorAssignMapper mapper;

    public List<SearchRes> getCollectorAssigns(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssigns(dto);
    }

    public PagingResult<SearchDetailRes> getCollectorAssignDetails(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectCollectorAssignDetailPages(dto, pageInfo);
    }

    public List<SearchDetailRes> getExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectCollectorAssignDetailPages(dto);
    }

    // TODO: 데이터 없는 경우 화면 테스트가 어려워 임시 데이터 생성용 메소드 추후 삭제 예정
    public List<HashMap<String, String>> getDumyData() {
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        String tempJson = "[";
        tempJson += "{ clctamPrtnrNo: '1', clctamPrtnrKnm: '홍길동', asnRat: '120', woCstn: '30', woCntrCt: '7', rwoTrgAmt: '18,160,000', woDlqAmt: '3,367,000', woThmChramAmt: '770,000', woDlqAddAmt: '336,700', woRsgBorAmt: '336,700', rentalCstn: '30', rentalCntrCt: '7', rentalTrgAmt: '18,160,000', rentalDlqAmt: '3,367,000', rentalThmChramAmt: '770,000', rentalDlqAddAmt: '336,700', rentalRsgBorAmt: '336,700', leaseCstn: '10', leaseCntrCt: '3', leaseTrgAmt: '18,160,000', leaseDlqAmt: '3,367,000', leaseThmChramAmt: '770,000', leaseDlqAddAmt: '336,700', leaseRsgBorAmt: '336,700 ', geMshCstn: '10', geMshCntrCt: '3', geMshTrgAmt: '18,160,000', cgeMshDlqAmt: '3,367,000', geMshDlqAmt: '770,000', geMshThmChramAmt: '336,700', geMshDlqAddAmt: '336,700', geMshRsgBorAmt: '10', hcrMshCstn: '3', hcrMshCntrCt: '18,160,000', hcrMshTrgAmt: '3,367,000', hcrMshDlqAmt: '770,000', hcrMshThmChramAmt: '336,700', hcrMshDlqAddAmt: '336,700', hcrMshRsgBorAmt: '10', spayCntrCt: '3', spayTrgAmt: '18,160,000', spayDlqAmt: '3,367,000', spayThmChramAmt: '770,000', spayDlqAddAmt: '336,700', spayRsgBorAmt: '0', rglrSppCstn: '10', rglrSppCntrCt: '3', rglrSppTrgAmt: '18,160,000', rglrSppDlqAmt: '3,367,000', rglrSppThmChramAmt: '770,000', rglrSppDlqAddAmt: '336,700', rglrSppRsgBorAmt: '166,350' },";
        tempJson += "{ clctamPrtnrNo: '1', clctamPrtnrKnm: '홍길동', asnRat: '120', woCstn: '30', woCntrCt: '7', rwoTrgAmt: '18,160,000', woDlqAmt: '3,367,000', woThmChramAmt: '770,000', woDlqAddAmt: '336,700', woRsgBorAmt: '336,700', rentalCstn: '30', rentalCntrCt: '7', rentalTrgAmt: '18,160,000', rentalDlqAmt: '3,367,000', rentalThmChramAmt: '770,000', rentalDlqAddAmt: '336,700', rentalRsgBorAmt: '336,700', leaseCstn: '10', leaseCntrCt: '3', leaseTrgAmt: '18,160,000', leaseDlqAmt: '3,367,000', leaseThmChramAmt: '770,000', leaseDlqAddAmt: '336,700', leaseRsgBorAmt: '336,700 ', geMshCstn: '10', geMshCntrCt: '3', geMshTrgAmt: '18,160,000', cgeMshDlqAmt: '3,367,000', geMshDlqAmt: '770,000', geMshThmChramAmt: '336,700', geMshDlqAddAmt: '336,700', geMshRsgBorAmt: '10', hcrMshCstn: '3', hcrMshCntrCt: '18,160,000', hcrMshTrgAmt: '3,367,000', hcrMshDlqAmt: '770,000', hcrMshThmChramAmt: '336,700', hcrMshDlqAddAmt: '336,700', hcrMshRsgBorAmt: '10', spayCntrCt: '3', spayTrgAmt: '18,160,000', spayDlqAmt: '3,367,000', spayThmChramAmt: '770,000', spayDlqAddAmt: '336,700', spayRsgBorAmt: '0', rglrSppCstn: '10', rglrSppCntrCt: '3', rglrSppTrgAmt: '18,160,000', rglrSppDlqAmt: '3,367,000', rglrSppThmChramAmt: '770,000', rglrSppDlqAddAmt: '336,700', rglrSppRsgBorAmt: '166,350' },";
        tempJson += "{ clctamPrtnrNo: '1', clctamPrtnrKnm: '홍길동', asnRat: '120', woCstn: '30', woCntrCt: '7', rwoTrgAmt: '18,160,000', woDlqAmt: '3,367,000', woThmChramAmt: '770,000', woDlqAddAmt: '336,700', woRsgBorAmt: '336,700', rentalCstn: '30', rentalCntrCt: '7', rentalTrgAmt: '18,160,000', rentalDlqAmt: '3,367,000', rentalThmChramAmt: '770,000', rentalDlqAddAmt: '336,700', rentalRsgBorAmt: '336,700', leaseCstn: '10', leaseCntrCt: '3', leaseTrgAmt: '18,160,000', leaseDlqAmt: '3,367,000', leaseThmChramAmt: '770,000', leaseDlqAddAmt: '336,700', leaseRsgBorAmt: '336,700 ', geMshCstn: '10', geMshCntrCt: '3', geMshTrgAmt: '18,160,000', cgeMshDlqAmt: '3,367,000', geMshDlqAmt: '770,000', geMshThmChramAmt: '336,700', geMshDlqAddAmt: '336,700', geMshRsgBorAmt: '10', hcrMshCstn: '3', hcrMshCntrCt: '18,160,000', hcrMshTrgAmt: '3,367,000', hcrMshDlqAmt: '770,000', hcrMshThmChramAmt: '336,700', hcrMshDlqAddAmt: '336,700', hcrMshRsgBorAmt: '10', spayCntrCt: '3', spayTrgAmt: '18,160,000', spayDlqAmt: '3,367,000', spayThmChramAmt: '770,000', spayDlqAddAmt: '336,700', spayRsgBorAmt: '0', rglrSppCstn: '10', rglrSppCntrCt: '3', rglrSppTrgAmt: '18,160,000', rglrSppDlqAmt: '3,367,000', rglrSppThmChramAmt: '770,000', rglrSppDlqAddAmt: '336,700', rglrSppRsgBorAmt: '166,350' },";
        tempJson += "{ clctamPrtnrNo: '1', clctamPrtnrKnm: '홍길동', asnRat: '120', woCstn: '30', woCntrCt: '7', rwoTrgAmt: '18,160,000', woDlqAmt: '3,367,000', woThmChramAmt: '770,000', woDlqAddAmt: '336,700', woRsgBorAmt: '336,700', rentalCstn: '30', rentalCntrCt: '7', rentalTrgAmt: '18,160,000', rentalDlqAmt: '3,367,000', rentalThmChramAmt: '770,000', rentalDlqAddAmt: '336,700', rentalRsgBorAmt: '336,700', leaseCstn: '10', leaseCntrCt: '3', leaseTrgAmt: '18,160,000', leaseDlqAmt: '3,367,000', leaseThmChramAmt: '770,000', leaseDlqAddAmt: '336,700', leaseRsgBorAmt: '336,700 ', geMshCstn: '10', geMshCntrCt: '3', geMshTrgAmt: '18,160,000', cgeMshDlqAmt: '3,367,000', geMshDlqAmt: '770,000', geMshThmChramAmt: '336,700', geMshDlqAddAmt: '336,700', geMshRsgBorAmt: '10', hcrMshCstn: '3', hcrMshCntrCt: '18,160,000', hcrMshTrgAmt: '3,367,000', hcrMshDlqAmt: '770,000', hcrMshThmChramAmt: '336,700', hcrMshDlqAddAmt: '336,700', hcrMshRsgBorAmt: '10', spayCntrCt: '3', spayTrgAmt: '18,160,000', spayDlqAmt: '3,367,000', spayThmChramAmt: '770,000', spayDlqAddAmt: '336,700', spayRsgBorAmt: '0', rglrSppCstn: '10', rglrSppCntrCt: '3', rglrSppTrgAmt: '18,160,000', rglrSppDlqAmt: '3,367,000', rglrSppThmChramAmt: '770,000', rglrSppDlqAddAmt: '336,700', rglrSppRsgBorAmt: '166,350' },";
        tempJson += "{ clctamPrtnrNo: '1', clctamPrtnrKnm: '홍길동', asnRat: '120', woCstn: '30', woCntrCt: '7', rwoTrgAmt: '18,160,000', woDlqAmt: '3,367,000', woThmChramAmt: '770,000', woDlqAddAmt: '336,700', woRsgBorAmt: '336,700', rentalCstn: '30', rentalCntrCt: '7', rentalTrgAmt: '18,160,000', rentalDlqAmt: '3,367,000', rentalThmChramAmt: '770,000', rentalDlqAddAmt: '336,700', rentalRsgBorAmt: '336,700', leaseCstn: '10', leaseCntrCt: '3', leaseTrgAmt: '18,160,000', leaseDlqAmt: '3,367,000', leaseThmChramAmt: '770,000', leaseDlqAddAmt: '336,700', leaseRsgBorAmt: '336,700 ', geMshCstn: '10', geMshCntrCt: '3', geMshTrgAmt: '18,160,000', cgeMshDlqAmt: '3,367,000', geMshDlqAmt: '770,000', geMshThmChramAmt: '336,700', geMshDlqAddAmt: '336,700', geMshRsgBorAmt: '10', hcrMshCstn: '3', hcrMshCntrCt: '18,160,000', hcrMshTrgAmt: '3,367,000', hcrMshDlqAmt: '770,000', hcrMshThmChramAmt: '336,700', hcrMshDlqAddAmt: '336,700', hcrMshRsgBorAmt: '10', spayCntrCt: '3', spayTrgAmt: '18,160,000', spayDlqAmt: '3,367,000', spayThmChramAmt: '770,000', spayDlqAddAmt: '336,700', spayRsgBorAmt: '0', rglrSppCstn: '10', rglrSppCntrCt: '3', rglrSppTrgAmt: '18,160,000', rglrSppDlqAmt: '3,367,000', rglrSppThmChramAmt: '770,000', rglrSppDlqAddAmt: '336,700', rglrSppRsgBorAmt: '166,350' },";
        tempJson += "{ clctamPrtnrNo: '1', clctamPrtnrKnm: '홍길동', asnRat: '120', woCstn: '30', woCntrCt: '7', rwoTrgAmt: '18,160,000', woDlqAmt: '3,367,000', woThmChramAmt: '770,000', woDlqAddAmt: '336,700', woRsgBorAmt: '336,700', rentalCstn: '30', rentalCntrCt: '7', rentalTrgAmt: '18,160,000', rentalDlqAmt: '3,367,000', rentalThmChramAmt: '770,000', rentalDlqAddAmt: '336,700', rentalRsgBorAmt: '336,700', leaseCstn: '10', leaseCntrCt: '3', leaseTrgAmt: '18,160,000', leaseDlqAmt: '3,367,000', leaseThmChramAmt: '770,000', leaseDlqAddAmt: '336,700', leaseRsgBorAmt: '336,700 ', geMshCstn: '10', geMshCntrCt: '3', geMshTrgAmt: '18,160,000', cgeMshDlqAmt: '3,367,000', geMshDlqAmt: '770,000', geMshThmChramAmt: '336,700', geMshDlqAddAmt: '336,700', geMshRsgBorAmt: '10', hcrMshCstn: '3', hcrMshCntrCt: '18,160,000', hcrMshTrgAmt: '3,367,000', hcrMshDlqAmt: '770,000', hcrMshThmChramAmt: '336,700', hcrMshDlqAddAmt: '336,700', hcrMshRsgBorAmt: '10', spayCntrCt: '3', spayTrgAmt: '18,160,000', spayDlqAmt: '3,367,000', spayThmChramAmt: '770,000', spayDlqAddAmt: '336,700', spayRsgBorAmt: '0', rglrSppCstn: '10', rglrSppCntrCt: '3', rglrSppTrgAmt: '18,160,000', rglrSppDlqAmt: '3,367,000', rglrSppThmChramAmt: '770,000', rglrSppDlqAddAmt: '336,700', rglrSppRsgBorAmt: '166,350' }";
        tempJson += "]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();
        return gson.fromJson(tempJson, type);
    }

    public PagingResult<HashMap<String, String>> getDumyData2() {
        // TODO: 데이터 없는 상태로 작업 하기 때문에 임시 데이터 작업 진행
        String tempJson = "[";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' },";
        tempJson += "{ clctamPrtnrNo: '홍길동', clctamPrtnrKnm: '홍길동', lstmmClctamDvCd: '단기', lstmmClctamPrtnrKnm: '김온달', lstmmClctamPrtnrNo:'1111' , cntrNo: '2022-1234567-01', cntrSn:'111111', cstKnm: '김교원', cstNo: '123456789', pdNm: '전집', dlqMcn: '1', trgAmt: '1,500,000', dlqAmt: '345,000', thmChramAmt: '55,000', dlqAddAmt: '17,250', rsgBorAmt: '', lwmTpCd:'', lwmDtlTpCd:'' , lwmDt: '2022-09-01', dfltDt: '2022-08-25', addr: '서울특별시 중구 을지로 52' }";
        tempJson += "]";
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>() {}.getType();
        ArrayList<HashMap<String, String>> dumyDatas = gson.fromJson(tempJson, type);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalCount(9L);
        return new PagingResult<>(dumyDatas, pageInfo);
    }
}
