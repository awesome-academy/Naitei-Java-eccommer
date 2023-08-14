package app.service;

import java.util.List;

import app.model.CartItem;

public interface ShoppingCartService {
	public void addItem(CartItem item);

	public void removeItem(CartItem item);

	public List<CartItem> getCartItems();
	
	public CartItem findByProductName(String productName);
	public CartItem findById(Long Id);
}
