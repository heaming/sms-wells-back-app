package com.kyowon.sms.wells.web.product.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class PdProductWellsConst {

    // 상품 기본 URL
    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/product";

    // 엑셀 업로드 리턴 상태값
    public static final String EXCEL_UPLOAD_SUCCESS = "S";
    public static final String EXCEL_UPLOAD_ERROR = "E";

    // 테이블 정보(메타 값 매칭:TB_PDBS_PD_BAS)
    public static final String TBL_TB_PDBS_PD_BAS = "tbPdbsPdBas";
    public static final String TBL_TB_PDBS_PD_ECOM_PRP_DTL = "tbPdbsPdEcomPrpDtl";

    // 상품구분
    public static final String PD_TP_CD_STANDARD = "P"; // 기준상품
    public static final String PD_TP_CD_SERVICE = "S"; // 서비스
    public static final String PD_TP_CD_MATERIAL = "M"; // 교재제품
    public static final String PD_TP_CD_COMPOSITION = "C"; // 복합상품

    // 메타컬럼 필수유무
    public static final String MNDT_Y = "Y";
    public static final String MNDT_N = "N";

    public static final String SAP_MAT_CD = "sapMatCd";

    // 메타컬럼 데이터 유형 코드(DTA_TP_CD)
    public static final String DTA_TP_CHAR = "01"; // 문자
    public static final String DTA_TP_DATE = "02"; // 날짜
    public static final String DTA_TP_NUMBER = "03"; // 숫자

    public static final String CARMEL_LRNN_LV_CD = "lrnnLvGrpCd"; // 카멜표기된 단계구분코드
    public static final String LRNN_LV_GRP_DV_CD = "LRNN_LV_GRP_DV_CD"; // 학습단계그룹구분코드

    // 임시저장 유무(PD_TEMP_SAVE_CD)
    public static final String TEMP_SAVE_Y = "Y";
    public static final String TEMP_SAVE_N = "N";

    // 상품확장속성그룹코드(PD_EXTS_PRP_GRP_CD)
    public static final String PD_EXTS_PRP_GRP_CD_CMN = "CMN"; // 공통
    public static final String PD_EXTS_PRP_GRP_CD_AFS = "AFS"; // AS
    public static final String PD_EXTS_PRP_GRP_CD_PRC = "PRC"; // 가격
    public static final String PD_EXTS_PRP_GRP_CD_STLM = "STLM"; // 결제
    public static final String PD_EXTS_PRP_GRP_CD_CNTR = "CNTR"; // 계약
    public static final String PD_EXTS_PRP_GRP_CD_EXCH = "EXCH"; // 교환/반품
    public static final String PD_EXTS_PRP_GRP_CD_ETC = "ETC"; // 기타
    public static final String PD_EXTS_PRP_GRP_CD_SL = "SL"; // 매출
    public static final String PD_EXTS_PRP_GRP_CD_GO = "GO"; // 발주
    public static final String PD_EXTS_PRP_GRP_CD_SPP = "SPP"; // 배송
    public static final String PD_EXTS_PRP_GRP_CD_ANA = "ANA"; // 분석
    public static final String PD_EXTS_PRP_GRP_CD_FEE = "FEE"; // 영업수수료
    public static final String PD_EXTS_PRP_GRP_CD_HIST = "HIST"; // 이력
    public static final String PD_EXTS_PRP_GRP_CD_SCHD = "SCHD"; // 일정관리
    public static final String PD_EXTS_PRP_GRP_CD_COCN = "COCN"; // 전집
    public static final String PD_EXTS_PRP_GRP_CD_LRNN = "LRNN"; // 학습/외국어
    public static final String PD_EXTS_PRP_GRP_CD_LV = "LV"; // 학습단계
    public static final String PD_EXTS_PRP_GRP_CD_PART = "PART"; // AS부품
    public static final String PD_EXTS_PRP_GRP_CD_FINC = "FINC"; // 재무
    public static final String PD_EXTS_PRP_GRP_CD_PDCT = "PDCT"; // 판매제품

    public static final String VALIDATION_TARGET_META = "META";
    public static final String VALIDATION_TARGET_DB = "DB";

    // 상품유형상세코드 (TB_PDBS_PD_BAS.PD_TP_DTL_CD)
    public static final String PD_TP_DTL_CD_M = "01"; // 제품
    public static final String PD_TP_DTL_CD_P = "02"; // (AS)부품
    public static final String PD_TP_DTL_CD_A = "03"; // 활동물품

    // 상품 변경 이력 최종일 (설계요구사항) 
    public static final String END_DATE_STR = "99991231235959";

}
