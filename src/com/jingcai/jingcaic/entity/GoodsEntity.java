package com.jingcai.jingcaic.entity;
/**
 * ��Ʒ������
 */
public class GoodsEntity {
	//��Ʒid
	private String id;
	//��Ʒ����
	private String title;
	//�Ƿ���Ч
	private String is_valid;
	//�Ƿ����
	private String is_sales;
	//��ƷͼƬ
	private String img;
	//��Ʒ����
	private String unit_prince;
	//��Ʒ�ܼ�
	private String amount;
	//��Ʒ���10��װ��
	private String unit_bag_weight;
	//����
	private String number;
	//��Ʒע��(��������Ʒ�Ƿ������޹���Ʒ������Ҫ���Լ�һЩ����Ҫ��)
	private String  notes;
	//��Ʒ����
	private String introduce;
	/**
	 * ��ʶ�Ƿ����ɾ��
	 */
	private boolean canRemove = true;
	
	public GoodsEntity() {
		
	}
	public GoodsEntity(String id, String title, String is_valid,
			String is_sales, String img, String unit_prince, String amount,
			String unit_bag_weight, String number, String notes,
			String introduce, boolean canRemove) {
		this.id = id;
		this.title = title;
		this.is_valid = is_valid;
		this.is_sales = is_sales;
		this.img = img;
		this.unit_prince = unit_prince;
		this.amount = amount;
		this.unit_bag_weight = unit_bag_weight;
		this.number = number;
		this.notes = notes;
		this.introduce = introduce;
		//��ʶ�Ƿ����ɾ��
		this.canRemove = canRemove;
	}


	public boolean isCanRemove() {
		return canRemove;
	}

	public void setCanRemove(boolean canRemove) {
		this.canRemove = canRemove;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public String getIs_sales() {
		return is_sales;
	}
	public void setIs_sales(String is_sales) {
		this.is_sales = is_sales;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUnit_prince() {
		return unit_prince;
	}
	public void setUnit_prince(String unit_prince) {
		this.unit_prince = unit_prince;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit_bag_weight() {
		return unit_bag_weight;
	}
	public void setUnit_bag_weight(String unit_bag_weight) {
		this.unit_bag_weight = unit_bag_weight;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
