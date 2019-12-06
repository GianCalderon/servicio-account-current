package com.springboot.currentAccount.dto;

public class PersonalDto {

	private String idCuenta;
	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;
	
	
	

	public PersonalDto() {
		super();
	}

	public PersonalDto(String idCuenta, String tipoDoc, String numDoc, String name, String apePat, String apeMat,
			String address) {
		
		this.idCuenta = idCuenta;
		this.tipoDoc = tipoDoc;
		this.numDoc = numDoc;
		this.name = name;
		this.apePat = apePat;
		this.apeMat = apeMat;
		this.address = address;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApePat() {
		return apePat;
	}

	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	public String getApeMat() {
		return apeMat;
	}

	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PersonalDto [idCuenta=" + idCuenta + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc + ", name=" + name
				+ ", apePat=" + apePat + ", apeMat=" + apeMat + ", address=" + address + "]";
	}

}
