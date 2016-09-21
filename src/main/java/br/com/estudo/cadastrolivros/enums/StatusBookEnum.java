package br.com.estudo.cadastrolivros.enums;

public enum StatusBookEnum {

    PUBLISHED("PUBLISHED"), DRAFT("DRAFT");
    private String description;

    private StatusBookEnum(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
