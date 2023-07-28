package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 20)
	@Column(name = "name")
	private String name;

	@Lob
	@Column(name = "description")
	private String description;

	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be positive")
	@Column(name = "price")
	private double price;

	@NotNull(message = "Stock quantity cannot be null")
	@Min(value = 0, message = "Stock quantity must be at least 0")
	@Column(name = "stock_quantity")
	private int stockQuantity;

	@Lob
	@Column(name = "image")
	private String image;

}
