package app.model.binding;

import app.model.entity.enums.FlowerSize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static app.constant.ConstantMessages.*;

public class UpdateFlowerDto {
    @Column(unique = true, nullable = false)
    @Length(min = 3, max = 50, message = PRODUCT_NAME_MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
    private String name;

    @Column(unique = true, nullable = false)
    @Length(min = 3, max = 50, message = PRODUCT_TYPE_MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
    private String type;

    @Column
    @Length(min = 5, max = 250, message = DESCRIPTION_MUST_BE_BETWEEN_5_AND_250_CHARACTERS)
    private String description;

    @Column
    @Min(value = 1, message = QUANTITY_MUST_BE_AT_LEAST_1_OR_BIGGER)
    @Max(value = 1000, message = QUANTITY_MUST_BE_1000_OR_LOWER)
    private int quantity;
    @Column(name = "flower_size")
    private FlowerSize flowerSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public FlowerSize getFlowerSize() {
        return flowerSize;
    }

    public void setFlowerSize(FlowerSize flowerSize) {
        this.flowerSize = flowerSize;
    }
}
