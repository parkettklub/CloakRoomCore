package hu.bme.sch.parkettklub.smartcheckroom.core.dtos;

import hu.bme.sch.parkettklub.smartcheckroom.core.exceptions.WrongBarcodeNumberException;

public class BarcodeNumber {
	private String barcodeNumber = "0000";

	private BarcodeNumber() {

	}

	public static BarcodeNumber createBarcodeNumber(String barcode) throws WrongBarcodeNumberException {
		BarcodeNumber response = new BarcodeNumber();
		String number = barcode.trim();
		if (number.length() != 4 || number.replaceAll("[0-9]", "").length() != 0) {
			throw new WrongBarcodeNumberException(barcode);
		} else {
			response.barcodeNumber = number;
			return response;
		}
	}

	public static BarcodeNumber createBarcodeNumber(int barcode) throws WrongBarcodeNumberException {
		BarcodeNumber response = new BarcodeNumber();
		if (0 >= barcode || barcode >= 10000) {
			throw new WrongBarcodeNumberException("" + barcode);
		} else {
			String number = "" + barcode;
			while (number.length() < 4) {
				number = "0" + number;
			}
			response.barcodeNumber = number;
			return response;
		}
	}

	public String get() {
		return barcodeNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcodeNumber == null) ? 0 : barcodeNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarcodeNumber other = (BarcodeNumber) obj;
		if (barcodeNumber == null) {
			if (other.barcodeNumber != null)
				return false;
		} else if (!barcodeNumber.equals(other.barcodeNumber))
			return false;
		return true;
	}
}