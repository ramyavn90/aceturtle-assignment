package com.aceturtle.assignment;

public class SiteConfig {

	public static final long MAX_TIMEOUT = 10;

	public static final String URL = "https://in.puma.com";

	public static final String TITLE = "Buy Sports T-Shirts, Tracks, Running Shoes and Accessories Online - in.puma.com";

	public static final String LINK_MEN = "//div[@id='header-nav']/ul/li[1]/a";

	public static final String LINK_RUNNING = "//li[@id='men-subnav']/div/div/div[2]/ul/li[2]/a";

	public static final String ITEM_SHOE = "//div[@id='mp-pusher']/div/div[4]/div[2]/div[2]/div[2]/div/ul/li[%s]";

	public static final String LINK_SHOE = "//div[@id='mp-pusher']/div/div[4]/div[2]/div[2]/div[2]/div/ul/li[%s]/div/h2/a";

	public static final String TRIGGER_PRODUCT_SIZE = "//div[@id='size_label']/div[1]/div[2]";

	public static final String BUTTON_PRODUCT_SIZE_10 = "//span[@id='180~72']";

	public static final String TEXT_PRODUCT_NAME = "//form[@id='product_addtocart_form']/div[3]/div[1]/span";

	public static final String TEXT_PRODUCT_PRICE = "//div[@id='product-options-wrapper']/dl/div/span/span";

	public static final String TEXT_PRODUCT_SIZE = "//div[@id='select_label_size']";

	public static final String DROPDOWN_PRODUCT_QUANTITY = "//select[@id='qty']";

	public static final String BUTTON_ADD_TO_CART = "//form[@id='product_addtocart_form']/div[3]/div[7]/div[2]/div/button";

	public static final String TEXT_CART_PRODUCT_NAME = "//table[@id='shopping-cart-table']/tbody/tr/td[2]/h2/a";

	public static final String TEXT_CART_PRODUCT_PRICE = "//table[@id='shopping-cart-table']/tbody/tr/td[3]/span/span";

	public static final String TEXT_CART_PRODUCT_SIZE = "//table[@id='shopping-cart-table']/tbody/tr/td[2]/dl/dd[2]";

	public static final String DROPDOWN_CART_PRODUCT_QUANTITY = "//table[@id='shopping-cart-table']/tbody/tr/td[4]/div/select";
}
