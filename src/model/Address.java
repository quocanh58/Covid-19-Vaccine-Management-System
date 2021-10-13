package model;

public class Address {
	public String numAddres;

	public Address(String numAddres, String address) {
		super();
		this.numAddres = numAddres;
		this.address = address;
	}

	public String address;

	public Address(String address) {
		super();
		this.address = address;
	}

	public String getNumAddres() {
		return numAddres;
	}

	public void setNumAddres(String numAddres) {
		this.numAddres = numAddres;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Address [numAddres=" + numAddres + ", address=" + address + "]";
	}

	public void printAddress() {
		System.out.println(toString());
	}

}
