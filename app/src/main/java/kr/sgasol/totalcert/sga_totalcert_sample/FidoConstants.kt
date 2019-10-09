package fido.umbridge

var FIDO_TARGETURL = "https://fido.sgasol.kr:9017"                // sga 개발서버
var FIDO_APPID = "https://fido.sgasol.kr:9017/appid"              // sga 개발서버 appid

var FIDO_TYPE_AUTH = "auth"
var FIDO_TYPE_REG = "reg"
var FIDO_TYPE_DREG = "dereg"
var FIDO_REGED_INFO = "reginfo"                                   // FIDO 등록정보 요청
var FIDO_DELETE_INFO = "deleteinfo"                               // FIDO 등록정보 삭제

val REG_ACTIVITY_RES = 1
val AUTH_ACTIVITY_RES = 2
val REGEDINFO_ACTIVITY_RES = 3                                 // 등록해제 결과
val DELETEINFO_ACTIVITY_RES = 4                                // 정보 삭제 결과

val FIDO_RESULT_SUCCESS = 10
val FIDO_RESULT_FAIL = 11
val FIDO_RESULT_REGEDINFO = 12                                 // FIDO 등록 정보
val FIDO_RESULT_DELETEINFO = 13                                // FIDO 등록 정보 삭제


val FIDO_RESULT_SUCCESS_CD = "10"                           // FIDO 성공
val FIDO_RESULT_FAIL_CD = "11"                              // FIDO 실패

val SUCCESS_CD = "0x0000001"                                // 성공
val CANCLE_CD = "0x0000002"                                 // 취소
val NETWORK_ERREOR_CD = "0x0000003"                         // 네트워크 오류
val AUTHFAIL_ERROR_CD = "0x0000004"                         // 인증 실패
val LOCAL_AUTHFAIL_ERROR_CD = "0x0000005"                   // 로컬 인증 실패

val APPID = "APPID"
val AGENTURL = "AGENTURL"
val OPERATION = "OPERATION"
val USERID = "USERID"
