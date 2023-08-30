package com.kyowon.sms.wells.web.kakaosync.common.service;// TODO :카카오 전송 관련 서비스
//package com.kyowon.sms.edu.web.kakaosync.test.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.kyowon.sms.edu.web.kakaosync.test.dvo.KakaoEcsbSyncCustDvo;
//import com.kyowon.sms.edu.web.kakaosync.test.mapper.KakaoFrdnCustMapper;
//import org.apache.commons.codec.binary.Base64;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.kyowon.exception.BusinessException;
//import com.kyowon.mapper.ksscomn.ksosora.msg.KCMsgMapper;
//import com.kyowon.service.comn.KCMsgService;
//import com.kyowon.util.ConvertUtil;
//import com.kyowon.util.StringUtil;
//
//import ch.qos.logback.classic.Logger;
//import kr.co.kyowon.mapper.BaseMappedMap;
//import kr.co.kyowon.mapper.as400.hq.pnr.FrdnCustAdmnMapper;
//import kr.co.kyowon.mapper.as400.sp.pnr.FrdnCustMapper;
//import kr.co.kyowon.service.api.sp.pnr.FrdnCustService;
//
//import static com.kyowon.sms.edu.web.kakaosync.test.service.KakaoEcsbFrdnCustService.castString;
//
//@Service
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
//public class KakaoFrdnCustAdmnService {
//
//    private static final Logger logger = (Logger)LoggerFactory.getLogger(FrdnCustAdmnService.class);
//
//    /**
//     *
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    public Map<String,Object> sendKakao(KakaoEcsbSyncCustDvo kakaoEcsbSyncCustDvo) throws Exception {
//
//        Map<String,Object> result = new HashMap<String,Object>();
//        Map<String,Object> msgParam = null;
//
//        // 휴대 전화번호 체크
//        if(!StringUtil.chkTlphNo((String)param.get("CUSTHNO"))) {
//            throw new BusinessException("휴대전화번호가 잘못 되었습니다.");
//        }
//
//        msgParam = this.setBindKakaoMsg(param); // 메시지 생성
//
//        try {
//
//            msgParam.put("MT_PR", msgMapper.getKakaoSrno()); // 카카오톡 메시지 고유 아이디 채번
//
//            logger.debug("### KAKAO SEND : " + msgParam);
//
//            int insertCnt = msgMapper.createKakaoSndg(msgParam);
//
//
//            /**
//             * TO-DO : 카카오 알림톡 발송 히스토리 저장
//             *   최초발송일자
//             *   최종발송일자
//             */
//            String templateCode = castString(param.get("TEMPCODE"));
//
//            // 컨택알림톡발송은 고객 상담 히스로리에 남긴다
//            if("VC01".equals(templateCode) || "EM01".equals(templateCode) || "ST90".equals(templateCode)) {
//                Map<String,Object> custParam = new HashMap<String,Object>();
//                custParam.put("CUSTSRNO", param.get("CUSTSRNO")); // 잠재고객 일련
//                String CONSCNTN = msgParam.get("SUBJECT")  + " (발송ID:" + msgParam.get("MT_PR") + ")";
//                if("VC01".equals(templateCode) || "ST90".equals(templateCode)) {
//                    CONSCNTN += ", 방문일자 : " + castString(param.get("visitDate")).substring(4,6) + "월 " + castString(param.get("visitDate")).substring(6,8) + "일";
//                } else if ( "EM01".equals(templateCode)) {
//                    CONSCNTN += ", 강의일자 : " + castString(param.get("lctDate")).substring(4,6) + "월 " + castString(param.get("lctDate")).substring(6,8) + "일";
//                }
//                custParam.put("CONSCNTN", CONSCNTN);
//                Map<String,Object> custResult = frdnCustService.saveCustHstr(custParam);
//
//                // 상담히스토리 추가 결과를 화면으로 전송
//                if("T".equals(castString(custResult.get("ackdResl")))) {
//                    result.put("cons", custResult.get("cons"));
//                }
//
//                //알림톡 발송 여부 저장
//                if("ST90".equals(templateCode)){
//                	Map<String,Object> map = new HashMap<String,Object> ();
//                	map.put("vstalmtyn", "Y");
//                	map.put("custsrNo", castString(param.get("CUSTSRNO")));
//                	frdnCustService.kitVisitAgreeSave(map);
//                }
//            }
//
//            /**
//             * TO-DO : 알림톡 발송 히스토리 저장
//             */
//
//            if(null != param.get("CUSTSRNO")) { // 최초 모바일 명함 발송시 고객정보가 저장되지 않은 상태이므로 고객id가 존재하지 않음
//                param.put("ALMTSRNO",msgParam.get("MT_PR")); // 메시지 발송 ID
//                KakaoFrdnCustMapper.insertAlmtHstr(param);
//            }
//
//
//            if (insertCnt != 1) {
//                throw new BusinessException ("카카오 알림톡 발송을 정상적으로 수행할 수 없습니다.");
//            }
//
//            result.put("ackdResl", "T");
//            result.put("ackdReslMsg",msgParam.get("returnMsg"));
//            result.put("param", msgParam);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BusinessException("카카오 알림톡 발송중 오류가 발생했습니다.");
//        }
//
//        return result;
//    }
//
//}
