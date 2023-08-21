package app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "enable")
	@ColumnDefault("true")
	private Boolean enable;

	public Product() {
	}

	public Product(Long id, String name, String description, double price, int stockQuantity, String image,
			Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.image = image;
		this.category = category;
		this.enable = true;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", category=" + category + ", description='"
				+ description + '\'' + ", price=" + price + ", stockQuantity=" + stockQuantity + ", image='" + image
				+ '\'' + enable + '}';
	}

}
