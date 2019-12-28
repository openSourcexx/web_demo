package com.example.webdemo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * todo
 *
 * @author tangaq25172@yunrong.cn
 * @since 2019/12/28 14:28
 */
@Getter
@AllArgsConstructor
public enum EnumSerialModalName {
    /** 描述 */
    INTO_APPLY("IA", "intoapply", "进件申请ID"),
    CREDIT_APPLY("CA", "creditapply", "授信申请ID"),
    LOAN_APPLY("LA", "loanapply", "支用申请ID"),
    LOAN_INVOICE_APPLY("LIA", "loaninvoiceapply", "借据申请单ID"),
    CREDIT("CI", "credit", "授信ID"),
    CONTRACT_APPLY("NI", "contractapply", "合同申请ID"),
    PROJECT_ID("PI", "projectid", "项目ID"),
    COMMODITY_ID("CD", "commodityid", "商品ID"),
    ORDER_VCHR_ID("OVI", "ordervchrid", "订单ID"),
    REPAY_ID("RPI", "repayid", "还款ID"),
    REFUND_APPLY("RA", "refundapplyid", "退款申请ID"),
    ADMITTANCE_SURVEY_ID("ASI", "admittancesurveyid", "准入调查ID"),
    BIZ_TRADE("BT", "bizTradeSerialId", "交易流水ID"),
    GROUP_ID("GI", "payLoanOrgGroupId", "放款机构组表ID"),
    POLICY_SERIAL_ID("PSI", "policySerialId", "风控流水id"),
    CREDIT_BIZ_SERIAL("CBS", "creditBizSerialId", "授信调整流水id"),
    ADJUST_APPLY_ID("AD", "adjustApplyId", "贷后调整申请Id"),
    REPAY_PLAN_ADJUST_RECORD_ID("ADR", "adjustRecordId", "还款计划变更记录Id"),
    SETTLE_RULE_ID("SRI", "settleRuleId", "结算规则Id"),
    RULE_SUB_ID("RSI", "ruleSubId", "结算子规则Id"),
    MERCHANT_REVIEW_ID("MS", "merchantReviewId", "商户审批Id"),
    MERCHANT_SIGN_ID("MS", "merchantSignId", "商户签约Id"),
    MERCHANT_SIGN_PRODUCT_ID("MSP", "merchantSignProductId", "商户签约产品Id"),
    ORG_COMPENSATION_ID("OCI", "orgCompensationId", "商户签约产品Id"),
    MERCHANT_FEE_ID("MFI", "merchantFeeId", "商户费用Id"),
    LOAN_VERIFICATE_ID("LVI", "loanVerificateId", "核销信息id"),
    CUST_MGR_APPLY_ID("CMA", "custMgrApplyId", "客户经理申请id"),
    MERCHANT_SETTLE_ID("MSI", "merchantSettleId", "商户结算单id"),
    MERCHANT_SETTLE_SUB_ID("MSUI", "merchantSettleSubId", "商户结算子单id"),
    MERCHANT_SETTLE_APPLY_ID("MSAI", "merchantSettleApplyId", "商户结算申请单id"),
    PERSONAL_INFO_CHANGE_APPLY_SERIAL_ID("PICAS", "personalInfoChangeApplySerialId", "用户信息变更申请id"),
    PERSONAL_BANK_CARD_CHANGE_APPLY_SERIAL_ID("PBCCAS", "personalBankCardChangeApplySerialId", "个人银行卡变更申请流水id"),
    PERSONAL_VISIT_SURVEY_SERIAL_ID("PVSS", "personalVisitSurveySerialId", "贷后回访调查流水id"),
    USER_MANAGER_CHANGE_APPLY_SERIAL_ID("UMCASI", "userManagerChangeApplySerialId", "客户经理变更申请流水id"),
    PERSONAL_LOAN_SUPPLEMENT_APPLY_SERIAL_ID("PLSAS", "personalLoanSupplementApplySerialId", "个人贷款补件流水id"),
    MERCHANT_VISIT_APPLY_SERIAL_ID("MVASI", "merchantVisitApplySerial", "商户调查回访流水id"),
    CREDIT_ARTIFICIAL_WITHHOLD_APPLY_SERIAL("CAWAS", "creditArtificialWithhold", "手工代扣申请流水表"),
    CREDIT_CERT_APPLY_SERIAL_ID("CCAI", "creditCertApplySerial", "证明开具流水id"),
    LOAN_APPLY_RATE("LAR", "loanapplyRate", "支用申请费率ID"),
    RH_CREDIT_REPORT_APPLY_SERIAL_ID("RCRASI", "rhCreditReportApplySerialId", "人行征信查询流水id"),
    USER_MANAGER_CHANGE_APPLY_BATCH_ID("UMCABI", "userManagerChangeApplyBatchId", "客户经理变更批次号id"),
    PENALTY_REFUND_APPLY_SERIAL_ID("PRAS", "penaltyRefundApplySerialId", "违约金退还申请流水id"),
    OVERFLOW_FEE_REFUND_APPLY_SERIAL_ID("OFRAS", "overflowFeeRefundApplySerialId", "溢缴款退还申请流水id"),
    ADVANCE_FEE_REFUND_APPLY_SERIAL_ID("AFRAS", "advanceFeeRefundApplySerialId", "溢缴款退还申请流水id"),
    LOAN_FEE_WITH_HOLD_APPLY_ID("LFWH", "loanFeeWithHoldApplyId", "贷款服务费代扣申请Id"),
    PARTNER_MONTH_LOAN_ID("PML", "partnerMonthLoan", "合作方月贷款id"),
    OVERPAY_REFUND_APPLY("ORA", "overpayRefundApply", "溢缴款退款申请id"),
    SUB_PRODUCT_INFO("SPI", "subProductInfo", "子产品授信明细id"),
    CREDIT_LIMIT_ID("CLI", "creditLimitID", "授信额度id"),
    CAPITAL_SIGN_ID("CS", "capitalSignId", "资金方签约Id"),
    CAPITAIL_SERIAL_DEAL_ID("CSDSI", "capitalSerialDealSerialId", "资金流水处理id"),
    CAPITAL_SERIAL_REFUND_ID("CSRI", "capitalSerialRefundId", "资金流水退款记录id"),
    COMPENSATION_APPLY_ID("CAI", "compensationApplyId", "代偿申请id"),
    COMPENSATION_CONFIRM_ID("CCI", "compensationConfirmId", "代偿确认id"),
    COMPENSATION_WITHHOLD_ID("CWI", "compensationWithholdId", "代偿扣款id");
    /** 状态码 */
    private String code;

    private String type;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumSerialModalName } 实例
     **/
    public static EnumSerialModalName find(String code) {
        for (EnumSerialModalName instance : EnumSerialModalName.values()) {
            if (instance.getCode()
                .equals(code)) {
                return instance;
            }
        }
        return null;
    }
}