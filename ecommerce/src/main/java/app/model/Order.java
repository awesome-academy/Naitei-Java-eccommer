package app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "total_amount")
	private double totalAmount;

	@Column(name = "delivery_status")
	private String deliveryStatus;

	@Column(name = "recipient_name")
	private String recipientName;

	@Column(name = "recipient_phone")
	private String recipientPhone;
}
