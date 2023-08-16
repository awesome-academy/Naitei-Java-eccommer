package app.request;

public class formOrder {
	private String recipientName;

	private String recipientPhone;

	private String recipientAddress;

	private double totalAmount;

	public formOrder() {
	}

	public formOrder(String recipientName, String recipientPhone, String recipientAddress, double totalAmount) {
		super();
		this.recipientName = recipientName;
		this.recipientPhone = recipientPhone;
		this.recipientAddress = recipientAddress;
		this.totalAmount = totalAmount;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "FormOrder{" + "recipientName='" + recipientName + '\'' + ", recipientPhone='" + recipientPhone + '\''
				+ ", recipientAddress='" + recipientAddress + '\'' + ", totalAmount=" + totalAmount + '}';
	}
}