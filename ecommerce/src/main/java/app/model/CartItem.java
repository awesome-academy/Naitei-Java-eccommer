package app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
	private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	public CartItem() {
	}

	public CartItem(Long id, Cart cart, Product product, int quantity) {
		this.id = id;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cart.getId()+
                ", product=" + product + 
                ", quantity=" + quantity +
                '}';
    }
}
