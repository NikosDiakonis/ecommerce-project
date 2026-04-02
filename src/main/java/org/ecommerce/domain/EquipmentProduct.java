package org.ecommerce.domain;

public class EquipmentProduct extends Product{
boolean extension;

 public String hasWarrantyExtension(){
    String result;

    if (extension == true){
         result = " Your Warranty Extension is active.";
     }else {
         result = " Your Warranty Extension is not active.";
     }

     return result;
    }

    public EquipmentProduct(String name, double price, String sku, boolean hasExtension) {
        super(name, price, sku);
        this.extension = hasExtension;
    }

    public boolean isExtension() {
        return extension;
    }

    public void setExtension(boolean extension) {
        this.extension = extension;
    }

    @Override
    public String getInfo() {
     String resultA = super.getInfo();
     String resultB = hasWarrantyExtension();
     String result = resultA + resultB;
     return result;
    }
}
