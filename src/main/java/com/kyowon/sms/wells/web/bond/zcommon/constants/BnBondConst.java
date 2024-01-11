package com.kyowon.sms.wells.web.bond.zcommon.constants;

import java.util.Arrays;

import com.sds.sflex.system.config.constant.CommConst;

public class BnBondConst {
    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/bond";

    public enum RentalPdCd {
        // 연체상태코드
        STR_00("00", "연체상태아님                                              "),
        STR_01("01", "이자연체                                                  "),
        STR_02("02", "할부금연체                                                "),
        STR_03("03", "한도초과                                                  "),
        STR_04("04", "원금연체(기한이익상실)                                  "),
        STR_05("05", "기일경과연체                                              "),
        STR_06("06", "기한이익 상실 전 신용카드 연체                    "),
        STR_07("07", "기한이익 상실 후 신용카드 연체                    "),
        STR_09("09", "기타                                                      "),
        // 렌탈상품코드
        STR_701("701", "정수기, 이온수기(정수 용품)                         "),
        STR_702("702", "생활가전(냉장고)                                        "),
        STR_703("703", "생활가전(에어컨)                                        "),
        STR_704("704", "생활가전(공기청정기)                                    "),
        STR_705("705", "생활가전(청소기)                                        "),
        STR_706("706", "생활가전(LED램프)                                       "),
        STR_707("707", "생활가전(비데)                                          "),
        STR_708("708", "생활가전(도정기)                                        "),
        STR_709("709", "생활가전(기타)                                          "),
        STR_710("710", "주방용품(음식물처리기)                                  "),
        STR_711("711", "주방용품(식기세척기)                                    "),
        STR_712("712", "주방용품(렌지, 인덕션, 후드)                        "),
        STR_713("713", "주방용품(기타)                                          "),
        STR_714("714", "헬스케어(안마의자)                                      "),
        STR_715("715", "헬스케어(안마의자 외의 안마, 마사지용품)          "),
        STR_716("716", "헬스케어(기타)                                          "),
        STR_717("717", "건강/의료용품                                           "),
        STR_718("718", "가구(침대, 매트리스)                                  "),
        STR_719("719", "가구(쇼파)                                              "),
        STR_720("720", "가구(책상/의자)                                       "),
        STR_721("721", "가구(옷장, 책장, 장롱)                              "),
        STR_722("722", "가구(기타)                                              "),
        STR_723("723", "PC, 서버(컴퓨터 용품)                                 "),
        STR_724("724", "노트북                                                    "),
        STR_725("725", "테블릿PC                                                  "),
        STR_726("726", "게임기, 게임 소프트 웨어                            "),
        STR_727("727", "그림, 악기 예술품                                     "),
        STR_728("728", "카메라, 촬영 장비                                     "),
        STR_729("729", "계측기                                                    "),
        STR_730("730", "타이어                                                    "),
        STR_731("731", "기계                                                      "),
        STR_732("732", "중장비                                                    "),
        STR_733("733", "건축자제, 용품                                          "),
        STR_734("734", "아웃도어 장비, 용품                                   "),
        STR_735("735", "명품,의류,잡화                                        "),
        STR_736("736", "레저,스포츠,펜션,민박                               "),
        STR_737("737", "유아용품, 장난감                                        "),
        STR_799("799", "기타(상기에 포함되지 않은 렌탈 서비스)          "),
        STR_810("810", "주류 공급 계약                                        "),
        STR_999("999", "기타계약                                                  "),
        NON("non", "");

        private final String codeId;
        private final String codeName;

        RentalPdCd(String codeId, String codeName) {
            this.codeId = codeId;
            this.codeName = codeName;
        }

        public String getCodeId() {
            return this.codeId;
        }

        public String getCodeName() {
            return this.codeName;
        }

        public static String getValue(String code) {
            return Arrays.stream(values())
                .filter((value) -> value.codeId.equals(code))
                .findFirst()
                .orElse(NON).getCodeName();
        }
    }
}
