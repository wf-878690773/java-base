package com.wf;




public enum CuntryEnum {

    ONE(1,"线程一"),TWO(2,"线程二"),THREE(3,"线程三"),FOUR(4,"线程四"),FIVE(5,"线程五");

    private Integer retCode;
    private String retMessae;


    CuntryEnum(Integer retCode, String retMessae) {
        this.retCode = retCode;
        this.retMessae = retMessae;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessae() {
        return retMessae;
    }



    public static CuntryEnum forEach(int i){

        CuntryEnum[] values = CuntryEnum.values();
        for (CuntryEnum value : values) {
            if (i == value.getRetCode()){
                return value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String cuntryEnum = forEach(1).getRetMessae();
        System.out.println(cuntryEnum);
    }
}
