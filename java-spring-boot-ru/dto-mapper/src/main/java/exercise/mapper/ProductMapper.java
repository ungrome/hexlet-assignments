package exercise.mapper;

// BEGIN
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;
import exercise.dto.ProductUpdateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.model.Product;


@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {


    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode", source = "vendorCode")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

    @Mapping(target = "price", source = "cost")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product model);
}

// END
