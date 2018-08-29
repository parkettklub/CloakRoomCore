package hu.bme.sch.parkettklub.smartcheckroom.core.exceptions;

public class WrongBarcodeNumberException extends Exception{
	private static final long serialVersionUID = 1965481845645320908L;
	
	public WrongBarcodeNumberException(String barcode){
		super(barcode + " is a wrong barcode. It must be contains just 4 numbers.");
	}
}