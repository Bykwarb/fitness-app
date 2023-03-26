package com.example.foodservice.entity;

import lombok.Getter;

@Getter
public enum Category {
    MEAT_AND_MEAT_PRODUCTS("MM"),
    DIARY_PRODUCTS("DR"),
    GRAIN_AND_CEREALS("GC"),
    FRUITS("FTS"),
    VEGETABLES("VGT"),
    LEGUMES("LGM"),
    NUTS_AND_SEEDS("NS"),
    OILS_AND_FATS("OF"),
    SWEETENERS("SWR"),
    SWEETS("SW"),
    SPICES_AND_HERBS("SH"),
    BEVERAGES("BVR"),
    BAKER_GOODS("BG"),
    SNACKS("SNK"),
    CONVENIENCE_FOODS("CF"),
    CONDIMENTS("CND");

    public final String code;

    Category(String code) {
        this.code = code;
    }

    public static Category getByCode(String code) {
        for (Category category : Category.values()) {
            if (category.code.equals(code)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Category.class.getName() + " with code " + code);
    }
}
