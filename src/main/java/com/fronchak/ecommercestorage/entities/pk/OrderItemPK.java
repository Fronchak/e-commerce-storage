package com.fronchak.ecommercestorage.entities.pk;

import java.util.Objects;

import com.fronchak.ecommercestorage.entities.Order;
import com.fronchak.ecommercestorage.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK {

	@ManyToOne
	@JoinColumn(name = "id_order")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;
	
	public OrderItemPK() {}
	
	public OrderItemPK(Order order, Product product) {
		this.order = order;
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
}
