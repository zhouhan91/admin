package com.wemeCity.admin.community.dto;

public class CommunityOrderSaveDTO {

	private long id;

	/** (confirm_flag,VARCHAR(1), null)是否已确认房(Y/N) */
	private String confirmFlag;

	/** (print_contract_flag,VARCHAR(1), null)是否出合同(Y/N) */
	private String printContractFlag;

	/** (sign_contract_flag,VARCHAR(1), null)是否签合同(Y/N) */
	private String signContractFlag;

	/** (complete_flag,VARCHAR(1), null)是否已完成(Y/N) */
	private String completeFlag;

	/** 是否关房 */
	private String closeFlag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getPrintContractFlag() {
		return printContractFlag;
	}

	public void setPrintContractFlag(String printContractFlag) {
		this.printContractFlag = printContractFlag;
	}

	public String getSignContractFlag() {
		return signContractFlag;
	}

	public void setSignContractFlag(String signContractFlag) {
		this.signContractFlag = signContractFlag;
	}

	public String getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}

	public String getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(String closeFlag) {
		this.closeFlag = closeFlag;
	}

}
