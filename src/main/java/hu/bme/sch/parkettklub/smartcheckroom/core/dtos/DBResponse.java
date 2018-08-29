package hu.bme.sch.parkettklub.smartcheckroom.core.dtos;

public enum DBResponse {
	USED_BARCODE("Barcode is used in an other checkroom number!"), //
	LOCKED_CHECKROOMNUMBER("Checkroom number is locked in an other session!"), //
	WRONG_CHECKROOMNUMBER("Checkroom number is WRONG!"), //
	USED_CHECKROOMNUMBER("Checkroom number is used in an other session!"), //
	INVALID_OTHERDATA("Some Item data are invalid!"), OK("DB updated."), NO_RESPONSE("No DB response!");

	String msg;

	private DBResponse(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return msg;
	}
}