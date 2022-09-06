package app.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

import static app.constant.ConstantMessages.*;
public class ProductDto {

    @Column(unique = true, nullable = false)
    @Length(min = 3, max = 50, message = NAME_MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
    private String name;

    @Column
    @Length(min = 3, max = 50, message = CATEGORY_MUST_BE_BETWEEN_3_AND_50_CHARACTERS)
    private String category;

    @Column
    @Length(min = 5, max = 250, message = DESCRIPTION_MUST_BE_BETWEEN_5_AND_250_CHARACTERS)
    private String description;

    @Column
    @Min(value = 1, message = QUANTITY_MUST_BE_AT_LEAST_1_OR_BIGGER)
    @Max(value = 1000, message = QUANTITY_MUST_BE_1000_OR_LOWER)
    private int quantity;

    @Column(name = "created_date", nullable = false)
    @PastOrPresent
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private LocalDate createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @PastOrPresent
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private LocalDate lastModifiedDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
