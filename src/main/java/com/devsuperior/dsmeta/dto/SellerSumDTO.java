package com.devsuperior.dsmeta.dto;

public class SellerSumDTO {
    private String sellerName;
    private Double total;

    public SellerSumDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SellerSumDTO() {

    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SellerSumDTO{" +
                "name='" + sellerName + '\'' +
                ", sum=" + total +
                '}';
    }
}
